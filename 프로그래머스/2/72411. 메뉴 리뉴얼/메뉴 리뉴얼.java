import java.util.*; 

class Solution {
    
    static Map<String, Integer> combMap;      // 조합 등장 횟수 저장
    static PriorityQueue<String> courses;     // 최종 결과
    static List<String> selected;             // 조합 임시 저장
    static String order;                       // 현재 주문

    public String[] solution(String[] orders, int[] course) {
        courses = new PriorityQueue<>();
        
        for (int c : course) {
            combMap = new HashMap<>();
            
            // 1️⃣ 각 주문에서 길이 c 조합 생성
            for (String o : orders) {
                order = sortString(o);   // 알파벳 순 정렬
                selected = new ArrayList<>();
                combinations(0, c);     // 재귀로 조합 생성
            }
            
            // 2️⃣ 등장 횟수 최댓값 구하기 (최소 2 이상)
            int max = 0;
            for (int cnt : combMap.values()) {
                if (cnt > 1) max = Math.max(max, cnt);
            }
            
            // 3️⃣ 최빈 조합만 결과 PQ에 추가
            for (String key : combMap.keySet()) {
                if (combMap.get(key) == max && max > 1) {
                    courses.add(key);
                }
            }
        }
        
        // 4️⃣ PQ를 배열로 변환
        String[] answer = new String[courses.size()];
        int idx = 0;
        while (!courses.isEmpty()) {
            answer[idx++] = courses.poll();
        }
        return answer;
    }
    
    // 조합 생성 재귀
    static void combinations(int start, int goal) {
        if (selected.size() == goal) {
            StringBuilder sb = new StringBuilder();
            for (String s : selected) sb.append(s);
            String comb = sb.toString();
            combMap.put(comb, combMap.getOrDefault(comb, 0) + 1);  // 등장 횟수 증가
            return;
        }
        
        for (int i = start; i < order.length(); i++) {
            selected.add(order.substring(i, i + 1));
            combinations(i + 1, goal);
            selected.remove(selected.size() - 1);
        }
    }
    
    // 문자열 알파벳 순 정렬
    static String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
