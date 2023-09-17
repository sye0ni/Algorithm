import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static int[][] input;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<t;i++){ // t회 반복

            n=Integer.parseInt(br.readLine()); // 스티커의 수
            dp=new int[2][n];
            input=new int[2][n];

            for(int j=0;j<2;j++){
                st=new StringTokenizer(br.readLine()," ");
                for(int k=0;k<n;k++){
                    input[j][k]=Integer.parseInt(st.nextToken());
                }
            } // 입력 받기 완료

            if(n==1) {
                sb.append(Math.max(input[0][0],input[1][0])+"\n");
                continue;
            }
            dp[0][0]=input[0][0];
            dp[1][0]=input[1][0];
            dp[0][1]=dp[1][0]+input[0][1];
            dp[1][1]=dp[0][0]+input[1][1];
            if(n==2) {
                sb.append(Math.max(dp[0][1],dp[1][1])+"\n");
                continue;
            }
            for(int j=2;j<n;j++){
                dp[0][j]=Math.max(dp[1][j-1],dp[1][j-2])+input[0][j];
                dp[1][j]=Math.max(dp[0][j-1],dp[0][j-2])+input[1][j];
            }
            sb.append(Math.max(dp[0][n-1],dp[1][n-1])+"\n");

//            for(int j=0;j<2;j++){
//                for(int k=0;k<n;k++){
//                    System.out.print(dp[j][k]+" ");
//                }
//                System.out.println();
//            }

        } // tc 반복

        System.out.println(sb);
    }


}