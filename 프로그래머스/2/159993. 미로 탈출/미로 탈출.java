import java.util.*;

class Solution {
    
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    static boolean[][] isVisited;
    static char[][] graph; 
    static int answer=0;
    static int[] lever=new int[2];
    static int[] end=new int[2];
    
    public int solution(String[] maps) {
        
        int[] start=new int[2];
        
        graph=new char[maps.length][maps[0].length()];
        isVisited=new boolean[maps.length][maps[0].length()];
        
        for(int i=0;i<maps.length;i++) {
            for(int j=0;j<maps[0].length();j++) {
                graph[i][j]=maps[i].charAt(j);
                if(graph[i][j]=='S') {
                    start[0]=i;
                    start[1]=j;
                } 
                else if(graph[i][j]=='L') {
                    lever[0]=i;
                    lever[1]=j;
                }
                else if(graph[i][j]=='E'){
                    end[0]=i;
                    end[1]=j;
                }
            }
        } // 그래프 생성 
        
        bfs(start[0],start[1]);
        
        return answer;
    }
    
    void bfs(int x,int y) {
        
        Deque<int[]> queue=new ArrayDeque<int[]>();
        int[] temp;
        int currX,currY;
        int tempX,tempY,tempD;
        
        queue.addLast(new int[]{x,y,0});
        isVisited[x][y]=true;
        
        // 레버 찾기 
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            tempX=temp[0];
            tempY=temp[1];
            tempD=temp[2];
            
            if(graph[tempX][tempY]=='L') {
                answer=tempD;
                break; 
            }
            
            for(int i=0;i<4;i++) {
                currX=tempX+dx[i];
                currY=tempY+dy[i];
                
                if(currX>=0 && currX<graph.length && currY>=0 && currY<graph[0].length && !isVisited[currX][currY] && graph[currX][currY]!='X') {
                    isVisited[currX][currY]=true;
                    queue.addLast(new int[]{currX,currY,tempD+1});
                }
            }
        }
        
        if(!isVisited[lever[0]][lever[1]]) {
            answer=-1;
            return;
        }
        
        // 레버 -> 도착지 찾기 
        isVisited=new boolean[graph.length][graph[0].length]; // 방문 배열 초기화 
        queue=new ArrayDeque<int[]>();
        isVisited[lever[0]][lever[1]]=true;
        queue.addLast(new int[]{lever[0],lever[1],0});
        
        // 레버 찾기 
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            tempX=temp[0];
            tempY=temp[1];
            tempD=temp[2];
            
            if(graph[tempX][tempY]=='E') {
                answer+=tempD;
                break; 
            }
            
            for(int i=0;i<4;i++) {
                currX=tempX+dx[i];
                currY=tempY+dy[i];
                
                if(currX>=0 && currX<graph.length && currY>=0 && currY<graph[0].length && !isVisited[currX][currY] && graph[currX][currY]!='X') {
                    isVisited[currX][currY]=true;
                    queue.addLast(new int[]{currX,currY,tempD+1});
                }
            }
        }
        
        if(!isVisited[end[0]][end[1]]) {
            answer=-1;
            return;
        }
        
    }
}