import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int total;
    static int[][] graph;
    static int[] purifier; // 공기 청정기 위치 저장
    static int r,c;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp=br.readLine().split(" ");
        r=Integer.parseInt(temp[0]);
        c=Integer.parseInt(temp[1]);
        int t=Integer.parseInt(temp[2]);

        graph=new int[r][c];
        purifier=new int[2];

        for(int i=0;i<r;i++){
            temp=br.readLine().split(" ");
            for(int j=0;j<c;j++){
                graph[i][j]=Integer.parseInt(temp[j]);
                if(graph[i][j]==-1 && graph[i-1][j]==-1) {
                    purifier[0]=i-1;
                    purifier[1]=i;
                } // 공청 위치 저장 (y좌표는 무조건 0)
                if(graph[i][j]>0) total+=graph[i][j]; // 미세먼지 양 저장
            }
        } // 전체 그래프 , 공기청정기 위치, 미세먼지 총량 저장

        for(int i=0;i<t;i++){ // t초간 반복
            spread();
            operate();
        }

        System.out.println(total);
    }

    static void spread(){ // 미세먼지의 확산
        /// 초기 상태 값에서 변화하는것이므로 새로운 배열 생성해야함
        int[][] copy=new int[r][c];
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1}; // 상하좌우
        int currX,currY;
        int cnt=0;

        // to do : 범위를 벗어나거나 공청이 있으면 확산하지 않음
        // 기존 그래프를 한칸씩 탐색하며 확산 ..
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(graph[i][j]>0) {
                    cnt=0;
                    // 4방 탐색
                    for(int k=0;k<4;k++){
                        currX=i+dx[k];
                        currY=j+dy[k]; // 확산할 좌표
                        if(currX<0 || currX>=r || currY<0 || currY>=c) continue; // 범위 벗어나면
                        if(graph[currX][currY]==-1) continue; // 공청 있으면
                        cnt++; // 확산한 방향 수 세기
                        copy[currX][currY]+=graph[i][j]/5; // 확산시키기
                    }
                    copy[i][j]=graph[i][j]-cnt*(graph[i][j]/5)+copy[i][j]; // 확산한만큼 겂 빼주기

                } // 확산시키기
                if(graph[i][j]==-1) copy[i][j]=-1;
            }
        }
        graph=copy;

    }

    static void operate(){
        // todo: 공기청정기 작동
        // 반시계, 시계 따로 처리함

        ///////////////////// 반시계 순환
        int x=purifier[0];

        // 좌->우 밀기 (공기 청정기의 다음 위치부터 밀기)
        int temp=graph[x][1];
        int temp2=0;

        for(int j=2;j<c;j++){
            temp2=graph[x][j]; // 밀릴 칸의 숫자 저장
            graph[x][j]=temp;
            temp=temp2;
        }

        graph[x][1]=0; // 이 자리는 무조건 0이 됨

        // 위로 올라갈 일 없으면 여기서 종료
        if(x>0){
            // 아래->위로 밀기
            for(int i=x-1;i>=0;i--){
                temp2=graph[i][c-1]; // 밀릴 값을 저장
                graph[i][c-1]=temp; // 밀어
                temp=temp2;
            }

            // 우 -> 좌 밀기
            for(int j=c-2;j>=0;j--){
                temp2=graph[0][j]; // 밀릴 값 저장
                graph[0][j]=temp;
                temp=temp2;
            }

            // 위->아래 밀기 (공청 있는 곳까지 밀기)
            for(int i=1;i<x;i++){
                temp2=graph[i][0];
                graph[i][0]=temp;
                temp=temp2;
            }


        }

        if(temp!=0){
            total-=temp;
        }

/*        System.out.println("반시계 회전");
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();*/

        ////////////////////////// 시계 순환
        x=purifier[1];

        // 좌->우 밀기 (공기 청정기의 다음 위치부터 밀기)

        temp=graph[x][1];

        for(int j=2;j<c;j++){
            temp2=graph[x][j]; // 밀릴 칸의 숫자 저장
            graph[x][j]=temp;
            temp=temp2;
        }

        graph[x][1]=0; // 이 자리는 무조건 0이 됨

        // 아래로 내려갈 일 없으면 여기서 종료

        if(x<r-1){
            // 위->아래로 밀기
            for(int i=x+1;i<r;i++){
                temp2=graph[i][c-1]; // 밀릴 값을 저장
                graph[i][c-1]=temp;
                temp=temp2;
            }

            // 우 -> 좌 밀기
            for(int j=c-2;j>=0;j--){
                temp2=graph[r-1][j]; // 밀릴 값 저장
                graph[r-1][j]=temp;
                temp=temp2;
            }

            // 아래->위 밀기 (공청 있는 곳까지 밀기)
            for(int i=r-2;i>x;i--){
                temp2=graph[i][0];
                graph[i][0]=temp;
                temp=temp2;
            }

        }

        if(temp!=0){
            total-=temp;
        }
/*
        System.out.println("시계회전");
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();*/
    }

}