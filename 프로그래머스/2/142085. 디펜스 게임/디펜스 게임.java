import java.util.*;

// 최대한 많은 라운드 진행 
// 특정 라운드에 무적권을 쓰려면 그 라운드까지 진행을 할 수 있다는 보장
// enemy가 많을때 무적권을 써야한다 - 우선순위큐 

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        
        int idx=0;
        int pop;
        
        while(idx<enemy.length) {            
            pq.add(enemy[idx]);
            if(n-enemy[idx]<0) { // 무적권 사용
                pop=pq.poll();
                n=n+pop;
                if(k==0) break;
                k--;
            }
            n-=enemy[idx];
            idx++;
        }
        
        return idx;
    }
}