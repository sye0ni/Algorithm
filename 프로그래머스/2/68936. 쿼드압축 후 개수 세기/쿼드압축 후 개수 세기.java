import java.util.*;

// 재귀

class Solution {
    
    int one;
    int zero;
    int size;
    
    public int[] solution(int[][] arr) {
        size=arr.length;
        
        divide(arr,0,0,size); // 시작점 x,y좌표,크기 
        
        return new int[]{zero,one};
    }
    
    void divide(int[][] arr,int x,int y,int len) {
        
        // 영역이 모두 같은 숫자인지 확인
        if(isSame(arr,x,y,len)) {
            if(arr[x][y]==0) {
                zero++;
            } else {
                one++;
            }
            return;
        }
        
        // 4개로 분할
        int newLen = len / 2;
        divide(arr, x, y, newLen); // 1사분면
        divide(arr, x, y + newLen, newLen); // 2
        divide(arr, x + newLen, y, newLen); // 3
        divide(arr, x + newLen, y + newLen, newLen); // 4
        
    }
    
    // 모두 같은 숫자인지 확인
    boolean isSame(int[][] arr, int x, int y, int len) {
        int n = arr[x][y];
        for(int i = x; i < x + len; i++) {
            for(int j = y; j < y + len; j++) {
                if(arr[i][j] != n) {
                    return false;
                }
            }
        }
        return true;
    }
}