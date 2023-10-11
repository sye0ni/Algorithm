import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static String[][] graph,copyGraph,finGraph;
    static ArrayList<int[]> cctv;
    static ArrayList<Integer> cctvType; // 1-4 cctv 저장
    static List<int[]> cctv5; // 5번 cctv 저장

    static int cctvCnt=0;
    static int n,m;
    static int Max=0;
    static int[] perm;

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    // 입력을 받기 위한 reader 생성
        String[] temp=br.readLine().split(" ");
        n=Integer.parseInt(temp[0]);
        m=Integer.parseInt(temp[1]);

        graph=new String[n][m];
        finGraph=new String[n][m];
        cctv=new ArrayList<>(); // 1-4 cctv 위치 및 종류 저장
        cctvType=new ArrayList<>();
        cctv5=new ArrayList<>(); // 5번 cctv 따로 저장

        // 기존 그래프 복사
        copyGraph=new String[n][m];

        for(int i=0;i<n;i++) {
            temp=br.readLine().split(" ");
            for(int j=0;j<m;j++) {
                graph[i][j]=temp[j];
                copyGraph[i][j]=temp[j];
                finGraph[i][j]=temp[j];
                if("1234".contains(graph[i][j])) {
                    cctv.add(new int[] {i,j}); // 해당 cctv 번호에 맞는 cctv 배열에 추가
                    cctvCnt++; // cctv 수 세기 (1~4번만) 
                    cctvType.add(Integer.parseInt(graph[i][j]));
                } // cctv 등록하기 (1-4 cctv만)

                if(graph[i][j].equals("5")) cctv5.add(new int[] {i,j}); // 5번 cctv 는 따로 저장 
            }
        } // 그래프 입력받기 및 cctv 등록


        if(cctvCnt!=0) {  // 1-4 cctv가 1개 이상일 때 
            perm=new int[cctvCnt];
            permutation(0);
            for(int i=0;i<n;i++) for(int j=0;j<m;j++) copyGraph[i][j]=finGraph[i][j];
        }

        else{ // 1-4 cctv 가 없음 -> 5번 있으면 걔만 설치 
            cctv5();
        }

        int cnt=0; // 사각지대 수 

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(copyGraph[i][j].equals("0")) cnt+=1;
            }
        }

        System.out.println(cnt);
    }

    // cnt번 카메라의 방향 찾기 ; 중복순열
    static void permutation(int cnt) {

        if(cnt==cctvCnt) { // 1~4번 카메라들 방향을 모두 정해주었다면 
            int monitorCnt=Monitor(); // 그래프에 그려보며 감시 범위 리턴
            if(monitorCnt>Max) { 
                Max=monitorCnt;
                for(int i=0;i<n;i++) for(int j=0;j<m;j++)  finGraph[i][j]=copyGraph[i][j];
                // 최댓값이 바뀔때의 상태 저장하기
            }
            return;
        }

        for(int i=0;i<4;i++) {
            if(cctvType.get(cnt)==2) {
                if(i==2 || i==3) continue;
            } // 2번 카메라는 두개 방향만 봐야함
            perm[cnt]=i;
            permutation(cnt+1);
        }
    }

    
    // 기존 입력 받은 그래프에서, 1~4번 cctv의 방향에 따라 cctv를 설치하고 
    // 5번 cctv 가 있다면 걔까지 설치해서 전체 감시 범위를 카운트한 값 반환 
    static int Monitor() {

        int cnt=0;

        // 기존 그래프 복사 
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                copyGraph[i][j]=graph[i][j];
            }
        }

        for(int i=0;i<perm.length;i++) { // 순열로 뽑은 값들 (방향) 하나씩 확인 , 1~4번 cctv 모두 설치 
            switch(cctvType.get(i)) { // 카메라 종류에 따라 다름 
                case(1):
                    cctv1(cctv.get(i),perm[i]); // cctv 좌표와 방향 전달
                    break;
                case(2):
                    cctv2(cctv.get(i),perm[i]);
                    break;
                case(3):
                    cctv3(cctv.get(i),perm[i]);
                    break;
                case(4):
                    cctv4(cctv.get(i),perm[i]);
                    break;
            }
        }

        // 5번 cctv 설치
        cctv5();

        // 감시 영역 count 하여 반환
        for(int i=0;i<n;i++)  for(int j=0;j<m;j++)  if(copyGraph[i][j].equals("#")) cnt+=1;

        return cnt;

    }

    static void cctv1(int[] pos,int direction){ // 1번 cctv 방향에 따라 처리 

        int x=pos[0];
        int y=pos[1];

        if(direction==0) { // 상
            search(x, y,"up");
        }
        else if(direction==1) { // 하
            search(x, y,"down");
        }
        else if(direction==2) { // 좌
            search(x, y,"left");
        }
        else { // 우
            search(x, y,"right");
        }

    }
    static void cctv2(int[] pos,int direction){ // 2번 cctv 방향에 따라 처리 

        int x=pos[0];
        int y=pos[1];

        if(direction==0) { // 상하
            search(x, y,"up");
            search(x, y,"down");
        }
        else if(direction==1) { // 좌우
            search(x, y,"left");
            search(x, y,"right");
        }

    }
    static void cctv3(int[] pos,int direction){ // 3번 cctv 방향에 따라 처리 

        int x=pos[0];
        int y=pos[1];

        if(direction==0) { // 상우
            search(x, y, "up");
            search(x, y, "right");
        }
        else if(direction==1) { // 하우
            search(x, y,"down");
            search(x, y,"right");
        }
        else if(direction==2) { // 좌하
            search(x, y,"left");
            search(x, y,"down");
        }
        else { // 좌상
            search(x, y,"up");
            search(x, y,"left");
        }


    }
    static void cctv4(int[] pos,int direction){ // 4번 cctv 방향에 따라 처리 

        int x=pos[0];
        int y=pos[1];

        if(direction==0) { // 상
            search(x, y,"up");
            search(x, y,"right");
            search(x, y,"left");
        }
        else if(direction==1) { // 하
            search(x, y,"down");
            search(x, y,"right");
            search(x, y,"left");
        }
        else if(direction==2) { // 좌
            search(x, y, "left");
            search(x, y, "up");
            search(x, y, "down");
        }
        else { // 우
            search(x, y,"up");
            search(x, y, "right");
            search(x, y, "down");
        }

    }
    static void cctv5(){ // 5번 cctv 들 전부 처리 

        for(int i=0;i<cctv5.size();i++) {
            int[] pop=cctv5.get(i);
            int x=pop[0];
            int y=pop[1];
            //      System.out.println(x+","+y);
            search(x, y, "up");
            search(x, y, "down");
            search(x, y, "left");
            search(x, y, "right");
        }
    }

    static void search (int x,int y,String dir){ // 방향에 따라 탐색해서 # 로 바꾸기 

        if(dir.equals("up")) {
            for(int i=x-1;i>=0;i--) {
                if(i<0) break;
                if(copyGraph[i][y].equals("6")) break;
                if(copyGraph[i][y].equals("0")) copyGraph[i][y]="#"; // 빈칸을 감시칸으로 바꾸기
            }
        }
        else if(dir.equals("down")) {
            for(int i=x+1;i<n;i++) {
                if(i==n) break;
                if(copyGraph[i][y].equals("6")) break;
                if(copyGraph[i][y].equals("0")) copyGraph[i][y]="#"; // 빈칸을 감시칸으로 바꾸기
            }
        }
        else if(dir.equals("left")) {
            for(int j=y-1;j>=0;j--) {
                if(j<0) break;
                if(copyGraph[x][j].equals("6")) break;
                if(copyGraph[x][j].equals("0")) copyGraph[x][j]="#"; // 빈칸을 감시칸으로 바꾸기
            }
        }
        else{
            for(int j=y+1;j<m;j++) {
                if(j==m) break;
                if(copyGraph[x][j].equals("6")) break;
                if(copyGraph[x][j].equals("0")) copyGraph[x][j]="#"; // 빈칸을 감시칸으로 바꾸기
            }
        }
    }
}