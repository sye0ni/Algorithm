import java.util.*; 

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int remain = 0;
        
        int limit=health;
        int time=bandage[0]; // 기술 시전 시간 
        int recovery=bandage[1]; // 초당 회복량
        int addedRecovery=bandage[2]; // 추가 회복량 
        
        // attacks -> [공격시간, 피해량]
        
        int lastTime=attacks[attacks.length-1][0];
        int cont=0; // 지속 시간 
        int attackIdx=0;
        
        for(int i=1;i<=lastTime;i++) {
            // 공격 받는 시간인지 확인 
            if(attacks[attackIdx][0]==i) { // 공격 받아 
                health-=attacks[attackIdx][1];
                if(health<=0) break; 
                attackIdx++; 
                cont=0;
            } else {
                cont++; // 연속 회복 횟수 추가
                health+=recovery;
                if(health>limit) health=limit; // 최대 체력 초과 불가능
                if(cont==time) { // 기술 시전 
                    cont=0; // 연속 성공 초기화 
                    health+=addedRecovery;
                    if(health>limit) health=limit;
                }
            }
        }
        
        if(health<=0) return -1;
        return health;
    }
}