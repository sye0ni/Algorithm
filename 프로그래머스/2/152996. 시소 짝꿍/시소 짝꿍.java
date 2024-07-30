import java.util.*;

// 큰수에서 작은수 나눴을때 1, 1.5, 2, 4/3 중에 하나면 됨 

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Map<Double,Integer> map=new HashMap<>();
        
        for(int i=0;i<weights.length;i++) {
            map.put((double)weights[i],map.getOrDefault((double)weights[i],0)+1);
        }
        
        double curr,temp;
        long currVal;
        List<Double> keys=new ArrayList(map.keySet());
        
        for(int i=0;i<keys.size();i++) {
            curr=keys.get(i);
            currVal=map.get(curr);
            
            if(currVal>1) answer+=currVal*(currVal-1)/2;
            temp=curr*1.5;
            answer+=(map.getOrDefault(temp,0))*currVal;
            temp=curr*2;
            answer+=(map.getOrDefault(temp,0))*currVal;
            temp=(curr/3)*4;
            answer+=(map.getOrDefault(temp,0))*currVal;
        }
        
        return answer;
    }
}