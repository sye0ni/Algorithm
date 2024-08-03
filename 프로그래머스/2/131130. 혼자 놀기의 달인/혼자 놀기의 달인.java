import java.util.*;

// 재귀 

class Solution {

    PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
    boolean[] isVisited; // 상자 방문 여부 
    
    public int solution(int[] cards) {
        
        isVisited=new boolean[cards.length];
        for(int i=0;i<cards.length;i++) {
            cards[i]=cards[i]-1;
        }
        for(int i=0;i<cards.length;i++) {
            if(!isVisited[i]) {
                dfs(i,1,cards); // 상자 번호, 현재까지의 개수 
            }
        }
        
        if(pq.size()==1) return 0;
        return pq.poll()*pq.poll();
    }

    void dfs(int boxNum,int count,int[] cards) {

        isVisited[boxNum]=true;
        int n=cards[boxNum];
        
        if(isVisited[n]) {
            pq.add(count);
            return;
        }
        
        dfs(n,count+1,cards);
        
        
    }
}
