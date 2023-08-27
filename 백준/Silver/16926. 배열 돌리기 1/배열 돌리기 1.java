import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * @author 임승연
 * 
 * 가장 바깥 사각형부터 돌리고 점점 안으로 들어간다  
 * 시작점 좌표의 규칙이 있으므로 rotate 메서드에 시작점 좌표, 탐색할 사각형의 가로 세로 크기를 전달
 * 4방향으로 나눠서 돌림
 *
 */
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n,m,r;
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		
		int[][] arr=new int[n][m];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		} // 그래프 생성 완료 
		
		for(int t=0;t<r;t++) { // r회 회전 
			
			int cnt=0;
			int nn=n+2;
			int mm=m+2;
			
			while(true) {
				nn=nn-2;
				mm=mm-2; // 가로, 세로 사이즈 줄여가기 
				if(nn<=0 || mm<=0) break;  // 종료 ! 
				
				rotate(arr,cnt++,nn,mm); // 시작점 좌표(x==y), 회전시킬 사각형 크기 전달
			}
		}
		
		for(int i=0;i<n;i++) { // 출력 
			for(int j=0;j<m;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	} // main
		
	// 돌려 ~! 
	static void rotate(int[][] arr,int start,int n,int m) {
		
		int move,wait; // wait 에는 밀릴 숫자 저장, move 에는 밀어줄 숫자 저장 
		move=arr[start][start]; // 시작점 저장 
		
		// 왼쪽 라인 -> 위에서부터 아래로 한칸씩 밀어주기
		for(int i=start;i<start+n-1;i++) {
			wait=arr[i+1][start];
			arr[i+1][start]=move;
			move=wait;
		}
		
		// ================== move 에 왼쪽 아래 모서리 값이 저장되어 있음 =================== // 
		
		// 아래 라인 -> 왼쪽에서 오른쪽으로 밀어주기 
		for(int j=start;j<start+m-1;j++) {
			wait=arr[start+n-1][j+1];
			arr[start+n-1][j+1]=move;
			move=wait;
		}
		
		// 위로 올라가는 방향 -> 아래에서 위로 밀어주기 
		for(int i=start+n-1;i>start;i--) {
			wait=arr[i-1][start+m-1];
			arr[i-1][start+m-1]=move;
			move=wait;
		}
		
		// 위쪽 라인 -> 오른쪽에서 왼쪽으로 밀어주기 
		for(int j=start+m-1;j>start;j--) {
			wait=arr[start][j-1];
			arr[start][j-1]=move;
			move=wait;
		}
	}

}