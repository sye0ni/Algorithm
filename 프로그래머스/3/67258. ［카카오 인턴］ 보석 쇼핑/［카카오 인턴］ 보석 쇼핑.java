import java.util.*; 

// 투포인터 
// 현재까지 start~end 에 포함된 보석 종류에 따른 갯수 저장
// 새로운 보석(end) 이 이미 포함되어 있는데, start랑 같다면 다른 보석 나올때까지 start++
// 포함되어 있지 않다면 end++; 

class Solution {

    
    public int[] solution(String[] gems) {

        
        Map<String,Integer> jewel=new HashMap<>(); // 0 이면 미포함, 1이면 포함 
        int types=new HashSet<>(Arrays.asList(gems)).size(); // 보석이 총 몇종류인지 
        
        int start=0;
        int end=0;
        int minLen=Integer.MAX_VALUE;
        int minStart=0;
        int minEnd=Integer.MAX_VALUE;
        
        while(end<gems.length) {
            
            jewel.put(gems[end], jewel.getOrDefault(gems[end],0)+1);
            end++;
            
            while(jewel.size()==types) { // 모든 보석을 포함했을경우 -> start 위치 조정 
                if (end-start < minLen) {
                    minLen = end-start;
                    minStart = start;
                }

                jewel.put(gems[start], jewel.get(gems[start])-1);
                if (jewel.get(gems[start]) == 0){
                    jewel.remove(gems[start]);
                }
                start++;
            }
        }

        return new int[]{minStart+1,minStart+minLen};
    }

}