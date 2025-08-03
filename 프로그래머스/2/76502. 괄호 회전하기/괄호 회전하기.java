import java.util.*;

class Solution {
    
    static int answer  = 0;
    static Deque<Character> stack=new ArrayDeque<>();
    static String left="[({";
    static Map<Character,Character> pairs = new HashMap<>();
    
    public int solution(String s) {
        
        pairs.put('{','}');
        pairs.put('[',']');
        pairs.put('(',')');
        
        for(int i=0;i<s.length();i++) {
            stack.clear();
            checkString(s,i);
        }
        
        return answer;
    }
    
    void checkString(String str, int idx) {
        
        int currIdx;
        boolean flag = true;
        boolean midFlag = false; 
        char pop;
        char currChr;
        
        for(int i=idx; i< idx+str.length() ; i++){
            if(!flag) return;    // 더 볼 필요 없음 
            
            currIdx = i%str.length();
            currChr = str.charAt(currIdx);
            midFlag = false;
            
            for(int j=0;j<3;j++) {  // 왼쪽 괄호인지 확인 
                if(currChr == left.charAt(j)) {
                    stack.addLast(left.charAt(j));
                    midFlag = true; 
                }
            }
            
            if(midFlag) continue;
            
            if(stack.isEmpty()) {
                flag=false;
                continue;
            }
            
            pop=stack.removeLast();
            if(pairs.get(pop) != currChr) {
                flag=false;
            }
            
        }
        
        
        if(flag && stack.isEmpty()) answer++;
    }
    
    
    
}