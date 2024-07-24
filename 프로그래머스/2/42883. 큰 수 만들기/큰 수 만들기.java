import java.util.*;

class Solution {
    
    String answer="";
    int goal;
    int len;
    
    public String solution(String number, int k) {
        len=number.length();
        goal=len-k;
        int idx=-1;
        
        for(int i=0;i<goal;i++) {
            idx=findNthNumber(idx,number);
        }
        
        return answer;
    }
    
    // index+1 번 부터 number 문자열을 보면서 
    // goal - answer의길이 -> 추가해야하는 숫자 개수 
    int findNthNumber(int index,String number) {
        int maxIdx=-1;
        int max=-1;
        int remain=goal-answer.length(); // 내 뒤로 remain-1 개 있어야함
        
        for(int i=index+1;i<=len-remain;i++) {
            if(number.charAt(i)-'0'>max){
                max=number.charAt(i)-'0';
                maxIdx=i;
            }
        }
        
        answer+=number.substring(maxIdx,maxIdx+1);
        return maxIdx;
    }
}