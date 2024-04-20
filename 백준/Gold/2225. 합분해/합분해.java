import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int N,K;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st=new StringTokenizer(br.readLine());

         N=Integer.parseInt(st.nextToken());
         K=Integer.parseInt(st.nextToken());
         dp=new long[K+1][N+1];

        Arrays.fill(dp[0],0);
        Arrays.fill(dp[1],1);

        for(int i=1;i<=K;i++) {
            for(int j=0;j<=N;j++) {
                for(int k=0;k<=j;k++) {
                    dp[i][j]+=dp[i-1][k];
                    dp[i][j]%=1000000000;
                }
            }
        }

        System.out.println(dp[K][N]%1000000000);
    }

}