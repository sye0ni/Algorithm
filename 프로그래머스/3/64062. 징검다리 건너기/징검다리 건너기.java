import java.util.*;

// 디딤돌 한 번 밟으면 -1 , 0이 되면 그 다음 디딤돌로 뛸 수 있음 
// 가장 작은 수부터 차례로 0 만들기, 만들면서 동시에 앞뒤 간격 체크 , 그 간격이 k 보다 커지면 끝남 
// 이분탐색 : 친구의 수 

class Solution {
    public int solution(int[] stones, int k) {
                
        int min=1;
        int max=200000000;
        int mid=0;
        
        while(min<=max) {
            // System.out.println(mid);
            mid=(min+max)/2;
            
            if(available(mid,stones,k)) {
                min=mid+1;
            } // 건널 수 있다면 
            else {
                max=mid-1;
            }
            
        }
        
        return max;
    }
    
    boolean available(int mid,int[] stones,int k) {
        int cnt=0;
        // mid 명이 다리를 건널 수 있다면 true 반환 
        for(int i=0;i<stones.length;i++) {
            if(stones[i]-mid>=0) cnt=0;
            else cnt++; 
            if(cnt==k) return false; 
        }
        
    
        return true;
    }
}