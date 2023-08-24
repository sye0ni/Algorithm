import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * nC1 ~ nCn/2 까지 수행
 * 뽑을 때마다, 각 집합의 한 점에서 dfs 수행, 둘 다 전부 탐색이 가능한 경우에만 인구수 차이 측정하기 
 * 12968 kb, 116 ms
 */

public class Main {

	static int n; // 구역 수 
	static int[] population; // 인구수 저장 
	static ArrayList<Integer>[] graph; // 그래프  
	static ArrayList<Integer> comb; // 조합으로 뽑은 요소 저장 
	static ArrayList<Integer> notSelected; // 뽑지 않은 요소 저장 
	static boolean[] visited; // 방문 정보 저장 
	static int Min; // 최소 인구 차이 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] temp;
		n=Integer.parseInt(br.readLine());
		population=new int[n];
		graph=new ArrayList[n];
		comb=new ArrayList<>();
		notSelected=new ArrayList<>();
		Min=Integer.MAX_VALUE; // 초기화 
		
		temp=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			graph[i]=new ArrayList<>();
			population[i]=Integer.parseInt(temp[i]);
		} // 인구수 저장 

		
		for(int i=0;i<n;i++) {
			temp=br.readLine().split(" ");
			for(int j=1;j<temp.length;j++) {
				int area=Integer.parseInt(temp[j]);
				graph[i].add(area-1);
			}
		} // 그래프 생성 
		
		
		for(int i=1;i<=n/2;i++) {
			comb.clear();
			notSelected.clear();
			combination(0,i);
		} // 1개 ~ n/2 개 조합 
		
		if(Min==Integer.MAX_VALUE) System.out.println(-1); // 나눌 수 없다면 -1 출력 
		else System.out.println(Min);
		
		return;
	}
	
	static void combination(int cnt,int goal) { // 지금까지 뽑은 수, 목표 개수
		if(cnt==goal) {
			
			notSelected.clear(); 
			boolean flag=true; // 초기화 
						
			// 각 집합에서 한 점을 골라서 dfs 수행, 모든 정점에 방문하면 인구 측정 
			visited=new boolean[comb.size()]; 
			visited[0]=true; // 시작점 방문처리  
			
			dfs(comb.get(0)); // 조합 구역에서 한개 뽑아서 dfs 
			
			for(int i=0;i<comb.size();i++) {
				if(!visited[i]) return;
			} // 방문하지 못한 점이 있다면 더 진행하지 않음 
		
			for(int i=0;i<n;i++) {
				if(!comb.contains(i)) {
					notSelected.add(i);
				}
			} // 미포함 숫자 찾기 

			visited=new boolean[notSelected.size()];
			visited[0]=true;
			
			dfs(notSelected.get(0)); // 미포함 구역에서 한 개 뽑아서 dfs 
			
			for(int i=0;i<notSelected.size();i++) {
				if(!visited[i]) return;
			} // 방문하지 못한 점이 있다면 더 진행하지 않음 
			
			// 여기까지 왔다면, 두 구역 모두 연결되어 있다는 의미이다
			// 인구 차이 count 및 최솟값 변경 
			int result=count();
			if(result<Min) Min=result;
			
			return;
		}
		
		for(int i=cnt;i<n;i++) { // 조합 
			if(!comb.contains(i)) {
				if(comb.size()>0 && comb.get(comb.size()-1)>i) continue;
				comb.add(i);
				combination(cnt+1, goal);
				comb.remove(comb.size()-1);
			}
		} // for 문 
		
	} // combination 
		
	static void dfs(int curr) { // 연결 정점이면서, 같은 집합인 경우에만 수행 
		
		for(int i=0;i<graph[curr].size();i++) {
			int tmp=graph[curr].get(i); // 연결 정점 
			
			// 연결 정점과 현재 정점(curr) 가 같은 그룹일 때에만 dfs 진행 
			if((comb.contains(curr)&&comb.contains(tmp)) || (notSelected.contains(curr)&&notSelected.contains(tmp))) {
				int index=0;
				
				if(comb.contains(curr)) index=comb.indexOf(tmp);
				else index=notSelected.indexOf(tmp);
			
				if(visited[index]) continue; // 이미 방문한 점이면 가지 않음 
				visited[index]=true; // 방문 처리 
				dfs(tmp);	// 다음 연결 요소 방문 
			} 
		}

		return;
	}
	
	static int count() { // 두 구역의 인구수 차이 반환
		
		int sumSelected=0,sumNotSel=0;
		
		for(int i=0;i<n;i++) {
			if(comb.contains(i)) sumSelected+=population[i];
			else sumNotSel+=population[i];
		}
		
		return Math.abs(sumSelected-sumNotSel);
	}

}