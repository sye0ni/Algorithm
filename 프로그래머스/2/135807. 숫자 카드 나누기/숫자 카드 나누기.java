import java.util.*;

// 각 배열의 최솟값 이하
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        for(int i=arrayA[0];i>0;i--) {
            if(checkNumber(i,arrayA,"o") && checkNumber(i,arrayB,"x")){
                if(i>answer){
                    answer=i;
                } 
                break;
            }
        }
        
        System.out.println(answer);
        for(int i=arrayB[0];i>0;i--) {
            if(checkNumber(i,arrayB,"o") && checkNumber(i,arrayA,"x")){ 
                if(i>answer){
                    answer=i;
                } 
                break;
            }
        }
        
        return answer;
    }
    
    boolean checkNumber(int num,int[] arr,String cond) { 
        
        boolean flag=true; // 조건을 만족함 
        
        if(cond.equals("o")) { // 나누어 떨어지는지 확인 
            for(int i=0;i<arr.length;i++) {
                if(arr[i]%num!=0){
                    flag=false;
                    break;
                }
            }
        }
        
        else {
            for(int i=0;i<arr.length;i++) {
                if(arr[i]%num==0){
                    flag=false;
                    break;
                }
            }
        }
        
        return flag;
    }
    
}