import java.util.*;

// dp 
// 2*1 -> 1 
// 2*2 -> 1(2*1꺼) + 1 -> 2 
// 2*3 -> 2(2*2꺼) + 1(2*1) -> 3 
// 2*4 -> 3(2*3꺼) + 2(2*2) -> 5

class Solution {
    public int solution(int n) {

        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++) {
            dp[i]=(dp[i-1]+dp[i-2])%1000000007;
        }
        
        
        return dp[n];
    }
}