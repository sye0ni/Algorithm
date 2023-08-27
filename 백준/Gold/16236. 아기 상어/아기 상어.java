import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author 임승연
 *
 * bfs 를 이용해서 푸는 문제였는데, 조건이 여러가지가 주어지기 때문에 놓치지 않도록 처음부터 설계를 잘 하는 것이 중요하다
 * 가장 까다로운 조건이 먹을 수 있는 물고기가 2마리 이상인 경우
 *      거리, x 좌표, y 좌표를 모두 고려해야한다.
 *      중요도가 낮은것부터 기준으로 하여 정렬하는 방식으로 구현했는데 우선순위큐를 사용해서 구현해봐야겠다 ( <<< 꼭 ..!  )
 *
 * ------
 * 리스트에 저장된 배열들을 인덱스 기준으로 아래와 같이 정렬할 수 있다!
 *      arr.sort(Comparator.comparingInt(o->o[1])); -> ArrayList 인 arr 의 요소들 (정수 배열들) 이 각각 인덱스 1번 값을 기준으로 정렬됨
 *
 *
 * 메모리 26112 kb	 시간 412 ms
 */


public class Main {
    static int n;
    static int[][] graph;
    static List<int[]> arr;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp;
  //      arr=new ArrayList<>();
        n=Integer.parseInt(br.readLine());
        graph=new int[n][n];
        int[] nextPos;
        int x = 0,y = 0; // 상어 위치 저장할 변수
        int size=2,fish=0,time=0;

        // 우선순위큐 정의 : 거리 -> x 좌표 -> y 좌표 순
        pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2]!=o2[2]) return Integer.compare(o1[2],o2[2]);
                if(o1[0]!=o2[0]) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1[1],o2[1]);
            }
        });


        for(int i=0;i<n;i++){
            temp=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                graph[i][j]=Integer.parseInt(temp[j]);
                if(graph[i][j]==9){
                    x=i;
                    y=j;
                }// 상어 위치 저장
            }
        } // 그래프 생성

        while(true){

            bfs(x,y,size); // 먹을 수 있는 상어 위치 담아옴 (pq에 저장)

            if(pq.size()==0){ // 먹을 수 있는 고기가 없다면 엄마 상어 불러오기
                break;
            }

            nextPos=pq.poll();
            x=nextPos[0];
            y=nextPos[1];
            time+=nextPos[2]; // 거리만큼 시간 추가

            fish+=1;
            if(fish==size){
                size+=1;
                fish=0;
            }
            pq.clear(); // 고기 초기화
        } // while

        System.out.println(time);

    }

    public static void bfs(int x,int y,int size){

        int[][] visited=new int[graph.length][graph.length]; // 이미 담은 좌표를 다시 담지 않기 위해
        for(int i=0;i<graph.length;i++){
            Arrays.fill(visited[i],-1);
        }
        Deque<int[]> fish=new ArrayDeque<>();
        fish.addFirst(new int[]{x,y});
        visited[x][y]=0;
        graph[x][y]=0;

        int[] dx={0,-1,0,1}; // 왼 위 오 아
        int[] dy={-1,0,1,0};

        int currX,currY;
        int xx,yy;
        int[] temp;

        while(fish.size()!=0){
            temp=fish.removeFirst();
            currX=temp[0];
            currY=temp[1]; // 탐색할 위치

            for(int i=0;i<4;i++){
                xx=dx[i]+currX;
                yy=dy[i]+currY;

                if(xx<0 || yy<0 || xx>=graph.length || yy>=graph.length){ // out
                    continue;
                }

                if(graph[xx][yy]>size || visited[xx][yy]>0) continue; // 나보다 커서 못먹음 & 이미 확인했던 위치

                fish.addLast(new int[]{xx,yy}); // 지나갈 수 있고 아직 방문 전이므로 탐색 목록에 추가
                visited[xx][yy]=visited[currX][currY]+1;

                if(graph[xx][yy]<size && graph[xx][yy]>0){
                    pq.add(new int[]{xx,yy,visited[xx][yy]}); // 먹을 수 있는 고기 리스트에도 추가
                }
            }
        }
    }
}