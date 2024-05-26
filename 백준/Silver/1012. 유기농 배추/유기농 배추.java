import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] graph = new int[m][n];

            for (int kk = 0; kk < k; kk++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            int cnt = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 1) {
                        bfs(i, j, m, n, graph);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    public static void bfs(int i, int j, int m, int n, int[][] graph) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        graph[i][j] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int r = 0; r < 4; r++) {
                int nx = x + dx[r];
                int ny = y + dy[r];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (graph[nx][ny] == 0) {
                    continue;
                }
                if (graph[nx][ny] == 1) {
                    q.add(new int[]{nx, ny});
                    graph[nx][ny] = 0;
                }
            }
        }
    }
}