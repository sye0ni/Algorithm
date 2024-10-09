import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq=new PriorityQueue<>();
        st=new StringTokenizer(br.readLine());
        int t;
        for(int i=0;i<n;i++) {
            t=Integer.parseInt(st.nextToken());
            pq.add((long) t);
        }

        long a,b;
        long sum=0;
        for(int i=0;i<m;i++) {
            a=pq.poll();
            b=pq.poll();
            pq.add(a+b);
            pq.add(a+b);
        }

        while(!pq.isEmpty()) {
            a=pq.poll();
            sum+=a;
        }
        System.out.println(sum);
    }


}