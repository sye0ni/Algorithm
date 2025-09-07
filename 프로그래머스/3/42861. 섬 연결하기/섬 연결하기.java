import java.util.*;

// MST 만들기 

class Solution {

    static int[] parents; // 그룹핑 
    static int answer=0; // 최소비용 저장
    static PriorityQueue<island> pq;
    
    static class island implements Comparable<island> {
        
        int a,b,cost;
        
        public island(int a,int b, int cost) {
            this.a=a;
            this.b=b;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(island i) {
            return this.cost-i.cost;
        } 
        
    }
    
    public int solution(int n, int[][] costs) {
        parents=new int[n];
        int a,b,c;
        pq=new PriorityQueue<>(); 
        island temp;
        
        for(int i=0;i<n;i++) {  // 초기화 
            parents[i]=i; 
        }
        
        for(int i=0;i<costs.length;i++) {
            a=costs[i][0];
            b=costs[i][1];
            c=costs[i][2];
            
            pq.add(new island(a,b,c)); 
        }
        
        while(!pq.isEmpty()) {
            temp=pq.poll();
            a=temp.a;
            b=temp.b;
            c=temp.cost;
            if(find(a)==find(b)) continue; // 이미 연결 
            union(a,b);
            answer+=c;
        }
        
        
        return answer;
    }
    
    static void union(int a,int b) { // big -> small 으로 합치기 
        a=find(parents[a]);
        b=find(parents[b]);
        
        if(a>b) {
            parents[a]=b;
        }
        else {
            parents[b]=a;
        }
    }
    
    static int find(int x) {
        if(x==parents[x]) {
            return x;
        } 
        return find(parents[x]);
    }
    
  
}