import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[][] graph=new String[19][19];
        String[] str;
        boolean result;

        for(int i=0;i<19;i++) {
            str=br.readLine().split(" ");
            for(int j=0;j<19;j++) {
                graph[i][j]=str[j];
            }
        } // 정수형 그래프 생성

        for(int i=0;i<19;i++) { // 좌 -> 우, 상-> 하 방향으로 탐색 (오른쪽, 아래 방향, 아래방향 대각선, 위쪽방향 대각선 만 확인)
            for(int j=0;j<19;j++) {
                if(!graph[i][j].equals("0")){
                    result=search(graph,i,j);
                    if(result) {
                        System.out.println(graph[i][j]);
                        System.out.println((i+1)+" "+(j+1));
                        return;
                    }
                }
            }
        }
        System.out.println(0); // 무승부
    }
    public static boolean search(String[][] graph,int x,int y) {
        int cnt=0;
        String curr=graph[x][y]; // 찾아야하는 돌
        boolean Flag=false;

        // 오른쪽 탐색
        for(int i=y+1;i<y+5;i++) { // 옆으로 4개 확인
            if(i>18) { // 영역 밖은 아웃
                break;
            }
            if(graph[x][i].equals(curr)) {
                cnt++;
            }
        }
        if(cnt==4) {
            Flag=Valid(1,graph,x,y); // 오른쪽 방향으로 오목이 맞는지 확인
            if(Flag)
                return Flag;
        }

        // 아래 탐색
        cnt=0;
        for(int i=x+1;i<x+5;i++) { // 아래로 4개 확인
            if(i>18) { // 영역 밖은 아웃
                break;
            }
            if(graph[i][y].equals(curr)) {
                cnt++;
            }
        }
        if(cnt==4) {
            Flag=Valid(2,graph,x,y); // 아래 확인
            if(Flag)
                return Flag;
        }

        // 아래 대각선 탐색
        cnt=0;
        for(int i=1;i<5;i++) { // 아래 대각선으로 4개 확인
            if(x+i>18 || y+i>18) { // 영역 밖은 아웃
                break;
            }

            if(graph[x+i][y+i].equals(curr)) {
                cnt++;
            }
        }
        if(cnt==4) {
            Flag=Valid(3,graph,x,y); // 아래 대각선 확인
            if(Flag)
                return Flag;
        }

        // 위 대각선 탐색
        cnt=0;
        for(int i=1;i<5;i++) { // 위 대각선으로 4개 확인
            if(x-i<0 || y+i>18) { // 영역 밖은 아웃
                break;
            }

            if(graph[x-i][y+i].equals(curr)) {
                cnt++;
            }
        }
        if(cnt==4) {
            Flag=Valid(4,graph,x,y); // 아래 확인
            if(Flag)
                return Flag;
        }
        return Flag;
    }

    public static boolean Valid(int direction,String[][] graph,int x,int y){
        boolean Flag=false;
        int size=graph.length;
        String curr=graph[x][y];

        if(direction==1){ // 오른쪽
            if(y-1<0 && !graph[x][y+5].equals(curr)) // 왼쪽이 out of bounds , 오른쪽은 유효
                Flag=true;
            else if(y+5>=size && (!graph[x][y-1].equals(curr))) // 오른쪽이 out of bounds , 왼쪽은 유효
                Flag=true;
            else if(y+5<size && y-1>=0 && (!graph[x][y+5].equals(curr)) && (!graph[x][y-1].equals(curr))) { // 둘 다 valid
                Flag=true;
            }
        } // 왼쪽이 없거나, 오른쪽+5가 없거나, 있는데 다른 값이면 오목


        else if(direction==2){ // 아래쪽
     //       System.out.println("아래쪽 체크");
            if(x-1<0 && (!graph[x+5][y].equals(curr)))
                Flag=true;
            else if(x+5>=size && (!graph[x-1][y].equals(curr)))
                Flag=true;
            else if(x+5<size && x-1>=0 && (!graph[x+5][y].equals(curr)) && (!graph[x-1][y].equals(curr))) {
                Flag=true;
            }
        }

        else if(direction==3){ // 아래 대각선 확인
            if(x-1>=0 && y-1>=0 && x+5<size && y+5<size){ //전부 범위 내
                if ((!graph[x-1][y-1].equals(curr)) && (!graph[x+5][y+5].equals(curr))){
                    Flag=true;
                }
            }

            else{ // 앞, 뒤 하나라도 out of range
                if(x-1<0 || y-1<0 ){ // 왼쪽이 out of range
                    if(x+5>=size || y+5>=size){ // 5칸 아래도 out of range
                        Flag=true;
                    }
                    else if(x+5<size && y+5<size && !graph[x+5][y+5].equals(curr)) // 5칸 아래는 Valid
                        Flag=true;
                }

                else if(x+5>=size || y+5>=size){ // 오른쪽이 out of range
                    if(x-1<0 || y-1<0){ // 5칸 앞도 out of range
                        Flag=true;
                    }
                    else if(x-1>=0 && y-1>=0 && !graph[x-1][y-1].equals(curr)){
                        Flag=true;
                    }
                }
            }

        }

        else{ // 위로 향하는 대각선 확인

            if(x+1<size && y-1>=0 && x-5>=0 && y+5<size){ //전부 범위 내
                if ((!graph[x+1][y-1].equals(curr)) & (!graph[x-5][y+5].equals(curr))){
                    Flag=true;
                }
            }

            else{ // 앞, 뒤 하나라도 out of range
                if(x+1>=size || y-1<0 ){ // 왼쪽이 out of range
                    if(x-5<0 || y+5>=size){ // 5칸 아래도 out of range
                        Flag=true;
                    }
                    else if(x-5>=0 && y+5<size && !graph[x-5][y+5].equals(curr)) // 5칸 아래는 Valid
                        Flag=true;
                }

                else if(x-5<0 || y+5>=size){ // 오른쪽이 out of range
                    if(x+1>=size || y-1<0){ // 5칸 앞도 out of range
                        Flag=true;
                    }
                    else if(x+1<size && y-1>=0 && !graph[x+1][y-1].equals(curr)){ // 5칸앞은 valid
                        Flag=true;
                    }
                }
            }
        }
        return Flag;
    }
}