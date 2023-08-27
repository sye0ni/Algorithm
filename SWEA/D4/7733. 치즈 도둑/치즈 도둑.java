import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[][] graph;
    static int N,Max;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;
        int T = Integer.parseInt(br.readLine()); // tc 수
        StringBuilder sb=new StringBuilder();

        for(int t=1;t<=T;t++){
            N=Integer.parseInt(br.readLine());
            graph=new int[N][N];
            visited=new boolean[N][N];
            Max=0;
            for(int i=0;i<N;i++){
                temp=br.readLine().split(" ");
                for(int j=0;j<N;j++) graph[i][j]=Integer.parseInt(temp[j]);
            }

            for(int d=0;d<=100;d++){
                eatCheese(d); // d 번 치즈 먹기 (-1로)
                int cnt=0;
                visited=new boolean[N][N]; // 매 반복마다 초기화

                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        if(graph[i][j]!=-1 && !visited[i][j]){
                            visited[i][j]=true;
                            dfs(i,j);
                            cnt+=1;
                        }
                    }
                }
                if(cnt>Max) Max=cnt;
            }
            sb.append("#"+t+" "+Max+"\n");
        }
        System.out.println(sb);
    }
    
    static void eatCheese(int n){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(graph[i][j]==n) graph[i][j]=-1;
            }
        }
    } // 치즈 먹어

    static void dfs(int x,int y){ // [x,y] 에서 시작하여 dfs

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1}; // 상하좌우
        int currX,currY;

        for(int i=0;i<4;i++){
            currX=x+dx[i];
            currY=y+dy[i];

            if(currX<0 || currX>=N || currY<0 || currY>=N) continue;
            if(visited[currX][currY]) continue;
            if(graph[currX][currY]==-1) continue; // 이미 먹힌 치즈

            visited[currX][currY]=true;
            dfs(currX,currY);
        }
    }
}