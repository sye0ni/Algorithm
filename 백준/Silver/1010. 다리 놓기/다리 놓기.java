import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		String[] temp;
		int west,east;
		
		for(int i=1;i<=T;i++) {
			temp=br.readLine().split(" ");
			west=Integer.parseInt(temp[0]);
			east=Integer.parseInt(temp[1]);
			buildBridge(west,east);
		}

	}
	
	static void buildBridge(int west,int east) {
		
		int[][] combination=new int[east+1][west+1];
		
		// iCj
		for(int i=1;i<=east;i++) {
			for(int j=0;j<=west;j++) {
				if(j==0 || i==j) combination[i][j]=1;
				else if(j==1) combination[i][j]=i;
				else combination[i][j]=combination[i-1][j-1]+combination[i-1][j];
			}
		}
		
		System.out.println(combination[east][west]);
	}

}