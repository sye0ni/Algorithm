import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq=new PriorityQueue<>();

        for(int i=0;i<N;i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        long sum=0;
        int a,b;
        while(true) {
            if(pq.size()==1) break;

            a=pq.poll();
            b=pq.poll();
            sum+=(a+b);
            pq.add(a+b);
        }

        System.out.println(sum);

    }
}