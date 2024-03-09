import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  13772 kb, 96 ms
 *  N 의 개수가 1-3 개 이므로, 완탐으로 구한다. => 시간초과
 *  이미 계산한 값을 다시 계산할 필요가 없기 때문에 dp 이용
 */


public class Main {
    static int[][][] dp;
    static int[][] attacks = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] scv = new int[3];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(input[i]);
        }

        dp = new int[61][61][61]; // dp[a][b][c]: 첫 번째 SCV가 a, 두 번째 SCV가 b, 세 번째 SCV가 c일 때의 최소 공격 횟수
        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 60; j++) {
                for (int k = 0; k <= 60; k++) {
                    dp[i][j][k] = -1; // 초깃값
                }
            }
        }
        
        System.out.println(dfs(scv[0], scv[1], scv[2]));
    }

    static int dfs(int a, int b, int c) {
        if (a <= 0 && b <= 0 && c <= 0) return 0;

        if (dp[a][b][c] != -1) return dp[a][b][c];

        dp[a][b][c] = Integer.MAX_VALUE;

        for (int i = 0; i < 6; i++) {
            int newA = Math.max(0, a - attacks[i][0]);
            int newB = Math.max(0, b - attacks[i][1]);
            int newC = Math.max(0, c - attacks[i][2]);
            dp[a][b][c] = Math.min(dp[a][b][c], dfs(newA, newB, newC) + 1);
        }
        return dp[a][b][c];
    }
}