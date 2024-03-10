import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 벌벌꿀 / 벌꿀벌 / 꿀벌벌
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int[] arr=new int[N];
        long[] sum=new long[N]; // 누적합 저장
        long sum0=Integer.MAX_VALUE;
        long sum1=0;
        long sum2=Integer.MAX_VALUE;

        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            if(i==0) sum[i]=arr[i];
            else sum[i]=sum[i-1]+arr[i];

            // sum0 의 최솟값
            if(arr[i]+sum[i]<sum0 && i>0 && i<N-1) {
                sum0=arr[i]+sum[i];
            }

            // sum1 의 최댓값
            if(arr[i]>sum1 && i>0 && i<N-1) {
                sum1=arr[i];
            }

            // sum2 의 최댓값
            if(i>0 && i<N-1 && arr[i]-sum[i-1]<sum2) {
                sum2=arr[i]-sum[i-1];
            }

        } // 누적합과 최소, 최댓값 저장

        sum0=2*sum[N-1]-arr[0]-sum0;
        sum1=sum1-arr[0]+sum[N-2];
        sum2=sum[N-1]-arr[N-1]-sum2;
//        System.out.println(sum1);

        long max=Math.max(sum0,sum1);
        max=Math.max(max,sum2);
        System.out.println(max);

    }
}