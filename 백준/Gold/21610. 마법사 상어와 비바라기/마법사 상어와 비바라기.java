import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 	매 반복시, 구름이 위치한 곳을 찾아 저장
 *
 */
public class Main {

    static int[][] graph;
    static int N;
    static Deque<int[]> move; // 움직임 명령 저장
    static int[][] directions;
    static List<int[]> clouds;
    static List<int[]> water;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int M;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken()); // 그래프 크기
        M=Integer.parseInt(st.nextToken());
        graph=new int[N][N];
        move=new ArrayDeque<>();
        clouds=new ArrayList<>();
        water=new ArrayList<>();
        directions= new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // 1~8 방향 지정


        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            move.add(new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()) }); // 움직임 정보 저장
        }

        // 처음 구름 4개 저장
        clouds.add(new int[]{N-1,0});
        clouds.add(new int[]{N-1,1});
        clouds.add(new int[]{N-2,0});
        clouds.add(new int[]{N-2,1});

        magic();
        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=graph[i][j];
            }
        }
        System.out.println(sum);
    }

    static void magic(){
        int[] pop; // 방향, 횟수 저장
        int dir,cnt;
        int[] temp;
        int x,y,xx,yy;
        int[] currDir;
        int[] dx=new int[]{-1,-1,1,1};
        int[] dy=new int[]{-1,1,-1,1}; // 대각선
        int cntWater;
        boolean[][] visited=new boolean[N][N];

        while(move.size()>0){

            pop=move.removeFirst();
            dir=pop[0];
            cnt=pop[1];
            currDir=directions[dir];
            visited=new boolean[N][N];

            // 구름 이동 , 물의 양 + 1
            for(int i=0;i<clouds.size();i++){
                temp=clouds.get(i);
                x=temp[0];
                y=temp[1]; // 이동시킬 구름

                for(int j=0;j<cnt;j++){
                    x+=currDir[0];
                    y+=currDir[1];
                    if(x==N) x=0;
                    if(y==N) y=0;
                    if(x==-1) x=N-1;
                    if(y==-1) y=N-1;
                }
                graph[x][y]+=1;
                water.add(new int[]{x,y});
                visited[x][y]=true; // 구름이 이동하여 물이 증가한 위치
            }

            // 물이 증가한 위치에 대해 물복사버그
            for(int i=0;i<water.size();i++){
                x=water.get(i)[0];
                y=water.get(i)[1];
                cntWater=0;

                for(int j=0;j<4;j++){ // 대각선 위치 살피며 물이 있는 칸 찾기
                    xx=x+dx[j];
                    yy=y+dy[j];
                    if(xx<0 || xx>=N || yy<0 || yy>=N) continue;
                    if(graph[xx][yy]>0){
                        cntWater+=1;
                    }
                }
                graph[x][y]+=cntWater;
               // visited[x][y]=true;
            }

            clouds.clear();
            water.clear();
            // 구름이 생기는 칸 찾기 (2 이상), 물의 양 - 2 , 방금 구름이 사라진 칸이 아니어야함
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(graph[i][j]>=2) {
                        if(!visited[i][j]){
                            graph[i][j]-=2;
                            clouds.add(new int[]{i,j});
                        }
                    }
                }
            }

//            // 구름의 이동 & 버그 완료
//            for(int i=0;i<N;i++){
//                for(int j=0;j<N;j++){
//                    System.out.print(graph[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

        }
    }

}