import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (oper.equals("I")) {
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                } else if (oper.equals("D")) {
                    if (treeMap.isEmpty()) continue;
                    if (num == 1) { // 최댓값 삭제
                        int maxKey = treeMap.lastKey();
                        if (treeMap.get(maxKey) == 1) {
                            treeMap.remove(maxKey);
                        } else {
                            treeMap.put(maxKey, treeMap.get(maxKey) - 1);
                        }
                    } else { // 최솟값 삭제
                        int minKey = treeMap.firstKey();
                        if (treeMap.get(minKey) == 1) {
                            treeMap.remove(minKey);
                        } else {
                            treeMap.put(minKey, treeMap.get(minKey) - 1);
                        }
                    }
                }
            }

            if (treeMap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }

        System.out.println(sb);
    }
}