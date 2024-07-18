import java.util.*;

class Solution {
    
    int cnt=0;
    HashSet<Integer> sets=new HashSet<>();
    boolean[] isVisited;
    
    public int solution(String numbers) {
        
        isVisited=new boolean[numbers.length()];
        
        for(int i=0;i<numbers.length();i++) {
            dfs("",numbers);
        }
        
        return cnt;
    }
    
    void dfs(String current,String numbers){
        
        if (!current.equals("")) {
            int currNumber = Integer.parseInt(current);
            if (!sets.contains(currNumber)) {
                sets.add(currNumber);
                if (checkPrime(currNumber)) {
                    cnt++;
                }
            }
        }
        
        for(int i=0;i<isVisited.length;i++) {
            if(!isVisited[i]) {
                isVisited[i]=true;
                dfs(current + numbers.charAt(i),numbers);
                isVisited[i]=false;
            }
        }
    }
    
    boolean checkPrime(int n) {
        if(n<=1) return false;

        for(int i=2;i<=(int)Math.sqrt(n);i++) {
            if(n%i==0) return false;
        }
        return true;
    }
}