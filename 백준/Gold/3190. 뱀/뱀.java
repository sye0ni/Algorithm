import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 	뱀의 영역을 ArrayDeque 에 저장하고, 움직일 때마다 꼬리와 머리 위치 관리
 *  사과 : -1, 뱀 : 1 으로 표시
 *  뱀이 사과를 먹으면 1로 바꾸기
 *  꼬리가 지나가면 0으로 돌려놓기
 */
public class Main {

    static int[][] graph;
    static int[] head;
    static Deque<int[]> snake; // 뱀 저장
    static int N; // 그래프 크기
    static Map<String,String[]> directions;  // 방향 전환 정보 저장
    static Deque<String[]> change; // 뱀의 방향 정보 저장
    static Map<String,int[]> dxdy; // 방향별 상하좌우 좌표 변화 값 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 현재 방향에서 오른쪽, 왼쪽으로 전환시 방향 저장
        directions=new HashMap<>();
        directions.put("R",new String[]{"D","U"});
        directions.put("L",new String[]{"U","D"});
        directions.put("D",new String[]{"L","R"});
        directions.put("U",new String[]{"R","L"});

        // 각 방향별 dxdy 저장
        dxdy=new HashMap<>();
        dxdy.put("R",new int[]{0,1});
        dxdy.put("L",new int[]{0,-1});
        dxdy.put("D",new int[]{1,0});
        dxdy.put("U",new int[]{-1,0});

        N=Integer.parseInt(br.readLine()); // 그래프 크기
        graph=new int[N][N];

        int cnt=Integer.parseInt(br.readLine()); // 사과
        int x,y;

        for(int i=0;i<cnt;i++){
            st=new StringTokenizer(br.readLine()," ");
            x=Integer.parseInt(st.nextToken())-1;
            y=Integer.parseInt(st.nextToken())-1;
            graph[x][y]=-1; // 사과는 -1 로 표시
        }

        cnt=Integer.parseInt(br.readLine()); // 뱀의 변환 횟수
        change=new ArrayDeque<>();
        for(int i=0;i<cnt;i++){
            st=new StringTokenizer(br.readLine()," ");
            change.addLast(new String[]{st.nextToken(),st.nextToken()});
        } // 방향 전환할 시간과 방향을 문자열로 저장

        moveSnake();

    }

    static void moveSnake(){ // 게임이 끝나는 시간 출력하고 종료
        int time=0;
        String curDir="R"; // 시작시엔 오른쪽 방향
        int[] d=new int[2];
        String[] pop;
        int[] tail;
        head=new int[]{0,0};
        graph[0][0]=1; // 뱀이 있는 곳은 1로 처리
        snake=new ArrayDeque<>();
        snake.add(new int[]{0,0});

        while(true){
            // 현재 방향에 맞는 좌표 변화 가져오기
            d=dxdy.get(curDir);
            head[0]+=d[0];
            head[1]+=d[1]; // 머리 이동 !!
            snake.addLast(new int[]{head[0],head[1]});
            time+=1;

            // out of range
            if(head[0]>=N || head[0]<0 || head[1]>=N || head[1]<0) break;
            // 몸이 겹침
            if(graph[head[0]][head[1]]==1) break;
            // 사과 있음
            if(graph[head[0]][head[1]]==-1){
                graph[head[0]][head[1]]=1; // 머리가 덮어버림
            }
            // 그냥 이동
            if(graph[head[0]][head[1]]==0){
                // 꼬리 지우기 & 머리 칠하기
                tail=snake.removeFirst();
                graph[tail[0]][tail[1]]=0;
                graph[head[0]][head[1]]=1;
            }

            // 방향을 바꿀 시간인지 확인
            if(change.size()>0 && change.peekFirst()[0].equals(Integer.toString(time))){
                pop=change.removeFirst();
                // 현재 방향 바꿔주기
                if(pop[1].equals("D")){
                    curDir=directions.get(curDir)[0];
                } // 오른쪽 전환
                else{
                    curDir=directions.get(curDir)[1];
                }
            }
        }

        System.out.println(time);
    }

}