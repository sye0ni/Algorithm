import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 조건 두개를 놓쳐서 고생했다
 * 1. 적이 한 칸씩 이동할 때마다 궁수를 새롭게 배치한 것 -> 한 번 배치하면 끝날때까지 진행
 * 2. 같은 적을 쏠 수 있다 는 조건을 빼먹음
 *
 * 문제 정독 5회 이상 해야할 것 같다 ,,,,,
 */
public class Main {
    static int N,M,D;
    static ArrayList<Integer> comb; // 조합 저장
    static int[][] graph;
    static int[][] savedGraph; // 불변 그래프 (초기 입력 상태 그대로 유지)
    static int Enemy; // 총 적의 수
    static int catchEnemy; // 잡은 적의 수
    static int disappear; // 사라진 적의 수
    static int Max; // catchEnemy 의 최댓값 저장

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    // 입력을 받기 위한 reader 생성
        String[] temp=br.readLine().split(" ");
        N=Integer.parseInt(temp[0]);
        M=Integer.parseInt(temp[1]);
        D=Integer.parseInt(temp[2]);
        graph=new int[N][M];
        savedGraph=new int[N][M];
        catchEnemy=0; // 잡은 적의 수
        disappear=0; // 시간이 지나서 맵 밖으로 사라진 적의 수
        comb=new ArrayList<>();

        for(int i=0;i<N;i++){
            temp=br.readLine().split(" ");
            for(int j=0;j<M;j++) {
                graph[i][j] = Integer.parseInt(temp[j]);
                savedGraph[i][j]=Integer.parseInt(temp[j]);
                if (graph[i][j] == 1) Enemy += 1;
            }
        } // 그래프 입력 , 적의 수 세기

        Max=0;
        combination(0);
        System.out.println(Max);
    }


    static void combination(int cnt){
        if(cnt==3){ // MC3 수행 완료 -> 그 조합으로 게임 한 판 돌려보기
            catchEnemy=0;
            disappear=0;

            for(int i=0;i<N;i++) for(int j=0;j<M;j++) graph[i][j]=savedGraph[i][j]; // 매 반복시마다 그래프 원복 

            while(true){
                ArrayList<int[]> result=bfs();
                // 이번 턴에서 잡을 수 있는 최대 수 적의 좌표가 result 에 저장되어 있음
                // 그 값들을 0으로 바꿔주고 배열 한칸씩 밀어주는 작업 수행하기 (moveEnemy 메서드)
                moveEnemy(result);
                if(catchEnemy+disappear==Enemy) break; // 종료
            }

            if(catchEnemy>Max) Max=catchEnemy;
            return;
        }

        for(int i=cnt;i<M;i++){
            if(comb.size()>0 && comb.get(cnt-1)>=i) continue;
            comb.add(i);
            combination(cnt+1);
            comb.remove(comb.size()-1);
        }

    }

    static ArrayList<int[]> bfs(){
        // comb 배열에 시작 y 좌표 저장되어 있음

        Deque<int[]> queue=new ArrayDeque<>();
        int[] dx={0,-1,0};
        int[] dy={-1,0,1}; // 왼위오
        int currX,currY,xx,yy;
        int[][] depth;
        ArrayList<int[]> catchedEnemy=new ArrayList<>(); // 이번 턴에 잡은 적
        boolean flag;

        for(int i=0;i<3;i++){ // 각각 궁수 좌표를 시작점으로 두고 3회 반복하여 잡을 수 있는 적 찾기
            queue.clear();
            int start=comb.get(i); // 공격을 시작할 y 좌표 (N,y) 에서 시작

            queue.add(new int[]{N,start}); // 시작점 add
            flag=false;
            depth=new int[N+1][M];

            while(!queue.isEmpty()){
                int[] temp=queue.removeFirst();
                xx=temp[0];
                yy=temp[1];

                if(depth[xx][yy]==D) break; // 최대 거리만큼만 공격 가능

                for(int j=0;j<3;j++){
                    currX=xx+dx[j];
                    currY=yy+dy[j];

                    if(currX<0 || currX>=N || currY<0 || currY>=M) continue;

                    if(graph[currX][currY]==1){
                        catchedEnemy.add(new int[]{currX,currY});
                        flag=true;
                        break;
                    } // 적 하나 잡으면 끝남

                    queue.addLast(new int[]{currX,currY});
                    depth[currX][currY]=depth[xx][yy]+1;
                }

                if(flag) break; // 적 하나라도 잡았으면 얘는 끝
            }
        }

        return catchedEnemy;
    }

    static void moveEnemy(ArrayList<int[]> tempEnemy){ // 잡은 적들 값을 0으로 바꿔주고, 배열을 한칸씩 아래로 밀기 
        // catchEnemy + , disappearEnemy -

        for(int i=0;i<tempEnemy.size();i++){
            int[] pop=tempEnemy.get(i);
            if(graph[pop[0]][pop[1]]==1) {
                graph[pop[0]][pop[1]]=0;
                catchEnemy+=1;
            } // 동일한 적을 잡을 수도 있으니까
        } // 잡은 적들은 기존 그래프에서 0으로 바꾸기

        for(int j=0;j<M;j++){
            if(graph[N-1][j]==1) disappear+=1;
        } // 사라질 적의 수 add

        for(int i=N-1;i>0;i--){
            for(int j=0;j<M;j++){
                graph[i][j]=graph[i-1][j];
            }
        } // 한칸씩 아래로 밀기

        for(int j=0;j<M;j++) graph[0][j]=0; // 1행 0으로
    }
}