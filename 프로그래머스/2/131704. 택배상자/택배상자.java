import java.util.*;

// order[i] : 기존의 컨테이너 벨트에 order[i]번째 상자를 i+1번째로 트럭에 실어야 함


class Solution {
    public int solution(int[] order) {
        int answer=0;
        Deque<Integer> container=new ArrayDeque<>();
        int idx=0; // 기존 컨테이너 가르키는 인덱스 
        
        for(int i=1;i<=order.length;i++) {
            if(order[idx]!=i) {
                container.addLast(i); 
            } else {
                answer++;
                idx++;
            }
            
            while(!container.isEmpty() && container.getLast()==order[idx]) {
                answer++;
                idx++;
                container.removeLast();
            }
        }
        
        return answer;
    }
}