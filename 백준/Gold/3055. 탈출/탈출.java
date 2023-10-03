import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static char[][] forest;
    static Deque<int[]> water;
    static Deque<int[]> goseum;
    static int R,C;
    static int[] dx=new int[] {-1,1,0,0};
    static int[] dy=new int[] {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        String line;

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        forest=new char[R][C];
        water=new ArrayDeque<>();
        goseum=new ArrayDeque<>();

        for(int i=0;i<R;i++) {
            line=br.readLine();
            for(int j=0;j<line.length();j++) {
                forest[i][j]=line.charAt(j);
                if(forest[i][j]=='S') { // 고슴도치 위치 저장
                    goseum.add(new int[]{i,j});
                }
                if(forest[i][j]=='*'){
                    water.add(new int[]{i,j});
                }
            }
        } // 숲 만들기

        bfs();
    }

    static void bfs(){

        int day=0;
        int size=water.size();
        int[] pop;
        int xx,yy;

        while(true){
            day++;
            size=water.size();

            // 물부터 이동
            for(int i=0;i<size;i++){
                pop=water.removeFirst();

                for (int j = 0; j < 4; j++) { // 상, 하, 좌, 우에 '.'이 있다면 물이 차올라야 하므로 다시 QUEUE에 넣음
                    xx = pop[0] + dx[j];
                    yy = pop[1] + dy[j];
                    if(isBoundary(xx,yy)) continue;
                    if(forest[xx][yy] != '.') continue;
                    forest[xx][yy] = '*';
                    water.add(new int[] {xx, yy});
                }
            }


            // 고슴도치 이동
            size=goseum.size();
            if(size==0){
                System.out.println("KAKTUS");
                return;
            }

            for(int i=0;i<size;i++){
                pop=goseum.removeFirst();
                for(int j=0;j<4;j++){
                    xx=pop[0]+dx[j];
                    yy=pop[1]+dy[j];
                    if(isBoundary(xx,yy)) continue;
                    if(forest[xx][yy]=='D'){
                        System.out.println(day);
                        return;
                    }
                    if(forest[xx][yy]!='.') continue;
                    forest[xx][yy]='S';
                    goseum.addLast(new int[]{xx,yy});
                }
            }
        }
    }

   static boolean isBoundary(int i,int y){
        return i<0 || i>=R || y<0 || y>=C;
   }
}