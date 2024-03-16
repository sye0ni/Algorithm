import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 그리디 ; 키 차이가 가장 큰 곳을 제외한다
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> contrast=new PriorityQueue<>();

        int n,k;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        int[] height=new int[n];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            height[i]=Integer.parseInt(st.nextToken());
            if(i>0) {
                contrast.add(height[i]-height[i-1]);
            }
        }

        long sum=0;
        for(int i=0;i<n-k;i++) {
            sum+=contrast.poll();
        }

        System.out.println(sum);
    }

}