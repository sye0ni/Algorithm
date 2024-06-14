import java.util.*;


class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        boolean[][] graph=new boolean[n+1][n+1];
        
        int from,to;
        
        for(int i=0;i<results.length;i++) {
            from=results[i][0];
            to=results[i][1];
            graph[from][to]=true;
        }

        // floyd-warshall 
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    if(graph[i][k]&&graph[k][j]){
                        graph[i][j]=true;
                    }
                }
            }
        }
        
        int cnt;
        for(int i=1;i<=n;i++) {
            cnt=0;
            for(int j=1;j<=n;j++) {
                if(i!=j){
                    if(graph[i][j]||graph[j][i]) cnt++;
                }
            }
            if(cnt==n-1) answer++;
        }
          
        return answer;
    }
}