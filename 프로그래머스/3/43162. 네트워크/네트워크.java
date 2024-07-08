import java.util.*;

class Solution {
    
    boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        isVisited=new boolean[n];
        
        for(int i=0;i<n;i++) {
            if(!isVisited[i]) {
                dfs(i,computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    void dfs(int curr,int[][] computers) {
        isVisited[curr]=true;
        
        for(int i=0;i<computers.length;i++) {
            int next=computers[curr][i];
            if(i==curr) continue;
            if(next==1 && !isVisited[i]) {  // 연결되어있고, 아직 방문 전이면 
                dfs(i,computers);
            } 
        }
    }
}