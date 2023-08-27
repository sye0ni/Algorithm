import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static int[][] graph;
    static int N, Min;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;
        N = Integer.parseInt(br.readLine()); // 다리길이
        graph = new int[N][N];
        Min = Integer.MAX_VALUE;

        // 입력
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int group = 1;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) { // 섬별로 마킹 다르게 하기
            for (int j = 0; j < N; j++) {
                if (graph[i][j] != 0 && !visited[i][j]) {
                    graph[i][j] = group;
                    dfs(i, j, group);
                    group++;
                }
            }
        }

//        for (int i = 0; i < N; i++) { // 섬별로 마킹 다르게 하기
//            for (int j = 0; j < N; j++) {
//                System.out.print(graph[i][j]+" ");
//            }
//            System.out.println();
//        }

        int cnt = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) { // bfs 수행할 수 있는 위치에서 수행
            for (int j = 0; j < N; j++) {
                if (!check(i, j)) continue;
                if(graph[i][j]==0) continue;
                visited = new boolean[N][N];
                cnt = bfs(i, j);
//                System.out.println("bfs 수행점 : "+i+","+j);
//                System.out.println(cnt);
                if(cnt==0) continue;
                if (cnt < Min) Min = cnt;
            }
        }
        System.out.println(Min);
    } // main

    static boolean check(int x, int y) { // 사방이 막혀있는지 확인
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1}; // 상하좌우
        int currX, currY;
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            currX = x + dx[i];
            currY = y + dy[i];

            if (currX < 0 || currX >= N || currY < 0 || currY >= N) continue;
            if (graph[currX][currY] == 0) {
                flag = true;
                break;
            } // 가능
        }
        return flag;
    }

    static void dfs(int x, int y, int g) { // 그룹별로 색칠하기

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1}; // 상하좌우
        int currX, currY;

        for (int i = 0; i < 4; i++) {
            currX = x + dx[i];
            currY = y + dy[i];

            if (currX < 0 || currX >= N || currY < 0 || currY >= N) continue;
            if (visited[currX][currY]) continue;
            if (graph[currX][currY] == 0) continue;

            visited[currX][currY] = true;
            graph[currX][currY] = g;
            dfs(currX, currY, g);
        }
    }

    static int bfs(int x, int y) { // 다른 그룹 땅 찾기

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1}; // 상하좌우
        int currX, currY;
        int[][] depth = new int[N][N];
        int[] pop;
        boolean flag = false;
        int cnt = 0;
        int group=graph[x][y]; // 지금 섬으로부터 이을 수 있는 개수

        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            pop = queue.removeFirst();
            x = pop[0];
            y = pop[1];// 현재 탐색하는 위치
       //     System.out.println("BFS 시작점: "+x+","+y);

            for (int i = 0; i < 4; i++) {
                currX = x + dx[i];
                currY = y + dy[i];

          //      System.out.println("BFS 중간점: "+currX+","+currY);

                if (currX < 0 || currX >= N || currY < 0 || currY >= N) {
            //        System.out.println(1);
                    continue;
                }

                if (visited[currX][currY]) {
              //      System.out.println(2);
                    continue; // 이미 방문한 점
                }

                if (graph[currX][currY] == 0) { // 방문 x, 전진 가능
             //       System.out.println(3);
                    queue.addLast(new int[]{currX, currY});
                    visited[currX][currY] = true;
                    depth[currX][currY] = depth[x][y] + 1;
                    continue;
                }

//                if (graph[currX][currY] == graph[x][y]){
//                 //   System.out.println(4);
//                    continue; // 같은 섬 & 0 은 아님
//                }

                if (graph[currX][currY] != group) {
               //     System.out.println(4);
                    flag = true;
                    cnt = depth[x][y];
                    break;
                }

          //      System.out.println(5);
            } // for

            if (flag) break;
        } // while

        return cnt;
    }

}