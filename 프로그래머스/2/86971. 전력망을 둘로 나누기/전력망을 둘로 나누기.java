import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static int min=Integer.MAX_VALUE;
    static boolean[] isVisited;
    static int cnt=0;

    public int solution(int n, int[][] wires) {
        int first=200,second=0;
        
        graph=new ArrayList[n];
        isVisited=new boolean[n];
        
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();
        
        int f,b;
        for(int i=0;i<wires.length;i++) {
            f=wires[i][0]-1;
            b=wires[i][1]-1;
            graph[f].add(b);
            graph[b].add(f);
        }
        
        
        for(int i=0;i<wires.length;i++) {
            Arrays.fill(isVisited,false);
            
            if(i>0) {
                graph[wires[i-1][0]-1].add(wires[i-1][1]-1);
                graph[wires[i-1][1]-1].add(wires[i-1][0]-1);
            } // 삭제했던 값 추가 
            
            int currF=wires[i][0]-1;
            int currB=wires[i][1]-1;
            
            for(int j=0;j<graph[currF].size();j++) {
                if(graph[currF].get(j)==currB) {
                    graph[currF].remove(j);
                    break;
                }
            }
             for(int j=0;j<graph[currB].size();j++) {
                if(graph[currB].get(j)==currF) {
                    graph[currB].remove(j);
                    break;
                }
            }
            
            isVisited[0]=true; 
            cnt=1;
            dfs(0);
            first=cnt;
            
            cnt=1;
            for(int j=1;j<n;j++) {
                if(!isVisited[j]) {
                    isVisited[j]=true;
                    dfs(j);
                    second=cnt;
                }
            }
            
            if(Math.abs(first-second)<min) {
                min=Math.abs(first-second);
            }
        }
        
        return min;
    }
    
    void dfs(int curr) {
        int next;
        
        for(int i=0;i<graph[curr].size();i++) {
            next=graph[curr].get(i);
            if(!isVisited[next]) {
                isVisited[next]=true;
                cnt++;
                dfs(next);
            }
        }

    }
}