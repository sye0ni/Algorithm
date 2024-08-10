import java.util.*; 

// dfs 로 모든 경우 탐색 

class Solution {
    
    int min=Integer.MAX_VALUE; // 최솟값 
    int goal=0; // 조작해야하는 문자 갯수 
    boolean[] isChanged; // 조작 여부 
    
    public int solution(String name) {

        isChanged=new boolean[name.length()];
        Arrays.fill(isChanged,true);
        
        for(int i=0;i<name.length();i++) {
            if(name.charAt(i)!='A') {
                goal++;
                isChanged[i]=false; // 바꿔야할 문자 
            }
        }
        
        if(!isChanged[0]) {
            isChanged[0]=true;
            dfs(name,0,Math.min('Z'-name.charAt(0)+1, name.charAt(0)-'A'),1);
        }
        else {
            dfs(name,0,0,0);
        }
        
        return min;
    }
    
    void dfs(String name,int curr, int operation, int operCnt) { // 현재 위치에서 갈 수 있는 위치 탐색 
        
        if(operation>=min) return;
        
        if(operCnt==goal) {
            if(operation<min) {
                min=operation;
            }
            return; 
        }

        // todo : 현재 위치에서 조작해야하는 문자들 탐색 
        // isChanged=true , operation->다음알파벳,이전알파벳 변환 중 더 작은 값을 더해주기, operCnt++ 
        // 탐색 종료 후에는 값 되돌리기 
        int len=name.length();
        int addedOper;
        int addedLen;
        int left,right;
        
        for(int i=1;i<len;i++) {
            right=(curr+i)%len;
            left=(curr-i+len)%len;
            
            if(!isChanged[right]) {
                
                isChanged[right]=true;
                addedOper=Math.min('Z'-name.charAt(right)+1, name.charAt(right)-'A'); 
                operation+=addedOper;
                
                addedLen=Math.min(i,len-i);
                operation+=addedLen;
                operCnt++;
                
                dfs(name,right,operation,operCnt);
                
                isChanged[right]=false;
                operation-=addedOper;
                operation-=addedLen;
                operCnt--;
            }   
            
            if(!isChanged[left]) {
                
                isChanged[left]=true;
                addedOper=Math.min('Z'-name.charAt(left)+1, name.charAt(left)-'A'); 
                operation+=addedOper;
                
                addedLen=Math.min(i,len-i);
                operation+=addedLen;
                operCnt++;
                
                dfs(name,left,operation,operCnt);
                
                isChanged[left]=false;
                operation-=addedOper;
                operation-=addedLen;
                operCnt--;
            }   
        }
    }
    
}