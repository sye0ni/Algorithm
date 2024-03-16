import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] students;
    static boolean[] visited;
    static boolean[] finished;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            students = new int[N];
            visited = new boolean[N];
            finished = new boolean[N];
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                students[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int i = 0; i < N; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }

            System.out.println(N - cnt);
        }
    }

    static void dfs(int start) {
        visited[start] = true;
        int next = students[start];

        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                for (int i = next; i != start; i = students[i]) {
                    cnt++;
                }
                cnt++; // 시작 노드까지 카운트
            }
        }
        finished[start] = true;
    }
}