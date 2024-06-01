import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets,(o1,o2)->{
            return o1[1]-o2[1];
        });
        
        int end=0;
        int answer = 0;
        
        for(int i=0;i<targets.length;i++) {
            if(targets[i][0]>=end) {
                end=targets[i][1];
                answer++;
            }
        
        }

        
        return answer;
    }
}