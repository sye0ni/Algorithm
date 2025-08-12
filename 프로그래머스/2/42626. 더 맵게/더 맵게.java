import java.util.*;

class Solution {
    
    static int answer=0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        
        for(int i=0;i<scoville.length;i++)
            pq.add(scoville[i]);
        
        shaking(K);
        
        
        return answer;
    }
    
    void shaking(int K) {
        
        int a,b; 
        
        
        // 꺼낸 원소가 k 이상이거나 , 큐가 비었거나 
        while(true) {
            if(pq.size()==0) {  // 큐가 비었다면 실패 
                answer=-1;
                break; 
            }
            
            if(pq.peek() >= K) break; // 모든 원소 만들기 성공 
            
            a=pq.poll();
            if(pq.size()==0) {
                answer=-1;
                break;
            }
            b=pq.poll();
            pq.add(a+b*2);
            answer++;
        }
        
    }
}