import java.util.*;


class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int start=0,end=0;
        int minStart=0;
        int minEnd=Integer.MAX_VALUE;
        long sum=sequence[0];
        
        while(true) {
            
            if(start==sequence.length || end==sequence.length) break;
            
            if(sum<k) {
                end++;
                if(end==sequence.length) break;
                sum+=sequence[end];
            } else if(sum==k) {
                if(minEnd-minStart > end-start) {
                    minEnd=end;
                    minStart=start;
                }
                sum-=sequence[start];
                start++;
            } else {
                sum-=sequence[start];
                start++;
            }
        }
        
        return new int[]{minStart,minEnd};
    }
}