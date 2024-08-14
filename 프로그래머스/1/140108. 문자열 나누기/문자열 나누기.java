import java.util.*;

class Solution {
    public int solution(String s) {
        int cnt = 0;
        
        int same=0;
        int diff=0;
        int idx=0;
        char ch;
        
        while(true) {
            
            if(s.length()==0) break; // 종료 
            
            ch=s.charAt(0);
            same++;
            
            for(int i=1;i<s.length();i++) { // same==diff 가 되는 순간 종료 
                if(ch!=s.charAt(i)) diff++;
                else same++;
                
                if(diff==same) {
                    idx=i;
                    break;
                }
            }
            
            if(same==diff) {
                s=s.substring(idx+1);
            } else { // 더 이상 읽을 글자가 없음
                s="";
            }
            cnt++;
        }
        
        
        return cnt;
    }
}