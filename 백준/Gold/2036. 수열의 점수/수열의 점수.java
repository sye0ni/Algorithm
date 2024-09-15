import java.io.*;
import java.util.*;

// 최댓값이 되는 조건
// 1. 0 -> 음수랑 곱해서 0으로 만들어버리기
// 2. 1 -> 더하는 것이 더 이득임
// 3. 음수끼리, 양수끼리 제거
//  3-1. 짝이 안맞으면 혼자 제거
//  3-2. 양수는 내림차순 pq , 음수는 오름차순 pq

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> positive=new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> negative=new PriorityQueue<>();

        int n=Integer.parseInt(br.readLine());
        long sum=0;
        boolean zero=false;

        int num;
        for(int i=0;i<n;i++) {
            num=Integer.parseInt(br.readLine());

            if(num>1) positive.add(num);
            else if(num==1) sum+=1;
            else if(num==0) zero=true;
            else negative.add(num);
        }

        long a,b;
        while (positive.size()>1){
            a=positive.poll();
            b=positive.poll();
            sum+=a*b;
        }
        if(!positive.isEmpty()){
            sum+=positive.poll();
        }

        while(negative.size()>1){
            a=negative.poll();
            b=negative.poll();
            sum+=a*b;
        }

        if(!negative.isEmpty()){
            if(!zero) sum+=negative.poll();
        }

        System.out.println(sum);
    }
}