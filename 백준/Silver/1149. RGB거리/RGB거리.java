import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] dp;
	static int[][] rgb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		rgb=new int[n][3];
		dp=new int[n][3];
		String[] temp;
		
		for(int i=0;i<n;i++) {
			temp=br.readLine().split(" ");
			for(int j=0;j<3;j++) rgb[i][j]=Integer.parseInt(temp[j]);
		} // 그래프 생성 

		for(int i=0;i<3;i++) dp[0][i]=rgb[0][i]; // 1행 복사 
		
		for(int i=1;i<n;i++) {
			paint(i);
		}
		
		// 마지막 행에서 최솟값 찾기 
		int min=Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			if(dp[n-1][i]<min) min=dp[n-1][i];
		}
		System.out.println(min);
	}

	static void paint(int row) { // row 행 dp 배열 채우기 
		for(int i=0;i<3;i++) {
			if(i==0) dp[row][i]=Math.min(dp[row-1][1],dp[row-1][2])+rgb[row][0];
			else if(i==1) dp[row][i]=Math.min(dp[row-1][0], dp[row-1][2])+rgb[row][1];
			else dp[row][i]=Math.min(dp[row-1][0], dp[row-1][1])+rgb[row][2];
		}
	}
}