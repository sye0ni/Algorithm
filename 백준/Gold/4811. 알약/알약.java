import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp
 * W >= H 의 조건을 유지
 */
public class Main {

    static long[][] dp;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int input;

        while(true) {
            input=Integer.parseInt(br.readLine());
            if(input==0) break;
            dp=new long[input+1][input+1]; // dp[i][j] -> W=i, H=j 가능한 경우의 수
            eat(input);
        }

        System.out.println(sb.toString());
    }

    static void eat(int N) {

        for(int i=1;i<=N;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0) dp[i][j]=1;
                else {
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }

        sb.append(dp[N][N]+"\n");
    }
}