import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static class question implements Comparable<question>{
        int deadline;
        long ramen;

        public question(int deadline, long ramen){
            this.deadline=deadline;
            this.ramen=ramen;
        }


        @Override
        public int compareTo(question q) {
            if(this.deadline!=q.deadline){
                return Integer.compare(this.deadline,q.deadline);
            }
            else {
                return Long.compare(q.ramen,this.ramen);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        PriorityQueue<question> pq=new PriorityQueue<>();
        PriorityQueue<Long> addPq=new PriorityQueue<>();

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            pq.add(new question(Integer.parseInt(st.nextToken()),Long.parseLong(st.nextToken())));
        }

        long sum=0;
        long min=Long.MAX_VALUE;
        int day=0; // 현재까지 처리한 문제 수
        question quest;

        for(int i=0;i<n;i++){

            quest=pq.poll();
//            System.out.println("-------------------------");
//            System.out.println("날짜 date:"+day);
//            System.out.println("sum:"+sum);
//            System.out.println("poll:"+quest.deadline+"->"+quest.ramen);


            if(day==quest.deadline){ // 앞 타임에서 추가한 값들 중 가장 작은 값을 빼기
                if(quest.ramen>addPq.peek()){
                    sum-=addPq.poll();
                    sum+=quest.ramen;
                    addPq.add(quest.ramen);
                }
            }

            if(day<quest.deadline) {
                sum+=quest.ramen;
                day++;
                addPq.add(quest.ramen);
            }
        }

        if(sum>Math.pow(2,31)) sum= (long) Math.pow(2,31);
        System.out.println(sum);
    }
}