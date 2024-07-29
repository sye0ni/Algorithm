import java.util.*;

// 5 4 3 2 1 
// 4 3 2 1 1 -> 0점
// 1 1 4 3 2 -> 3점

// 1 2 3 4 5 
// 1 1 2 3 4 

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int index=0;
        for(int i=0;i<A.length;i++) {
            if(index>=B.length) break; 
            
            // System.out.println(A[i]+","+B[index]);
            
            if(A[i]<B[index]) {
                // System.out.println(A[i]+"<"+B[index]+": 1번 케이스");
                index++;
                answer++; 
            } else {
                while(index<B.length && A[i]>=B[index]) {
                    index++; 
                }
                if(index<B.length){
                    // System.out.println(A[i]+"<"+B[index]+": 2번 케이스");
                    answer++;
                    index++;
                }
            }
        }
        
        return answer;
    }
}