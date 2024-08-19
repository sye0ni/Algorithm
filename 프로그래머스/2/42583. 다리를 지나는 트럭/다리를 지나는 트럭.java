import java.util.*;

// 다리를 건너려면 bridge_length 초가 필요함 , 1초에 1대씩만 실을 수 있음 
// 작은 무게를 여러개 올리기

// 리스트에 배열 담기 (무게, 다리에 올라온 시간) 
// 시간을 1초씩 늘려서 
// 1) 더 실을 수 있다면 더 실고 (idx++)
// 2) 더 실을 수 없다면 맨 앞에 있는 애 나갈때로 시간 이동 

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int cnt=0; // 이동한 트럭 갯수 
        List<int[]> bridge=new ArrayList<>();
        int idx=0;
        long sum=0;
        
        bridge.add(new int[]{truck_weights[idx],0}); // 첫번째 트럭 다리에 올림 
        sum+=truck_weights[idx];
        idx++;
        
        while(true) {
            
            time++; 
            
            // 맨 앞에 있는 트럭이 나갈 차례인지 확인하기 
            if(time==bridge.get(0)[1]+bridge_length) {
                sum-=bridge.get(0)[0];
                cnt++;
                bridge.remove(0);
            } 
            
            if(cnt==truck_weights.length) break; 
            
            // 더 실을 수 있는지 확인
            if(idx<truck_weights.length && sum+truck_weights[idx]<=weight) {
                sum+=truck_weights[idx];
                bridge.add(new int[]{truck_weights[idx],time});
                idx++;
            }

        }
        
        return time+1;
    }
}