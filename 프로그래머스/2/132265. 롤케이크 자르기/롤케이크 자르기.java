import java.util.*; 

// 동일한 가짓수의 토핑이 올라가면 공평
// 철수가 모든 토핑을 다 가지고, 하나씩 빼면서 종류 수가 같아질때 answer++ 

class Solution {

    public int solution(int[] topping) {
        int answer=0;
        
        Map<Integer,Integer> chulsoo=new HashMap<>();
        Map<Integer,Integer> brother=new HashMap<>(); 
        
        for(int i=0;i<topping.length;i++) {
            chulsoo.put(topping[i],chulsoo.getOrDefault(topping[i],0)+1);
        }
        
        int count;
        for(int i=0;i<topping.length;i++) {
            count=chulsoo.get(topping[i]);
            chulsoo.put(topping[i],count-1);
            
            if(count==1) chulsoo.remove(topping[i]);
            
            brother.put(topping[i],brother.getOrDefault(brother.get(topping[i]),0)+1);
            
            if(chulsoo.size()==brother.size()) answer++;
        }

        
        return answer;
    }
    
    
}