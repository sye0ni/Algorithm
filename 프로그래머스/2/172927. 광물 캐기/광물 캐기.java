import java.util.*;

// 최소한의 피로도 
// 그리디

// 다이아, 철, 돌 곡괭이 피로도 각각 저장하기 
// 5개씩 끊었을 때 피로도가 가장 높은 집단부터 좋은 곡괭이 소모하기 
// 피로도 다이아>철>돌 
// 좋은 곡괭이 다이아>철>돌 

class Solution {
    
    class Mine implements Comparable<Mine>{
        private int diamond;
        private int iron;
        private int stone;
        
        public Mine(int diamond,int iron,int stone){
            this.diamond=diamond;
            this.iron=iron;
            this.stone=stone; 
        }
        
         
        @Override
        public int compareTo(Mine mine) {
            if (this.diamond != mine.diamond) {
                return Integer.compare(mine.diamond, this.diamond); 
            } else if (this.iron != mine.iron) {
                return Integer.compare(mine.iron, this.iron); 
            } else {
                return Integer.compare(mine.stone, this.stone); 
            }
        }
    }
    
    int answer=0;
    PriorityQueue<Mine> pq=new PriorityQueue<>();
    
    public int solution(int[] picks, String[] minerals) {
        
        int totalPicks=picks[0]+picks[1]+picks[2];
        totalPicks*=5;
        if(minerals.length>totalPicks) {
            minerals=Arrays.copyOfRange(minerals,0,totalPicks);
        }
        
        
        for(int i=0;i<minerals.length;i+=5) {
            Mine mine=new Mine(0,0,0);
            
            for(int j=i;j<i+5;j++) {
                if(j==minerals.length) break;
                if(minerals[j].equals("diamond")) mine.diamond++;
                else if(minerals[j].equals("iron")) mine.iron++;
                else mine.stone++;
            }
            
            pq.add(mine);
        }
        
        while(!pq.isEmpty()) {
            Mine pop=pq.poll();
            if(picks[0]>0) {
                answer+=(pop.diamond + pop.iron + pop.stone); 
                picks[0]--;
            } else if(picks[1]>0) {
                answer+=(pop.diamond*5 + pop.iron + pop.stone);
                picks[1]--;
            } else if(picks[2]>0){
                answer+=(pop.diamond*25 + pop.iron*5 + pop.stone);
                picks[2]--;
            }
        }
        
        return answer;
    }
    

}