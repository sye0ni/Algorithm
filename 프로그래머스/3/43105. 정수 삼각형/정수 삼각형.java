import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int maxLen=triangle[triangle.length-1].length;
        int[][] dp=new int[maxLen][maxLen]; 
        
        dp[0][0]=triangle[0][0];

        for(int i=1;i<triangle.length;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0 || j==i) {
                    if(j==0) dp[i][j]=triangle[i][j]+dp[i-1][0];
                    else dp[i][j]=triangle[i][j]+dp[i-1][j-1];
                } 
                else {
                    dp[i][j]=Math.max(dp[i-1][j-1],dp[i-1][j])+triangle[i][j];
                }
                answer=Math.max(answer,dp[i][j]);
            }
        }
        
        return answer;
    }
}