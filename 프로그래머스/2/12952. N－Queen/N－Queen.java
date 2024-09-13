import java.util.*;

// 상하좌우대각선 피하기 
// 순열 & 백트래킹 

class Solution {
    
    int cnt=0;
    int[] perm;
    int size;
    boolean[] isVisited;
    
    public int solution(int n) {
        perm=new int[n];
        size=n;
        isVisited=new boolean[n];
        
        permutation(0); // 현재 인덱스, 값 
        
        return cnt;
    }
    
    void permutation(int index) {
        if(index==size) {
            cnt++;
            return; 
        }
        
        for(int i=0;i<size;i++) {
            if(!isVisited[i]) {
                if(!isValid(index,i)) continue; // index 번 칸에 i 를 넣는 것이 유효한지 확인 
                isVisited[i]=true;
                perm[index]=i;
                permutation(index+1);
                isVisited[i]=false;
            }
        }
    }
    
    boolean isValid (int index, int n) {
        // 중복 제거 했기 때문에 상하좌우 확인할 필요 없음 (index,n)
        boolean flag=true; 
        
        int x=index;
        int y=n;
        int dx,dy;
        
        for(int i=0;i<index;i++) {
            
            dx=i;
            dy=perm[i];
            
            if(Math.abs(x-dx)==Math.abs(y-dy)){
                flag=false;
                break;
            } 
        }
        
        return flag;
    }
}