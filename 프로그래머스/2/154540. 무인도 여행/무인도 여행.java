import java.util.*;


class Solution {
    
    static PriorityQueue<Integer> pq=new PriorityQueue<>();  // 각 섬 생존 날짜 저장
    static Character[][] graph;
    static boolean[][] isVisited;
    static Deque<int[]> queue=new ArrayDeque<>(); 
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    static int sero,garo;
    
    public int[] solution(String[] maps) {
        sero=maps.length;
        garo=maps[0].length();
        
        graph=new Character[sero][garo];
        isVisited=new boolean[sero][garo];
        
        for(int i=0;i<sero;i++) {
            for(int j=0;j<garo;j++) {
                graph[i][j]=maps[i].charAt(j);
            }
        } // 그래프  생성
        
        for(int i=0;i<sero;i++) {
            for(int j=0;j<garo;j++) {
                if(graph[i][j]!='X' && !isVisited[i][j]) {
                    bfs(i,j);
                }
            }
        }
        
        int[] answer;
        // pq 에 있는 것들 빼서 배열 만들기 
        if(pq.size()==0) {
            answer=new int[]{-1};
        }
        else {
            answer=new int[pq.size()];
            for(int i=0;i<answer.length;i++) {
                answer[i]=pq.poll();
            }
        }
        
        return answer;
    }
    
    static void bfs(int x,int y) { // 시작점 지정 
        
        queue.clear(); 
        queue.addLast(new int[]{x,y}); 
        isVisited[x][y]=true; 
        
        int currX,currY; 
        int nextX,nextY;
        int[] temp;
        int cnt= graph[x][y]-'0'; // 문자를 숫자로 
        
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            currX=temp[0];
            currY=temp[1];
            
            for(int i=0;i<4;i++) {
                nextX=currX+dx[i];
                nextY=currY+dy[i];
                
                if(nextX<0 || nextX>=sero || nextY<0 || nextY>=garo || isVisited[nextX][nextY] || graph[nextX][nextY]=='X') {
                    continue;
                }
                
                isVisited[nextX][nextY]=true;
                queue.addLast(new int[]{nextX,nextY});
                cnt+=graph[nextX][nextY]-'0';
            }
            
        }
        
        pq.add(cnt);
    }
    
}