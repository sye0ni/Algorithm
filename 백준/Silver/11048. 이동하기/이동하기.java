import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N,M;
		int[][] dp;
		
		String[] temp=br.readLine().split(" ");
		
		N=Integer.parseInt(temp[0]);
		M=Integer.parseInt(temp[1]);
		
		dp=new int[N][M];
		
		for(int i=0;i<N;i++) {
			temp=br.readLine().split(" ");
			for(int j=0;j<M;j++) dp[i][j]=Integer.parseInt(temp[j]);
		}
		
		// 1행 채우기
		for(int j=1;j<M;j++) dp[0][j]+=dp[0][j-1];
		// 1열 채우기
		for(int i=1;i<N;i++) dp[i][0]+=dp[i-1][0];
		
		int max;
		for(int i=1;i<N;i++) {
			for(int j=1;j<M;j++) {
				max=dp[i-1][j]; // 위에서 오는 경우
				max=Math.max(max,dp[i-1][j-1]); // 좌상에서 오는 경우
				max=Math.max(max, dp[i][j-1]); // 옆에서 오는 경우 
				dp[i][j]+=max;
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}

}