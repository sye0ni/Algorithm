import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        long sum=0;
        long temp=0;

        long[][] dp=new long[N+1][10];

        Arrays.fill(dp[1],1);

        for(int i=2;i<=N;i++){
            temp=0;
            for(int j=0;j<=9;j++){
                temp+=dp[i-1][j];
                dp[i][j]=temp%10007;
            }
        }
        for(int i=0;i<10;i++){
            sum+=dp[N][i];
        }
        System.out.println(sum%10007);
    }

}