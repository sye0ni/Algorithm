import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int q,r;
        q=s/n;
        r=s%n;
        
        if(q==0) {
            answer=new int[1];
            answer[0]=-1;
        }
        else{
            Arrays.fill(answer,q);
            for(int i=0;i<r;i++) {
                answer[n-1-i]+=1;
            }
        }
        return answer;
    }
}