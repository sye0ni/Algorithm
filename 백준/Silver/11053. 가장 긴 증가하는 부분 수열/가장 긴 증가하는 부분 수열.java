import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        int[] dp=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
        dp[0]=1;

        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    if(dp[j]>=dp[i]) dp[i]=dp[j]+1;
                }
            } // 내부 for 문
            if(dp[i]==0) dp[i]=1;
        } // 외부 for 문
        int max=0;
        for(int i=0;i<n;i++){
//            System.out.print(dp[i]+" ");
            if(dp[i]>max) max=dp[i];
        }
//        System.out.println();
        System.out.println(max);
    }
}