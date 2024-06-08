import java.util.*;

class Task {
    String name;
    int remain;
    
    Task(String name, int remain) {
        this.name=name;
        this.remain=remain; 
    }
}

class Solution {
    
    public String[] solution(String[][] plans) {
        List<String> answer=new ArrayList<>();
        Deque<Task> stack=new ArrayDeque<Task>();
        
        // 시작 시간 순 정렬 
        Arrays.sort(plans,(o1,o2)->{
            return o1[1].compareTo(o2[1]);
        });
        
        int time; // 시간으로 변환 
        int hour,minute; // 시간, 분 
        int spending; // 소요 시간 
        int end;
        int remain,temp;
        
        for(int i=0;i<plans.length-1;i++) {
            hour=Integer.parseInt(plans[i][1].substring(0,2));
            minute=Integer.parseInt(plans[i][1].substring(3,5));
            time=hour*60+minute; 
            spending=Integer.parseInt(plans[i][2]);
            end=time+spending; // 현재 과제가 끝나는 시간 
            
            hour=Integer.parseInt(plans[i+1][1].substring(0,2));
            minute=Integer.parseInt(plans[i+1][1].substring(3,5));
            time=hour*60+minute; // 다음 과제 시작 시간 
            
            if(time<end) { // 시간 부족 
                stack.addLast(new Task(plans[i][0], end-time));
            } else { // 시간 남음 
                answer.add(plans[i][0]); // 현재 과제 완료 
                if(time==end) continue;
                
                // 남은 시간 동안 최대한 처리하기 
                remain=time-end; 
                
                while(true) {
                    if(stack.isEmpty()) break; 
                    Task prev=stack.removeLast();
                    temp=prev.remain;
                    prev.remain=prev.remain-remain;
                    if(prev.remain>0) {
                        stack.addLast(prev);
                    } else {
                        answer.add(prev.name);
                    }
                    remain-=temp;
                    if(remain<=0) break;          
                }
            }  
        }
        
        // 마지막 과제 종료 
        answer.add(plans[plans.length-1][0]);
        
        // 나머지 과제 순차 종료 
        while(!stack.isEmpty()) {
            answer.add(stack.removeLast().name);
        }
            
        return answer.toArray(new String[answer.size()]);
    }
}