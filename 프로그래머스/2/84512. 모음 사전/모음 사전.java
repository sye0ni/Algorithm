import java.util.*;

// 중복순열 이용 
class Solution {
    
    static int count=0;
    static boolean flag=false;
    
    public int solution(String word) {
     //   int answer = 0;
        
        String input="";
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)=='A') input+="0";
            else if(word.charAt(i)=='E') input+="1";
            else if(word.charAt(i)=='I') input+="2";
            else if(word.charAt(i)=='O') input+="3";
            else input+="4";
        }
        System.out.println(input);
        search("",input); // input이 될 때까지 
        return count;
    }
    
    static void search(String curr,String word){
     //  System.out.println(curr);
        if(flag) return;
        
        if(curr.equals(word)){ // 조건 만족 
            flag=true;
            return;
        }
        
        count++;
        
        if(curr.length()==5){ // 가지치기 
            return; 
        }
        
        for(int i=0;i<5;i++){
            curr+=Integer.toString(i);
            search(curr,word);
            curr=curr.substring(0,curr.length()-1);
        }
        
    }
}