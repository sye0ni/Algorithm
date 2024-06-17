import java.util.*;

// 각 판매원의 이름을 담은 배열 enroll
// 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral

// 아래 -> 위를 가리키는 방향으로 그래프가 만들어져야함 
// hashmap<String, List<String>> 으로 저장 
// referral 없으면 center 

class Solution {
    
    Map<String, List<String>> graph=new HashMap<String, List<String>>();
    Map<String, Integer> index=new HashMap<String, Integer>();
    int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        
        // 이름 저장 
        graph.put("center",new ArrayList<String>());
        for(int i=0;i<enroll.length;i++) {
            graph.put(enroll[i],new ArrayList<String>());
            index.put(enroll[i],i);
        }
        
        // 아래서 위를 가리키게 저장 
        List<String> value;
        for(int i=0;i<referral.length;i++) {
            value=graph.get(enroll[i]);
            if(referral[i].equals("-")) {
                value.add("center");
            } else {
                value.add(referral[i]);
            }
            graph.replace(enroll[i],value);
        }
        
        // 수익 계산 
        String name;
        int count;
        for(int i=0;i<seller.length;i++) {
            name=seller[i];
            count=amount[i];
            
            calculate(name,count);
        }
        
        
        return answer;
    }
    
    // name 이 count 만큼 팔았다
    void calculate(String name, int count) {
        int idx=index.get(name); 
        int income=count*100;
        
        while(true) {
            idx=index.get(name);
            
            if(income*0.1<1) {  // 절사 불가능
                answer[idx]+=income;
                break; 
            } else { // 절사 가능 
                answer[idx]+=income-(int)(income*0.1);
            }
            
            // 다음 사람에게 10% 넘기기 
            income*=0.1;
            name=graph.get(name).get(0);
            if(name.equals("center")) {
                break; 
            }
        }
        
    }
}