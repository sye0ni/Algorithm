import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] perm; // 순열 담을 배열
	static boolean[] visited;
	static int[][] graph;
	static int min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		graph=new int[N][N];
		String[] temp;
		min=Integer.MAX_VALUE;
		perm=new int[N];
		visited=new boolean[N];
		
		for(int i=0;i<N;i++) {
			temp=br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j]=Integer.parseInt(temp[j]);
			}
		}
		
		permutation(0);
		System.out.println(min);
	}
	
	static void permutation(int cnt) {
		if(cnt==N) {
			int cost=0;
			for(int i=0;i<cnt;i++) {
				if(graph[perm[i%N]][perm[(i+1)%N]]==0) return;
				cost+=graph[perm[i%N]][perm[(i+1)%N]];
				if(cost>min) return;
			}
			
			if(cost<min) min=cost;
			
			return;
		} // N개 뽑았으면 
		
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				perm[cnt]=i;
				permutation(cnt+1);
				visited[i]=false;
			}
		} // 순열 뽑기 
		
	}

}