import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위에서부터 시작점으로 지정하여, 최대한 위쪽 경로로 연결할 수 있는 경우에 경로를 생성한다
 *
 */
public class Main {
    static int R,C;
    static char[][] graph;
    static int cnt;
    static List<int[]> path;
    static boolean[][] isVisited; // dfs 방문 처리
    static boolean[][] installed; // 파이프 설치
    static int[] dx=new int[]{-1,0,1}; // y 는 항상 전진
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        graph=new char[R][C];
        isVisited=new boolean[R][C];
        installed=new boolean[R][C];
        String str;
        cnt=0;
        path=new ArrayList<>();

        for(int i=0;i<R;i++){
            str=br.readLine();
            for(int j=0;j<C;j++){
                graph[i][j]=str.charAt(j);
            }
        } // 그래프 생성

        for(int i=0;i<R;i++){
            if(dfs(i,0)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static boolean dfs(int x,int y){

        if(y==C-1){ // 연결 성공
            return true;
        }

        int xx,yy;
        for(int i=0;i<3;i++){
            xx=x+dx[i];
            yy=y+1;

            if(xx<0 || xx>=R || yy<0 || yy>=C) continue;
            if(isVisited[xx][yy]) continue;
            if(graph[xx][yy]=='x') continue;

            isVisited[xx][yy]=true;
            if(dfs(xx,yy)){
                return true;
            }
        }
        return false;

    }
}