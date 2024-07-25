// 그리디 
// 끝자리가 5면 앞자리를 봐서 올림 혹은 버림 
// 끝자리가 5보다 크면 올림, 5보다 작으면 버림 

class Solution {
    public int solution(int storey) {
        int answer=0;
        
        while (storey>0) { // 0으로 만들기 
            int digit = storey%10; // 끝자리 숫자  
            storey/=10; 
            
            if (digit>5) { // 올림 
                answer+=(10-digit);
                storey++; 
            } else if (digit==5 && (storey%10>=5)) { // 올림 
                answer+=(10 - digit);
                storey++; 
            } else { // 버림 
                answer+=digit;
            }
        }
        
        return answer;
    }
}
