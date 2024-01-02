import java.util.*;

class Shortcut implements Comparable<Shortcut> {
    int start;
    int end;
    int distance;

    public Shortcut(int start, int end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Shortcut other) {
        return this.end - other.end;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int D = scanner.nextInt();

        List<Shortcut> shortcuts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int distance = scanner.nextInt();

            if (end <= D) {
                shortcuts.add(new Shortcut(start, end, distance));
            }
        }

        Collections.sort(shortcuts);

        int[] dp = new int[D + 1];
        for (int i = 0; i <= D; i++) {
            dp[i] = i; // 기본값 설정
        }

        for (int i = 0; i <= D; i++) {
            // 현재 위치까지 가는 최소 거리 갱신
            for (Shortcut shortcut : shortcuts) {
                if (i == shortcut.end) {
                    dp[i] = Math.min(dp[i], dp[shortcut.start] + shortcut.distance);
                } else if (i < shortcut.end) {
                    dp[shortcut.end] = Math.min(dp[shortcut.end], dp[shortcut.start] + shortcut.distance + shortcut.end - i);
                }
            }
            // 다음 위치로 이동하는데 걸리는 시간 갱신
            if (i < D) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            }
        }

        System.out.println(dp[D]);
        scanner.close();
    }
}