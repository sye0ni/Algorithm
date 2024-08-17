import java.util.*;

// 시작도착, 도착시작 좌표를 set 에 넣어두며 방문 확인  

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        Set<String> hash=new HashSet<>();
        Map<Character,int[]> dirMap=new HashMap<>();
        dirMap.put('U', new int[]{0,-1});
        dirMap.put('L', new int[]{-1,0});
        dirMap.put('D', new int[]{0,1});
        dirMap.put('R', new int[]{1,0});
        
        int x=0;
        int y=0;
        int nx,ny;
        int[] temp;
        String key;
        
        for(int i=0;i<dirs.length();i++) {
            temp=dirMap.get(dirs.charAt(i));
            nx=x+temp[0];
            ny=y+temp[1];
            
            if(nx>5 || nx<-5 || ny>5 || ny<-5) continue;
            
            key=x+""+y+""+nx+""+ny;
            if(!hash.contains(key)) {
                answer++;
                hash.add(key);
                key=nx+""+ny+""+x+""+y;
                hash.add(key);
            } 
            
            x=nx;
            y=ny;
        }
        
        return answer;
    }
}