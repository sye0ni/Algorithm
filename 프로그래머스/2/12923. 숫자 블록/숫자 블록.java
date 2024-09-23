import java.util.*; 

// 각 숫자의 배수에 해당하는 블록에 설치 
// 덮어쓰기 가능 
// 가장 큰 약수 찾기 .. ? 없으면 1 
// 인덱스+1 이 실제로 적혀있는 숫자 

class Solution {
    
    int[] arr;
    public int[] solution(long begin, long end) {
        arr=new int[(int)(end-begin+1)];
        
        for (long i=begin; i<=end; i++) {
            if (i==1) {
                arr[(int)(i-begin)] = 0; 
            } else {
                arr[(int)(i-begin)] = (int)findMaxDiv(i);
            }
        }
        return arr;
    }
    
    long findMaxDiv(long n) {
        long ret=1;
        
        for(long i=2;i<=(int)Math.sqrt(n);i++) {
            if(n%i==0) {
                if(n/i<=10000000) return n/i;
                ret=i;
            }
        }
        
        return ret;
    }
}