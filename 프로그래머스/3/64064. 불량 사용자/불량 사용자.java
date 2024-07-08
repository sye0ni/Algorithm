import java.util.*;


class Solution {
    
    boolean[] isVisited;
    HashSet<String> hashSet=new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        isVisited=new boolean[user_id.length];
        List<Integer> arrays=new ArrayList<>();
        
        dfs(0,user_id,banned_id,arrays);
        
        return hashSet.size();
    }
    
    void dfs(int curr, String[] user, String[] banned, List<Integer> arrays) {
        
        if(curr==banned.length) {
            Collections.sort(arrays);
            String result="";
            for(int i=0;i<arrays.size();i++) result+=arrays.get(i);
            
            if(hashSet.contains(result)) return;
            hashSet.add(result);
            return;
        } 
        
        for(int i=0;i<user.length;i++) {
            if(isVisited[i]) continue; 
            if(validate(user[i],banned[curr])) {
                isVisited[i]=true;
                arrays.add(i);
                dfs(curr+1,user,banned,arrays);
                isVisited[i]=false;
                arrays.remove(arrays.indexOf(i));
            }    
        }
        
    }
    
    
    boolean validate(String curr,String check) { // curr -> check (검증) 인지 확인 
        
        if(curr.length()!=check.length()) return false;
        
        String newStr="";
        
        for(int i=0;i<curr.length();i++) {
            if(check.charAt(i)=='*'){
                newStr+='*';
            } else {
                newStr+=curr.charAt(i);
            }
        }
        
        if(check.equals(newStr)) return true;
        
        return false;
    }
}