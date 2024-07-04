import java.util.*;

class Solution {
            
    static int min=Integer.MAX_VALUE;
    static int[][][] cost;
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static int n;
    
    public int solution(int[][] board) {
        n=board.length;
        cost=new int[n][n][4];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {            
                Arrays.fill(cost[i][j],Integer.MAX_VALUE);
            }
        }
        
        bfs(board);
        
        //  for(int i=0;i<n;i++) {
        //     for(int j=0;j<n;j++){
        //         System.out.print(cost[i][j]+" ");
        //     }
        //      System.out.println();
        // }
        
        int min=Integer.MAX_VALUE;
        for(int i=0;i<4;i++) {
            if(cost[n-1][n-1][i]==Integer.MAX_VALUE) continue;
            if(cost[n-1][n-1][i]<min) min=cost[n-1][n-1][i];
        }
        
        return min;
    }
    
    void bfs(int[][] board) {
        Deque<int[]> queue=new ArrayDeque<>();
        int currX,currY,currDir,currCost;
        int nextX,nextY;
        int[] temp;
        
        queue.addLast(new int[]{0,0,-1,0});
        for(int i=0;i<4;i++) cost[0][0][i]=0;
        // cost[0][0]=0;
        
        while(!queue.isEmpty()){
            temp=queue.removeFirst();
            currX=temp[0];
            currY=temp[1];
            currDir=temp[2];
            currCost=temp[3];
            
            for(int i=0;i<4;i++) {
                nextX=currX+dx[i];
                nextY=currY+dy[i];

                if(nextX<0 || nextX>=n || nextY<0 || nextY>=n) continue;
                if(board[nextX][nextY]==1) continue;
                
                if(currDir==-1) { // 시작점 처리 
                    cost[nextX][nextY][i]=100;
                    queue.addLast(new int[]{nextX,nextY,i,100});
                } else {
                    if(currDir==i) { // 같은 방향 
                        if(currCost+100 <=cost[nextX][nextY][i]) {
                            queue.addLast(new int[]{nextX,nextY,i,currCost+100});
                            cost[nextX][nextY][i]=currCost+100;
                        }
                    } else { // 다른 방향 
                        if(currCost+600 <= cost[nextX][nextY][i]) {
                            queue.addLast(new int[]{nextX,nextY,i,currCost+600});
                            cost[nextX][nextY][i]=currCost+600;
                        }
                    }
                }
    
            }
        }
 
    }
}