import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // int 배열을 String 배열로 변환
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }
        
        // 정렬 기준 정의
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 두 문자열을 합쳤을 때 더 큰 쪽을 기준으로 정렬
                String order1 = o1 + o2;
                String order2 = o2 + o1;
                return order2.compareTo(order1);
            }
        });
        
        // 정렬된 문자열 배열을 하나의 문자열로 합침
        StringBuilder answer = new StringBuilder();
        for (String num : strNumbers) {
            answer.append(num);
        }
        
        // "0000" 같은 경우를 "0"으로 반환
        if (answer.charAt(0) == '0') {
            return "0";
        }
        
        return answer.toString();
    }
}
