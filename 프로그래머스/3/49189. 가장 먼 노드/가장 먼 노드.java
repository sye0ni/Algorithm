import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static boolean[] isVisited;
    static int[] distance;
    static int max=0;
    
    public int solution(int n, int[][] edge) {
        
        int answer=0;
        graph=new ArrayList[n];
        isVisited=new boolean[n];
        distance=new int[n];
        
        for(int i=0;i<n;i++) graph[i]=new ArrayList<Integer>();
        
        int f,b;
        for(int i=0;i<edge.length;i++) {
            f=edge[i][0]-1;
            b=edge[i][1]-1;
            graph[f].add(b);
            graph[b].add(f);
        } // 그래프 생성 
        
        bfs();
        for(int i=0;i<n;i++) {
            if(distance[i]==max) answer++;
        }
        
        return answer;
    }
    
    static void bfs() {

        Deque<int[]> queue=new ArrayDeque<>();
        
        queue.addLast(new int[]{0,0});
        isVisited[0]=true;
        distance[0]=0;
        
        int[] temp;
        int depth,curr,next;
        
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();    
            curr=temp[0];
            depth=temp[1];
            
            for(int i=0;i<graph[curr].size();i++) {
                next=graph[curr].get(i);
                if(!isVisited[next]) {
                    queue.addLast(new int[]{next,depth+1});
                    distance[next]=depth+1;
                    isVisited[next]=true;
                    if(depth+1>max) {
                        max=depth+1;
                    }
                }
            }            
        }
        
    }
}