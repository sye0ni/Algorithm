import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] dp=new int[1000001]; 
		
		dp[1]=0;
//		dp[2]=1;
//		dp[3]=1;
		
		int a,b,c;
	
		for(int i=2;i<=n;i++) {
			a=0;
			b=0;
			
			if(i%3==0) a=i/3; // 몫 
			if(i%2==0) b=i/2; // 몫 
			c=i-1;
			
			if(a==0 && b==0) dp[i]=dp[c]+1; // 3,2 모두 나누어 떨어지지 않는 경우 
			else if(a==0 && b!=0) dp[i]=Math.min(dp[b]+1, dp[c]+1); // 2로 나누어 떨어지는 경우
			else if(a!=0 && b==0) dp[i]=Math.min(dp[a]+1, dp[c]+1); // 3로 나누어 떨어지는 경우 
			else { // 3,2 모두 나누어 떨어지는 경우 
				dp[i]=Math.min(dp[a]+1, dp[b]+1);
				dp[i]=Math.min(dp[i], dp[c]+1);
			}
		}
		
		System.out.print(dp[n]);
	}
}