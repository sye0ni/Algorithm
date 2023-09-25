import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 0 : 빈칸, 1 : 벽, 2 : 바이러스 
 * 
 * 1. 완탐으로 벽을 세울 3군데 찾기 ( 조합 ) 
 * 2. 벽을 세운 뒤 바이러스가 있는 위치에서 모두 bfs 수행
 * 3. 안전영역  크기 찾기, 최댓값 비교 
 *
 * 초기 상태를 저장해두고, 조합을 완성할 때마다 초기 상태 불러와서 수행하기 
 * 초기 상태의 빈 칸의 위치 (좌표) 를 따로 저장해두기 (조합에 사용) 
 */

public class Main {

	static int[][] graph;
	static int[][] copyGraph; // 복사본 
	static int n,m;
	static int Max;
	static List<int[]> empty; // 초기 빈칸
	static List<int[]> virus; // 초기 바이러스 
	static List<Integer> comb; // 조합 저장 (뽑은 빈칸 인덱스)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		graph=new int[n][m];
		copyGraph=new int[n][m];
		empty=new ArrayList<>();
		virus=new ArrayList<>();
		comb=new ArrayList<>();
		Max=0;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<m;j++) {
				graph[i][j]=Integer.parseInt(st.nextToken());
				if(graph[i][j]==0) empty.add(new int[] {i,j});
				else if(graph[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		
		combination(0); 
		System.out.println(Max);
	}
	
	static void combination(int cnt) {
		if(cnt==3) {
			// bfs 진행 
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					copyGraph[i][j]=graph[i][j];
				}
			} // 원조 그래프 복사 
			
			int[] temp_wall=new int[2];
			
			for(int i=0;i<3;i++) {
				temp_wall=empty.get(comb.get(i));
				copyGraph[temp_wall[0]][temp_wall[1]]=1; // 임시 벽 세우기 
			}
			
			for(int i=0;i<virus.size();i++) {
				bfs(virus.get(i));
			}

			count();
			
			return; 
		} // virus 위치에서 bfs 돌리기 
		
		for(int i=cnt;i<empty.size();i++) {
			if(cnt>0 && comb.get(cnt-1)>=i) continue;
			comb.add(i);
			combination(cnt+1);
			comb.remove(comb.size()-1);
		}
	}
	
	static void bfs(int[] start) { // 0인 점 탐색 -> 2로 바꾸기 
		
		Deque<int[]> queue=new ArrayDeque<>();
		queue.add(start);
		int[] pop;
		int x,y;
		int[] dx=new int[] {-1,1,0,0};
		int[] dy=new int[] {0,0,-1,1};
		int xx,yy;
		
		while(!queue.isEmpty()) {
			pop=queue.removeFirst();
			x=pop[0];
			y=pop[1];
			
			for(int i=0;i<4;i++) {
				xx=x+dx[i];
				yy=y+dy[i];
				if(xx<0 || xx>=n || yy<0 || yy>=m) continue;
				if(copyGraph[xx][yy]==0) { // add 
					queue.addLast(new int[] {xx,yy});
					copyGraph[xx][yy]=2;
				}
			}
		}
	}
	
	static void count() { // 빈칸의 개수 찾기 (copyGraph에서)
		int cnt=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copyGraph[i][j]==0) cnt++;
				//System.out.print(copyGraph[i][j]+" ");
			}
			//System.out.println();
		}
		
		
		//System.out.println();
		
		if(cnt>Max) {
			Max=cnt; 
		}
	}

}