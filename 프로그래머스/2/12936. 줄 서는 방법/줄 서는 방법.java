import java.util.*;

class Solution {
    
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        
        // 1부터 n까지 숫자를 리스트에 추가
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        
        k--; // k를 0-based index로 변환
        
        long factorial = 1;
        // n-1 팩토리얼 계산
        for (int i = 1; i < n; i++) {
            factorial *= i;
        }
        
        for (int i = 0; i < n; i++) {
            int index = (int)(k / factorial);
            answer[i] = numbers.remove(index); // index에 해당하는 숫자 선택 및 제거
            k %= factorial; // 남은 k값 갱신
            if (i < n - 1) {
                factorial /= (n - 1 - i); // 남은 팩토리얼 값 갱신
            }
        }
        
        return answer;
    }
}
