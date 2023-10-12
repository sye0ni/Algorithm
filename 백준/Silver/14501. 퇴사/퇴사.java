import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] T;
    static int[] P;
    static int maxProfit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        maxProfit = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(maxProfit);
    }

    static void dfs(int day, int profit) {
        if (day >= N) {
            maxProfit = Math.max(maxProfit, profit);
            return;
        }

        // 현재 날짜에서 상담을 수락하는 경우
        if (day + T[day] <= N) {
            dfs(day + T[day], profit + P[day]);
        }

        // 현재 날짜에서 상담을 수락하지 않는 경우
        dfs(day + 1, profit);
    }
}