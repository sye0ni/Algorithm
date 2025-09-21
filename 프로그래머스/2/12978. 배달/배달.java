import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        int n;
        int dist;
        
        public Node (int n,int dist) {
            this.n=n;
            this.dist=dist;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.dist-node.dist;
        }
    }
    
    static ArrayList<Node>[] graph;
    static boolean[] isVisited;
    static int[] distance; // 각 점의 최단거리 저장 
    
    public int solution(int N, int[][] road, int K) {
        int answer=0;
        
        graph=new ArrayList[N+1];
        isVisited = new boolean[N+1];
        distance=new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        Arrays.fill(isVisited,false);
        
        for(int i=1;i<=N;i++) {
            graph[i]=new ArrayList<>();
        }
        
        int a,b,c;
        for(int i=0;i<road.length;i++) {
            a=road[i][0];
            b=road[i][1];
            c=road[i][2];
            
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }
        
        dijkstra();
        
        for(int i=1;i<=N;i++) {
            if(distance[i]<=K) answer++;
        }
        
        return answer;
    }
    
    static void dijkstra() {
        
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.add(new Node(1,0)); 
        isVisited[1]=true;
        distance[1]=0; 
        
        int curr, currDist; 
        Node pop;
        Node next;
        
        while(!pq.isEmpty()) {
            pop=pq.poll();
            curr=pop.n;   // 현재 위치 
            currDist=pop.dist;    
            
            for(int i=0;i<graph[curr].size();i++) {
                next=graph[curr].get(i);
                if(distance[next.n] > currDist+ next.dist) {   // 최단거리 갱신 
                    distance[next.n] = currDist+next.dist; 
                    pq.add(new Node(next.n,distance[next.n]));
                }
                if(!isVisited[next.n]) {
                    isVisited[next.n]=true; 
                }
            }
        }
        
    }
}