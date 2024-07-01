import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes,(o1,o2)->{
            if(o1[1]==o2[1]){
                return o1[0]-o2[0];
            }
            return o1[1]-o2[1];
        });
        
        int pre=routes[0][1];
        answer=1;
        for(int i=1;i<routes.length;i++) {
            // System.out.println(routes[i][0]+"->"+routes[i][1]);
            if(routes[i][0]<=pre && routes[i][1]>=pre) continue;
            pre=routes[i][1];
            answer++;
        }
        
        return answer;
    }
}