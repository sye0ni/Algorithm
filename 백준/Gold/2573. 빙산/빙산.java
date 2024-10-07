import java.io.*;
import java.util.*;

// 두 덩이 이상으로 분리되는 최초의 시간 구하기

public class Main {

    static int N,M;
    static int[][] iceberg;
    static int time;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    static boolean[][] isVisited;
    static int[][] subtract;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        iceberg=new int[N][M];
        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                iceberg[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        isVisited=new boolean[N][M];

        int cnt=0;
        boolean flag=false;

        while(true) {
            cnt=0; // 더 녹을 빙산이 있는지 확인
            subtract=new int[N][M];

            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(iceberg[i][j]!=0) {
                        counting(i,j);
                        cnt++;
                    }
                }
            }
            time++;
            if(cnt==0) break;

            melting();
//            print();

            cnt=0;
            isVisited=new boolean[N][M];
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(iceberg[i][j]!=0 && !isVisited[i][j]) {
                        bfs(i,j);
                        cnt++;
                    }
                    if(cnt>1) {
                        flag=true;
                        break;
                    }
                }
                if(flag) break;
            }

            if(flag) break;
        }

        if(flag) System.out.println(time);
        else System.out.println(0);

    }

    static void bfs(int x,int y) {
        isVisited[x][y]=true;

        Deque<int[]> queue=new ArrayDeque<>();
        queue.addLast(new int[]{x,y});

        int[] pop;
        int xx,yy;
        while(!queue.isEmpty()) {
            pop=queue.removeFirst();
            x=pop[0];
            y=pop[1];

            for(int i=0;i<4;i++) {
                xx=x+dx[i];
                yy=y+dy[i];

                if(xx<0 || xx>=N || yy<0 || yy>=M) continue;
                if(!isVisited[xx][yy] && iceberg[xx][yy]>0) {
                    queue.addLast(new int[]{xx,yy});
                    isVisited[xx][yy]=true;
                }
            }
        }

    }

    static void counting(int x,int y) {
        int xx,yy;
        int cnt=0;

        for(int i=0;i<4;i++) {
            xx=x+dx[i];
            yy=y+dy[i];

            if(xx<0 || xx>=N || yy<0 || yy>=M) continue;
            if(iceberg[xx][yy]==0) cnt++;
        }

        subtract[x][y]=cnt;
    }

    static void melting() {

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(iceberg[i][j]>0) {
                    iceberg[i][j]-=subtract[i][j];
                    if(iceberg[i][j]<0) iceberg[i][j]=0;
                }
            }
        }
    }

//    static void print(){
//        for(int i=0;i<N;i++) {
//            for(int j=0;j<M;j++) {
//                System.out.print(iceberg[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//    }

}