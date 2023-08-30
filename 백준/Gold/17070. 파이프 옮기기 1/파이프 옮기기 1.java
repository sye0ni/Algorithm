import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    // 입력을 받기 위한 reader 생성
        String[] temp;

        int N=Integer.parseInt(br.readLine());
        int[][] graph=new int[N][N];
        int[][][] dp=new int[N][N][3]; // 0 : 우 , 1: 대각선, 2: 하

        for(int i=0;i<N;i++){
            temp=br.readLine().split(" ");
            for(int j=0;j<N;j++) graph[i][j]=Integer.parseInt(temp[j]);
        } // 그래프 생성

        dp[0][1][0]=1; // 시작점 표시
        for(int j=1;j<N;j++){
            if(graph[0][j]==1) break;
            dp[0][j][0]=1;
        }

        for(int i=1;i<N;i++){
            for(int j=1;j<N;j++){
                if(graph[i][j]==1) continue; // 벽 있으면 다음

                // 오른쪽에서 올 수 있는 경우
                dp[i][j][0]=dp[i][j-1][0]+dp[i][j-1][1];

                // 대각선에서 올 수 있는 경우
                if(i>0){
                    if(graph[i-1][j]==0 && graph[i][j-1]==0) dp[i][j][1]=dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];
                }

                // 위에서 올 수 있는 경우
                if(i>0){
                    dp[i][j][2]=dp[i-1][j][1]+dp[i-1][j][2];
                }

            }
        }
        System.out.print(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);

    }
}