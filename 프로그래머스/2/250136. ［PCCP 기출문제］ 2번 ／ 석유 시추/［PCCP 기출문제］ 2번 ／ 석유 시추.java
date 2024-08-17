import java.util.*; 

// 최초에 그룹화 시켜놓기 : 1부터 시작 ~ 

class Solution {
    
    int n,m;
    int[][] group;
    int[] dx=new int[]{0,0,-1,1};
    int[] dy=new int[]{-1,1,0,0};
    int size=0;
    Map<Integer,Integer> sizeMap=new HashMap<>();
    int Max=0;
    
    public int solution(int[][] land) {
        
        n=land.length;
        m=land[0].length; // 가로, 세로
        group=new int[n][m];
        int gNum=1; // 그룹번호
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++){
                if(land[i][j]==1 && group[i][j]==0) {
                    size=1;
                    group[i][j]=gNum;
                    dfs(land,i,j,gNum);
                    sizeMap.put((Integer)gNum,(Integer)size);
                    gNum++;
                }
            }
        }
        
        calculateOil(gNum,land);
        
        return Max;
    }
    
    void dfs(int[][] land, int x, int y, int gNum){
        // x,y 에서 dfs 하여 gNum 으로 채우기 
        
        int nx,ny;
        int ret=0;
        
        for(int i=0;i<4;i++) {
            nx=x+dx[i];
            ny=y+dy[i];
            
            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            if(land[nx][ny]==1 && group[nx][ny]==0) {
                group[nx][ny]=gNum;
                size++;
                dfs(land,nx,ny,gNum);
            }
        }
        
    }
    
    void calculateOil(int gNum, int[][] land){
        boolean[] isVisited=new boolean[gNum];
        int num;
        int sum;
        
        for(int j=0;j<m;j++) {
            Arrays.fill(isVisited,false);
            sum=0;
            for(int i=0;i<n;i++) {
                num=group[i][j];
                if(num>0) {
                    if(!isVisited[num]) {
                        sum+=sizeMap.get(num);
                        isVisited[num]=true;
                    }
                }
            }
            if(sum>Max) Max=sum;
        }
    }
} 