import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int len;
        int r,q;
        int pre=0;
        
        for(int i=0;i<stations.length;i++) {
            len=stations[i]-w-pre-1;
            pre=stations[i]+w;
            
            if(len<=0) continue;
            r=len%(w*2+1);
            q=len/(w*2+1);
            if(r>0) q++;
            answer+=q;
        }
        
        // 맨 뒤 처리 
        len=n-pre;
        
        if(len>0) {
            r=len%(w*2+1);
            q=len/(w*2+1);
            if(r>0) q++;
            answer+=q;
        }
        
        return answer;
    }
}