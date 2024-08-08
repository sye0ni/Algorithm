import java.util.*;

// 'D' 까지 혹은 끝까지 이동 후 정지 
// 큐에 넣을때 지금까지 이동 횟수 같이 넣기 

class Solution {
    
    int answer=-1;
    int[] start;
    int[][] depth;
    Deque<int[]> queue=new ArrayDeque<>();
    
    public int solution(String[] board) {
            
        start=new int[2];
        start[0]=-1;
        
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length();j++) {
                if(board[i].charAt(j)=='R') {
                    start[0]=i;
                    start[1]=j; 
                } // 시작점 저장 
            }
            if(start[0]!=-1) break;
        }
        
        depth=new int[board.length][board[0].length()]; // 방문배열과 유사한 역할 수행 
        for(int i=0;i<board.length;i++) Arrays.fill(depth[i],-1);
        
        depth[start[0]][start[1]]=0;
        bfs(start,board);
        
        for(int i=0;i<depth.length;i++) {
            for(int j=0;j<depth[i].length;j++) {
                System.out.print(depth[i][j]+" ");
            }
            System.out.println();
        }
        
        return answer;
    }

    void bfs(int[] start,String[] board) {
        
        queue.addLast(new int[]{start[0],start[1],0}); 
        
        int[] pop;
        int px,py,pd;
        
        while(!queue.isEmpty()) {
            pop=queue.removeFirst();
            px=pop[0];
            py=pop[1];
            pd=pop[2];
            // if(board[px].charAt(py)=='G') {
            //     answer=pd;
            //     break; 
            // } // 도착 
            
            addNextSpot(px,py,pd,board);
            if(answer>-1) break; 
        }
    }
    
    void addNextSpot(int x,int y,int dist,String[] board) {
        
        // System.out.println(x+","+y+" 에서의 이동 시작 ");
        
        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};
        
        int nx,ny;
        int garo=board[0].length();
        int sero=board.length;
        
        for(int i=0;i<4;i++) {
            nx=x;
            ny=y;
            while(true) {
                nx+=dx[i];
                ny+=dy[i];
                if(nx<0 || nx>=sero || ny<0 || ny>=garo || board[nx].charAt(ny)=='D'){ // 직전 좌표를 추가 
                    nx-=dx[i];
                    ny-=dy[i];
                    if(depth[nx][ny]==-1) {
                        queue.addLast(new int[]{nx,ny,dist+1}); 
                        depth[nx][ny]=dist+1;
                        // System.out.println(nx+","+ny+"의 depth : "+(dist+1));
                        if(board[nx].charAt(ny)=='G') {
                            answer=dist+1;
                        }
                    }
                    break;
                }
            }
        }
    }
}
