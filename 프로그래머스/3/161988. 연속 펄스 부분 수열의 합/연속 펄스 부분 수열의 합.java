import java.util.*;

// 조건
// 1. 부호가 번갈아 나와야 함 
// 2. 절댓값의 합 최대

// 1 -1 1 -1 ... 
// -1 1 -1 1 ... 곱한 값들 따로 저장 후 
// 누적합이 최대인 값 구하기 

class Solution {
    public long solution(int[] sequence) {
        
        int size=sequence.length;
        long[] dpPlus=new long[size];
        long[] dpMinus=new long[size];
        
        for(int i=0;i<size;i++) {
            dpPlus[i]=sequence[i]*(long)Math.pow(-1,i);
            dpMinus[i]=sequence[i]*(long)Math.pow(-1,(i+1));
        }
        
        long sumPlus=dpPlus[0];
        long sumMinus=dpMinus[0];
        long maxPlus=sumPlus;
        long maxMinus=sumMinus;
        
        for(int i=1;i<size;i++) {
            for(int j=0;j<2;j++) {
                if(j==0) {
                    if(dpPlus[i]+dpPlus[i-1] > dpPlus[i]) {
                        dpPlus[i]=dpPlus[i]+dpPlus[i-1];
                        maxPlus=Math.max(dpPlus[i],maxPlus); 
                    } 
                } else {
                    if(dpMinus[i]+dpMinus[i-1] > dpMinus[i]) {
                        dpMinus[i]=dpMinus[i]+dpMinus[i-1];
                        maxMinus=Math.max(dpMinus[i],maxMinus); 
                    } 
                }
            }
            
        }
        
        
        return Math.max(maxPlus,maxMinus);
    }
}