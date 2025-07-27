import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    static char[] vowels = {'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {
        dfs("", 0);
        return list.indexOf(word) + 1;
    }

    // 모든 가능한 조합 생성
    private void dfs(String current, int depth) {
        if (depth > 5) return;

        if (!current.isEmpty()) list.add(current);

        for (int i = 0; i < 5; i++) {
            dfs(current + vowels[i], depth + 1);
        }
    }
}