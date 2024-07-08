import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] graph=new int[n][m];
        int[][] dp=new int[n][m];
        
        for(int i=0;i<puddles.length;i++) {
            graph[puddles[i][1]-1][puddles[i][0]-1]=1; // 물에 잠김 
        }
        
        int idx=n;
        for(int i=1;i<n;i++){
            if(graph[i][0]==1) {
                idx=i;
                break; 
            }
        } 
        
        for(int i=1;i<idx;i++) dp[i][0]=1;
        
        idx=m;
        for(int i=1;i<m;i++){
            if(graph[0][i]==1) {
                idx=i;
                break; 
            }
        } 

        for(int i=1;i<idx;i++) dp[0][i]=1;
        
        int left,up;
        int dpLeft,dpUp;
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++) {
                if(graph[i][j]==1) continue; // 해당 위치가 물에 잠겨 있다면 
                
                // 왼쪽 
                left=graph[i][j-1];
                if(left==1) dpLeft=0;
                else dpLeft=dp[i][j-1];
                
                // 위쪽 
                up=graph[i-1][j];
                if(up==1) dpUp=0;
                else dpUp=dp[i-1][j];
                
                dp[i][j]=(dpLeft+dpUp)%1000000007;
            }
        }
        
        return dp[n-1][m-1];
    }
}