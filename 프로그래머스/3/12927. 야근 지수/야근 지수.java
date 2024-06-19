import java.util.*;


class Solution {
    
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>(new Comparator<Integer>()          {
            @Override 
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
        
        for(int i=0;i<works.length;i++) {
            pq.add(works[i]);
        }
            
        int pop;
        for(int i=0;i<n;i++) {
            if(pq.size()==0) break;
            pop=pq.poll();
            if(pop==1) continue;
            pq.add(pop-1);
        }
        
        while(!pq.isEmpty()){
            pop=pq.poll();
            answer+=pop*pop;
        }
        
        return answer;
    }
}