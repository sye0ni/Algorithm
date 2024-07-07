import java.util.*; 

// 어떤 조건을 만족해야 하는지 찾기 
// (왼쪽~나) , (나~오른쪽) 고려 
// 나 를 남기려면 -> 나보다 큰 수 선택 or 나보다 작은 수 선택 (최대1회)
// 큰수 나 큰수 -> ooo
// 큰수 나 작은수 -> ooo
// 작은수 나 작은수 -> xxx
// 작은수 나 큰수 -> ooo
// 나를 기점으로 양쪽의 최솟값 찾기 (항상 큰것만 선택하니까) -> 결과가 작나작 이면 안됨 

// O(n) 에 끝내기 
// 앞에서 한 번 뒤에서 한 번 최솟값 저장! 

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        int size=a.length;
        int[] leftMin=new int[size];
        int[] rightMin=new int[size];
        
        int min=a[0];
        for(int i=1;i<size;i++) {
            if(min>a[i]) {
                leftMin[i]=min;
                min=a[i];
            } else {
                leftMin[i]=min;
            }
        }
        
        min=a[size-1];
        for(int i=size-2;i>=0;i--) {
            if(min>a[i]) {
                rightMin[i]=min;
                min=a[i];
            } else {
                rightMin[i]=min;
            }
        }
        
        answer=2; // 맨 앞, 맨 뒤는 항상 가능함 
        for(int i=1;i<size-1;i++) {
            if(leftMin[i]<a[i] && rightMin[i]<a[i]) continue;
            answer++;
        }
        
        return answer;
    }
}