import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static long[][][] dp;
    static int[][] pipe;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        dp=new long[N][N][3]; // 가로, 세로, 대각선
        pipe=new int[N][N];


        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++) {
                pipe[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0]=1;

        for(int i=0;i<N;i++) {
            for(int j=2;j<N;j++) {
                if(pipe[i][j]==1) continue;

                // 옆에서 올 수 있는 경우
                dp[i][j][0]=dp[i][j-1][0]+dp[i][j-1][2];

                if(i==0) continue;

                // 위에서 올 수 있는 경우
                dp[i][j][1]=dp[i-1][j][1]+dp[i-1][j][2];

                // 대각선에서 올 수 있는 경우
                if(pipe[i-1][j]==0 && pipe[i][j-1]==0) dp[i][j][2]=dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];

            }
        }

        System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);

    }

}