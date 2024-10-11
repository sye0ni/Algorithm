import java.util.*;

// 최소의 비용으로 모든 섬이 통행 가능하도록 만들기 -> MST 만들기
// n-1개 이상의 간선 필요 
// union find 

class Solution {
    
    int[] parent;
    
    
    class Node implements Comparable<Node> {
        int x,y,cost;
        
        Node(int x,int y,int cost) {
            this.x=x;
            this.y=y;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.cost-node.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        
        parent=new int[n];
        for(int i=0;i<n;i++) parent[i]=i; // 처음에는 자기 자신이 부모임 
        
        Set<Integer> included=new HashSet<>(); 
        int answer=0;
        PriorityQueue<Node> pq=new PriorityQueue<>();
        
        for(int i=0;i<costs.length;i++) { // pq 생성 
            pq.add(new Node(costs[i][0],costs[i][1],costs[i][2]));
        }
        
        while(!pq.isEmpty()) {
            Node curr=pq.poll();
            int x=curr.x;
            int y=curr.y;
            int cost=curr.cost;
            
            if(find(x)==find(y)) continue;
            else {
                union(x,y);
                answer+=cost;
            }
            
        }
        
        return answer;
    }
    
    int find(int x) {
        if(x==parent[x]) return x;
        return find(parent[x]);
    }
    
    void union(int x,int y) { // 더 작은 값으로 합치기 
        x=find(x);
        y=find(y);
        
        if(x<y) parent[y]=x;
        else parent[x]=y;
    }
}