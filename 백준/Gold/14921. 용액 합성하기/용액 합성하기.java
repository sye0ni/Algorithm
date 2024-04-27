import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 완전탐색
 */
public class Main {



    public static void main(String[] args) throws IOException {
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         int N=Integer.parseInt(br.readLine());
         long sum=0;
         long minSum;
         long min=Long.MAX_VALUE;
         int[] arr=new int[N];

         StringTokenizer st=new StringTokenizer(br.readLine());
         for(int i=0;i<N;i++) arr[i]=Integer.parseInt(st.nextToken());
         int left=0;
         int right=N-1;

         while(left<right) {
//             System.out.println(left+"~"+right);
             sum=arr[left]+arr[right];
             if(Math.abs(sum)<Math.abs(min)) {
//                 System.out.println(arr[left]);
//                 System.out.println(arr[right]);
//                 System.out.println();
                 min=sum;
             }
             if(sum>0) right--;
             else left++;
         }
        System.out.println(min);
    }
}