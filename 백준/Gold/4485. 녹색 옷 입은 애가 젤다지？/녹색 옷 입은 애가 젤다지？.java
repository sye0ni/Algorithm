import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[][] copyMap;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        int iter=1;
        int size;
        
        while(true) {
        	size=Integer.parseInt(br.readLine());
        	if(size==0) break;
        	
        	map=new int[size][size];
        	copyMap=new int[size][size];
        	
        	for(int i=0;i<size;i++) {
        		st=new StringTokenizer(br.readLine()," ");
        		for(int j=0;j<size;j++) {
        			map[i][j]=Integer.parseInt(st.nextToken());
        			copyMap[i][j]=Integer.MAX_VALUE;
        		}
        	} // 맵 생성
        	
        	find();
        	
        	sb.append("Problem "+iter+": "+copyMap[size-1][size-1]+"\n");
        	iter++;
        }
        System.out.println(sb.toString());
	}
	
	static void find() { // 항상 4방 탐색을 하면서, 값이 갱신될 때마다 큐에 넣어준다 
		
		int size=map.length;
		Deque<int[]> queue=new ArrayDeque<>();
		boolean[][] visited=new boolean[size][size];
		int[] dx=new int[] {-1,1,0,0};
		int[] dy=new int[] {0,0,-1,1};
		int x,y,xx,yy;
		int[] pop;
		
		queue.addLast(new int[] {0,0}); // 출발점 
		copyMap[0][0]=map[0][0];
		
		while(!queue.isEmpty()) {
			pop=queue.removeFirst();
			x=pop[0];
			y=pop[1];
			
			for(int i=0;i<4;i++) {
				xx=x+dx[i];
				yy=y+dy[i];
				
				if(xx<0 || xx>=size || yy<0 || yy>=size) continue; 
				if(copyMap[xx][yy]>map[xx][yy]+copyMap[x][y]) {
					queue.addLast(new int[] {xx,yy});
					copyMap[xx][yy]=map[xx][yy]+copyMap[x][y];
				}
			}
		}
	}
	
}