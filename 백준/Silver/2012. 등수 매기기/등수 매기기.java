import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq=new PriorityQueue<>();

        int pop;
        long sum=0;
        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        for(int i=1;i<=N;i++){
            pop=pq.poll();
            sum+=Math.abs(pop-i);
        }

        System.out.println(sum);

    }
}