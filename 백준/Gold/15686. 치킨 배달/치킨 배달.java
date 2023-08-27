import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 임승연
 * 
 * 조합 이용
 * 전체 치킨집에서 m 개만큼 뽑을 때마다, 집과의 거리들을 하나씩 비교하여 최솟값만 뽑아 도시 치킨 거리를 구한다
 * 새롭게 계산한 도시 치킨 거리가 기존에 저장된 최소 도시 치킨 거리보다 작을 때마다 값을 바꿔주기 
 * 
 * 메모리 20448 kb, 시간 284 ms
 *
 */

public class Main {

	static int[][] city;	// 도시 정보 저장 
	static List<Integer> comb;	// 조합으로 뽑은 인덱스 저장할 리스트 
	static int Min;				// 최소 거리 저장할 변수
	static List<int[]> chicken;	// 치킨집 좌표 저장
	static List<int[]> homes;	// 집 좌표 저장 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] temp=br.readLine().split(" ");
		
		int n=Integer.parseInt(temp[0]);
		int m=Integer.parseInt(temp[1]); // n,m 입력 
		
		city=new int[n][n];		// 도시 정보 저장 
		comb=new ArrayList<>(); // 조합으로 뽑은 인덱스 저장할 리스트 
		
		chicken=new ArrayList<>();	// 치킨집 저장 
		homes=new ArrayList<>();	// 집 저장 
		Min=Integer.MAX_VALUE;		// 최소 거리 저장할 변수 초기화 

		
		for(int i=0;i<n;i++) {
			temp=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				city[i][j]=Integer.parseInt(temp[j]); // 도시 만들기 
				if(city[i][j]==2) chicken.add(new int[] {i,j}); // 치킨집 좌표 저장 
				else if(city[i][j]==1) homes.add(new int[] {i,j}); // 집 좌표 저장 
			}
		} // 입력값 받기 완료 
		
		combination(0,m);
		System.out.println(Min);	// m 개를 다 뽑아본 뒤 최솟값  출력 
		
	}
	
	static void combination(int cnt,int goal) { // cnt 는 현재까지 뽑은 수, goal 은 최종 뽑을 수 
		
		if(cnt==goal) { // 집들과 치킨집과의 거리 비교해서 최솟값 바꿔주기 
			
			int[] curr_chicken; // 지금 확인하는 치킨집 
			int[] curr_home;	// 지금 확인하는 집 
			int distance,city_distance=0;	// 집-치킨 거리, 전체 도시 거리 
			
			for(int i=0;i<homes.size();i++) { 	// 집 하나씩 확인 
				distance=Integer.MAX_VALUE;		// 집- 치킨 거리는 매 집마다 초기화  
				curr_home=homes.get(i);	
				
				for(int j=0;j<goal;j++) {		// m 개의 치킨집 확인 
					curr_chicken=chicken.get(comb.get(j)); // 이번 치킨집의 좌표 
					if(distance>Math.abs(curr_home[0]-curr_chicken[0])+Math.abs(curr_home[1]-curr_chicken[1])) {
						distance=Math.abs(curr_home[0]-curr_chicken[0])+Math.abs(curr_home[1]-curr_chicken[1]);
					}	// 이번 치킨집과의 거리가 더 작으면 값 바꿈 
				} 
				
				city_distance+=distance; // 도시 거리에 더해준다 
			}
			
			if(city_distance<Min) Min=city_distance;	// 전체 도시 거리를 구한 뒤 더 작으면 값 변경 
			return;
		}
		
		for(int i=cnt;i<chicken.size();i++) { // 치킨집을 m 개만큼 뽑는 조합 
			if(!comb.contains(i)) {
				if(comb.size()>0 && comb.get(comb.size()-1)>i) continue; // 조합 조건 만족 x
				comb.add(i);
				combination(cnt+1, goal);
				comb.remove(comb.indexOf(i));
			}
		}
	}
}
