import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main {

	static int[] inDegree; // 노드 진입차수 저장
	static ArrayList<Integer>[] graph; // 그래프 
	static int[] sequence; // 몇 학기에 이수할 수 있는지 
	static int n; // 과목 개수 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp=br.readLine().split(" ");
		n=Integer.parseInt(temp[0]);
		int m=Integer.parseInt(temp[1]); // n과 m 입력 
		
		int a,b;
		
		graph=new ArrayList[n];
		inDegree=new int[n];
		sequence=new int[n]; // 초기화
		
		for(int i=0;i<n;i++) graph[i]=new ArrayList<>(); 
		
		for(int i=0;i<m;i++) {
			temp=br.readLine().split(" ");
			a=Integer.parseInt(temp[0])-1;
			b=Integer.parseInt(temp[1])-1;
			graph[a].add(b); // a->b 를 graph[a].add(b) 로 저장 
			inDegree[b]+=1; // b의 진입차수 +1 
		}
		
		topologySort(); // 위상정렬 수행 
	}
	
	static void topologySort() {
		Deque<Integer> queue=new ArrayDeque(); // 큐 생성 
		
		for(int i=0;i<n;i++) {
			if(inDegree[i]==0) {
				queue.add(i); // 진입차수가 0인 노드들 큐에 추가 
				sequence[i]=1; // 이수 학기 1학기로 지정  
			}
		}
		
		while(!queue.isEmpty()) { // 큐가 빌 때까지 
			int curr=queue.removeFirst(); // 현재 순서 
			
			for(int i=0;i<graph[curr].size();i++) { // 현재 노드에서 도달할 수 있는 정점들 확인 
				inDegree[graph[curr].get(i)]--;	// 진입차수 -1 
				if(inDegree[graph[curr].get(i)]==0) { // 진입차수가 0이 되면 큐에 add, 이수 학기 지정 
					queue.addLast(graph[curr].get(i));
					sequence[graph[curr].get(i)]=sequence[curr]+1; 
				}
			}
		}
		for(int i=0;i<n;i++) System.out.print(sequence[i]+" "); // 정답 출력 
	}
}