import java.util.*;

class Solution {
    
    int min=Integer.MAX_VALUE;
    
    public int solution(int x, int y, int n) {

        bfs(x,y,n);
        if(min==Integer.MAX_VALUE) min=-1;
        return min;
    }
    
    void bfs(int x,int goal,int n) {
        
        boolean[] isVisited=new boolean[goal+1];
        Deque<int[]> queue=new ArrayDeque<>();
        queue.addLast(new int[]{x,0});
        isVisited[x]=true; // 최초 방문이 가장 짧은것 
        
        int[] temp;
        int depth,curr,next;
        boolean flag=false;
        
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            curr=temp[0];
            depth=temp[1];
            
            if(curr== goal) {
                flag=true;
                min=depth;
                break;
            }
            
            next=curr+n;
            if(next<= goal && !isVisited[next]) {
                queue.addLast(new int[]{next,depth+1});
                isVisited[next]= true;
            }
            
            next=curr*2;
            if(next<= goal && !isVisited[next]) {
                queue.addLast(new int[]{next,depth+1});
                isVisited[next]= true;
            }
            
            next=curr*3;
            if(next<= goal && !isVisited[next]) {
                queue.addLast(new int[]{next,depth+1});
                isVisited[next]= true;
            }
        }
        
    }
}