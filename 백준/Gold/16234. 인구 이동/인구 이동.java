import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/16234
 *
 */

public class Main {

    static boolean[][] isValid;
    static int[][] graph;
    static int N,L,R;
    static int[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int day = 0;
        int cnt = 0;
        // int L,R;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        isValid = new boolean[N][N];
        check = new int[N][N];
      //  boolean flag = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 그래프 생성

        while (true) {

            boolean flag = false; // flag를 사용하여 종료 조건을 체크

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j + 1 < N && Math.abs(graph[i][j] - graph[i][j + 1]) >= L && Math.abs(graph[i][j] - graph[i][j + 1]) <= R) {
                        cnt++;
                        flag = true; // 조건이 하나라도 참일 경우 flag를 true로 설정
                    }
                    if (i + 1 < N && Math.abs(graph[i][j] - graph[i + 1][j]) >= L && Math.abs(graph[i][j] - graph[i + 1][j]) <= R) {
                        cnt++;
                        flag = true; // 조건이 하나라도 참일 경우 flag를 true로 설정
                    }
                }
            }

            if (!flag) {
                break; // 종료 조건: flag가 false일 경우 더 이상 반복하지 않음
            }

            if (cnt == 0) break;


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (check[i][j] == 0) {
                        bfs(i, j); // 인접 영역들 다 처리
                    }
                }
            }

        //    System.out.println("종료할까요");
            // 종료 조건 ...
//            cnt = 0;
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (j + 1 < N && Math.abs(graph[i][j] - graph[i][j + 1]) < L || Math.abs(graph[i][j] - graph[i][j + 1]) > R) {
//                        cnt++;
//                        break;
//                    }
//                    if (i + 1 < N && Math.abs(graph[i][j] - graph[i + 1][j]) < L || Math.abs(graph[i][j] - graph[i + 1][j]) > R) {
//                        cnt++;
//                        break;
//                    }
//                }
//
//                if (cnt > 0) break;
//            }



                // isValid 초기화
                day += 1;
                for (int i = 0; i < N; i++) {
                    //   Arrays.fill(isValid[i],false);
                    Arrays.fill(check[i], 0);
                }
                //   cnt=0;
            }

            System.out.println(day);

        }


    static void bfs(int x,int y){
        int sum=0;
        int cnt;
        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};
        int xx,yy;
        int[] temp;

        // 1. 개수, 합 count , 방문처리
        Deque<int[]> queue=new ArrayDeque<>();
        Deque<int[]> save=new ArrayDeque<>();
        queue.add(new int[]{x,y});
        save.add(new int[]{x,y});
        sum+=graph[x][y];
        check[x][y]=1;
        int abs;

        while(!queue.isEmpty()){
            temp=queue.removeFirst();
            xx=temp[0];
            yy=temp[1];

            for(int i=0;i<4;i++){

                if(xx+dx[i]<0 || xx+dx[i]>=N || yy+dy[i]<0 || yy+dy[i]>=N) continue;
                if(check[xx+dx[i]][yy+dy[i]]!=0) continue;

                abs=Math.abs(graph[xx][yy]-graph[xx+dx[i]][yy+dy[i]]);
                if(abs>=L && abs<=R) { // 더 탐색할 조건
                    //cnt+=1;
                    sum+=graph[xx+dx[i]][yy+dy[i]];
                   // isValid[xx+dx[i]][yy+dy[i]]=false; // 방문처리
                    check[xx+dx[i]][yy+dy[i]]=1; // 방문처리
                    queue.addLast(new int[]{xx+dx[i],yy+dy[i]});
                    save.addLast(new int[]{xx+dx[i],yy+dy[i]});
                }
            }
        }

        // 2. 값 바꾸기
        cnt=save.size();
        int result=(int)sum/cnt;
        while(!save.isEmpty()){
            temp=save.removeFirst();
            graph[temp[0]][temp[1]]=result;
        }
//
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(graph[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}