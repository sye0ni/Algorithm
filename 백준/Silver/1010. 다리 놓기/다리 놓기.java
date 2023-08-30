import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n,m;
        StringBuilder sb=new StringBuilder();;
        int[][] dp;
        int t=Integer.parseInt(br.readLine());
        String[] str;

        for(int k=0;k<t;k++) {
            str=br.readLine().split(" ");
            n=Integer.parseInt(str[0]);
            m=Integer.parseInt(str[1]);

            dp=new int[m+1][n+1]; // dp[x][y] 에는 xCy 의 값이 저장

            for(int i=1;i<=m;i++){
                for(int j=0;j<=n;j++){
                    if(i<j) continue;
                    if(j==0 || i==j){
                        dp[i][j]=1;
                        continue;
                    }
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }
            }
            System.out.println(dp[m][n]);

        }
    }


}