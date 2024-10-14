import java.io.*;
import java.util.*;

// 3차원 bfs

public class Main {
    static int L,R,C;
    static char[][][] building;
    static Spot start,end;
    static int min=Integer.MAX_VALUE; // 걸리는 시간

    static class Spot {
        int x,y,z,time;

        Spot(int z,int x,int y){ // 층 행 열
            this.x=x;
            this.y=y;
            this.z=z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        while(true) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            L=Integer.parseInt(st.nextToken());
            R=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());

            if(L==0&&R==0&&C==0) break;

            building=new char[L][R][C];
            min=Integer.MAX_VALUE;

            for(int k=0;k<L;k++) { // 그래프 생성
                for(int i=0;i<R;i++) {
                    String str=br.readLine();
                    for(int j=0;j<C;j++) {
                        building[k][i][j]=str.charAt(j);
                        if(building[k][i][j]=='S') {
                            start=new Spot(k,i,j);
                        } else if(building[k][i][j]=='E') {
                            end=new Spot(k,i,j);
                        }
                    }
                }
                br.readLine();
            }

            bfs();

            if(min==Integer.MAX_VALUE) {
                sb.append("Trapped!");
            } else {
                sb.append("Escaped in "+min+" minute(s).");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void bfs() {
        Deque<Spot> queue=new ArrayDeque<>();
        start.time=0;
        queue.addLast(start);
        int[] dx=new int[]{-1,1,0,0,0,0};
        int[] dy=new int[]{0,0,-1,1,0,0};
        int[] dz=new int[]{0,0,0,0,-1,1};
        boolean[][][] isVisited=new boolean[L][R][C]; // 층행열

        isVisited[start.z][start.x][start.y]=true;

        Spot curr;
        int xx,yy,zz;

        while(!queue.isEmpty()) {
            curr=queue.removeFirst();

            if(curr.x==end.x && curr.y==end.y && curr.z==end.z) { // 도착지
                min=curr.time;
                break;
            }

            for(int i=0;i<6;i++) {
                xx=curr.x+dx[i];
                yy=curr.y+dy[i];
                zz=curr.z+dz[i];

                if(xx<0 || xx>=R || yy<0 || yy>=C || zz<0 || zz>=L) continue;
                if(isVisited[zz][xx][yy]) continue;
                if(building[zz][xx][yy]=='#') continue;

                Spot added=new Spot(zz,xx,yy);
                added.time=curr.time+1;
                queue.addLast(added);
                isVisited[zz][xx][yy]=true;
            }
        }
    }


}