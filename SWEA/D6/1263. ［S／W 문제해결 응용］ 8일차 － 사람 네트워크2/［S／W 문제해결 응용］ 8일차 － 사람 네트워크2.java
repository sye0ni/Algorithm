import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워샬
 *
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t=Integer.parseInt(br.readLine()); // testcase
        int[][] graph;
        int n;
        StringBuilder sb=new StringBuilder();
        int inf=99999999;

        for(int T=1;T<=t;T++){
            st=new StringTokenizer(br.readLine()," ");
            n=Integer.parseInt(st.nextToken()); // 사람 수
            graph=new int[n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    graph[i][j]=Integer.parseInt(st.nextToken());
                    if(i==j) continue;
                    if(graph[i][j]==0) {
                        graph[i][j]=inf;
                    }
                }
            } // 그래프 생성

            // 경 출 도
            for(int k=0;k<n;k++){
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        if(k==i || k==j || i==j) continue;
                        if(graph[i][k]==inf || graph[k][j]==inf) continue;
                        graph[i][j]=Math.min(graph[i][k]+graph[k][j],graph[i][j]);
                    }
                }
            }

            int MinIdx=0;
            int min=Integer.MAX_VALUE;
            int cnt=0;
            for(int j=0;j<n;j++){
                cnt=0;
                for(int i=0;i<n;i++){
                    if(graph[i][j]==inf) continue;
                    cnt+=graph[i][j];
                }
                min=Math.min(min,cnt);
            }

            sb.append("#"+T+" "+min+"\n");

        }

        System.out.println(sb);
    }
}