import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long left=1;
        long mid=0;
        long right=(long)n*(long)times[times.length-1];
        long cnt;
        
        while(left<=right) {
            mid=(left+right)/2;
            
            cnt=0;
            for(int i=0;i<times.length;i++) {
                cnt+=mid/times[i];
            }
            
            if(cnt>=n) {
                answer=mid;
                right=mid-1;
            } else {
                left=mid+1;
            }
        }
        
        
        return answer;
    }
}