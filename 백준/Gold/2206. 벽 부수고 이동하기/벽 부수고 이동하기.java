import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 일반적인 최단 경로를 구하는 bfs에 여러 조건을 추가해야한다
 * 벽을 부수고 온 경로와 벽을 부수지 않고 온 경로를 구분
 */
public class Main {

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        char[][] graph=new char[N][M];
        String str;

        for(int i=0;i<N;i++){
            str=br.readLine();
            for(int j=0;j<M;j++){
                graph[i][j]=str.charAt(j);
            }
        } // 입력 완료

        Deque<int[]> queue=new ArrayDeque<>();
        int[][] depth=new int[N][M]; // 0 이면 not visitied 라는 뜻
        boolean[][] visited=new boolean[N][M]; // 일반 경로의 방문 체크
        boolean[][] broken=new boolean[N][M]; // 벽을 부순 경로의 방문 체크
        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};

        int shortest=Integer.MAX_VALUE;

        depth[0][0]=1;
        queue.addLast(new int[]{0,0,0,1});
        visited[0][0]=true;

        int[] pop;
        int x,y;

        while(!queue.isEmpty()){
            pop=queue.removeFirst();

            for(int i=0;i<4;i++){
                x=pop[0]+dx[i];
                y=pop[1]+dy[i];

                if(x<0 || x>=N || y<0 || y>=M) continue;
                if(visited[x][y]) continue; // 정상 경로로 이미 방문
                if(x==N-1 && y==M-1){
                    if(pop[3]+1<shortest) {
                        shortest=pop[3]+1;
                    } // 최단경로 갱신
                }
                if(pop[2]==1) { // 벽을 부수고 온 경로라면 ?
                    if(visited[x][y]||broken[x][y]) continue;
                    else {
                        if(graph[x][y]=='0'){ // 뚫린 길만 갈 수 있음
                            broken[x][y]=true;
                            queue.add(new int[]{x,y,1,pop[3]+1});
                        }
                    }
                }
                else if(pop[2]==0){ // 일반 경로라면 ?
                    if(graph[x][y]=='1'){ // 벽 부수기 가능
                        broken[x][y]=true;
                        queue.add(new int[]{x,y,1,pop[3]+1});
                    }
                    else{
                        visited[x][y]=true;
                        queue.add(new int[]{x,y,0,pop[3]+1});
                    }
                }


            }
        }

        if(shortest==Integer.MAX_VALUE) shortest=-1;
        if(N==1 && M==1 && graph[0][0]=='0') shortest=1;
        System.out.println(shortest);

    }
}