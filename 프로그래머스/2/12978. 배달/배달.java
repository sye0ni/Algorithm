// 아이디어 - 한 점에서 다른 모든 점까지의 최단거리를 구하기 위해 다익스트라 사용 
// 우선순위큐 이용하여 구현하기 

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        int[] distance=new int[N]; 
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0; // 시작점 초기화 
        
        // 그래프 생성 
        List<int[]>[] graph=new ArrayList[N];
        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
        
        for(int i=0;i<road.length;i++){
            graph[road[i][0]-1].add(new int[]{road[i][1]-1,road[i][2]});
            graph[road[i][1]-1].add(new int[]{road[i][0]-1,road[i][2]});
        }
        
        // pq 생성
        PriorityQueue<Integer> pq=new PriorityQueue<>((o1,o2)->Integer.compare(distance[o1],distance[o2]));
        pq.add(0); 
        
        int pop;
        int[] temp;
        while(!pq.isEmpty()){
            pop=pq.poll();

            for(int i=0;i<graph[pop].size();i++){
                temp=graph[pop].get(i); // 도착지, 거리 담겨있음 
                
                if(distance[temp[0]] > temp[1]+distance[pop]){
                    distance[temp[0]]= temp[1]+distance[pop]; 
                    pq.add(temp[0]);
                }
                
//                 if(!visited[temp[0]]){
//                     visited[temp[0]]=true;
//                     pq.add(temp[0]);
//                 }
            }
        }
        
        for(int i=0;i<N;i++){
            if(distance[i]<=K) answer++;
        }

        return answer;
    }
}