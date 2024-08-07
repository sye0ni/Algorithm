import java.util.*;

// 언제 평균 시간이 최소가 되는지 
// 대기중인 작업 중 가장 소요 시간이 짧은 작업을 먼저 수행 


class Solution {
    
    class job implements Comparable<job> {
        int request;
        int spend;
        
        job(int request, int spend) {
            this.request=request;
            this.spend=spend;
        }
        
        @Override
        public int compareTo(job j) {
            return this.spend-j.spend;
        }
    }
    
    public int solution(int[][] jobs) {
        int time = 0;
        int sum=0;
        
        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
        PriorityQueue<job> pq=new PriorityQueue<>();

        int index = 0; 
        int count=0;
        job pop;
        
        while(count<jobs.length) {
            while (index < jobs.length && jobs[index][0] <= time) {
                pq.add(new job(jobs[index][0], jobs[index][1]));
                index++;
            }
            
            if (pq.isEmpty()) {
                time=jobs[index][0];
            } else {
                pop=pq.poll();
                time+=pop.spend;
                sum+=time-pop.request;
                count++;
            }
        }        
        
        
        return (int)sum/jobs.length;
    }
}