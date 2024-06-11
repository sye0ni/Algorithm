import java.util.*;

// bfs 로 최단거리 구하기 
class Solution {
    
    static List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        int[] answer=new int[sources.length];
        graph=new ArrayList[n+1];
        
        int f,b;
        
        // 그래프 생성 
        for(int i=0;i<=n;i++){
            graph[i]=new ArrayList<Integer>();
        }
        
        for(int i=0;i<roads.length;i++) {
            f=roads[i][0];
            b=roads[i][1]; 
            graph[f].add(b);
            graph[b].add(f);
        }
        
        // 각 sources 에서 bfs 수행 
        for(int i=0;i<sources.length;i++) {
            answer[i]=bfs(sources[i],destination,n);
        }
        
        return answer;
    }
    
    int bfs(int source, int destination,int n) {
        
        // 방문 배열 초기화 
        boolean[] isVisited=new boolean[n+1];
        
        // queue 초기화 
        Deque<int[]> queue=new ArrayDeque<int[]>();
        
        // 시작점 처리 (curr, depth 저장)
        queue.addLast(new int[]{source,0});
        isVisited[source]=true;
        
        int curr,depth=0,temp;
        int[] pop;
        
        while(!queue.isEmpty()) {
            pop=queue.removeFirst();
            curr=pop[0];
            depth=pop[1];
            
            // 만약 도착지라면? 종료, 그 거리 리턴하기 
            if(curr==destination) {
                break;
            }
            
            for(int i=0;i<graph[curr].size();i++) {
                temp=graph[curr].get(i);
                if(!isVisited[temp]) {
                    if(temp==destination) return depth+1;
                    queue.addLast(new int[]{temp,depth+1});
                    isVisited[temp]=true;
                }
            }
        }
        
        if(isVisited[destination]) {
            return depth;
        } else return -1;
    }
}