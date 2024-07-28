import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String,Integer> map=new HashMap<>();
        
        for(int i=0;i<clothes.length;i++) {
            map.put(clothes[i][1],map.getOrDefault(clothes[i][1],0)+1);
        }
        
        Set<String> keys=map.keySet();
        List<String> keyList=new ArrayList<>(keys);
        
        for(int i=0;i<keyList.size();i++){
            // System.out.println(keyList.get(i));
            // System.out.println(map.get(keyList.get(i)));
            answer*=(map.get(keyList.get(i))+1);
        }
        
        
        return answer-1;
    }
}