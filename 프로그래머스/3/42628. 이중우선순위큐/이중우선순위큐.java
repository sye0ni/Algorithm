import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer;
        
        // min heap
        PriorityQueue<Integer> minHeap=new PriorityQueue<Integer>();
        // max heap 
        PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1,Integer o2) {
                return o2-o1;
            }
        });
        
        String oper;
        int num;
        StringTokenizer st;
        
        for(int i=0;i<operations.length;i++) {
            st=new StringTokenizer(operations[i]);
            oper=st.nextToken();
            num=Integer.parseInt(st.nextToken());
            
            if(oper.equals("I")) {
                minHeap.add(num);
                maxHeap.add(num); 
            } else if(oper.equals("D")) {
                if(num==1) { // 최댓값 삭제
                    if(maxHeap.size()==0) continue; 
                    num=maxHeap.poll();
                    minHeap.remove(num);
                } else {
                    if(minHeap.size()==0) continue; 
                    num=minHeap.poll();
                    maxHeap.remove(num);
                }
            }
        }
        
        if(minHeap.size()==0) answer=new int[]{0,0};
        else answer=new int[]{maxHeap.poll(),minHeap.poll()};
        
        return answer;
    }
}