import java.util.*;

// dp[i] : i번째 원소까지의 최댓값 

class Solution {
    public int solution(int sticker[]) {

        int sum=0;
        
        if(sticker.length==1) sum=sticker[0];
        else if(sticker.length==2) sum=Math.max(sticker[0],sticker[1]);
        else{
            
            int[] dp=new int[sticker.length-1];
            // 첫 번째 원소 선택
            dp[0]=sticker[0];
            dp[1]=Math.max(dp[0],sticker[1]);
            for(int i=2;i<sticker.length-1;i++) {
                dp[i]=Math.max(dp[i-1],dp[i-2]+sticker[i]);
            }
            sum=dp[dp.length-1];
            
            Arrays.fill(dp,0);
            // 첫 번째 원소 미선택
            dp[0]=sticker[1];
            dp[1]=Math.max(dp[0],sticker[2]);
            for(int i=3;i<sticker.length;i++) {
                dp[i-1]=Math.max(dp[i-2],dp[i-3]+sticker[i]);
            }
            
            sum=Math.max(sum,dp[dp.length-1]);
        }

        return sum;
    }
}