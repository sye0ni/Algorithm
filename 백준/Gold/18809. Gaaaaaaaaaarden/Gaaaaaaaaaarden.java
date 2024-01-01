import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n,m,g,r;
    static int[] comb;
    static int[] comb2;
    static int[] red;
    static int[] green;
    static int[][] garden;
    static List<int[]> avail; // 배양액을 뿌릴 수 있는 위치
    static int flower; // 피울 수 있는 꽃의 최대 개수 저장

    static class spot{
        int day;
        String color; // r , g , f (꽃)

        spot(int day, String color){
            this.day=day;
            this.color=color;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        g=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());

        comb=new int[g+r];
        comb2=new int[r];
        green=new int[g];
        red=new int[r];
        garden=new int[n][m];
        avail=new ArrayList<>();
        flower=0;


        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                garden[i][j]=Integer.parseInt(st.nextToken());
                if(garden[i][j]==2){
                    avail.add(new int[]{i,j});
                }
            }
        } // 입력

        combination(0,0);

        System.out.println(flower);


    }

    static void combination(int curr,int cnt){
        // r+g 개 뽑기 !!
        if(cnt==r+g){
            // 다시 comb 에서 r 개 뽑기 !! -> r, g 뿌릴 위치 결정 (인덱스 저장...)
            Arrays.fill(red,0);
            Arrays.fill(green,0);
            combination2(0,0);
            return;
        }

        for(int i=curr;i<avail.size();i++){
            comb[cnt]=i;
            combination(i+1,cnt+1);
        }

    }
    static void combination2(int curr, int cnt) {
        // r 개 뽑기 !! -> red, green 나눠주기
        if (cnt == r) {
            boolean[] chosen = new boolean[r + g]; // r+g 개의 조합을 저장할 배열
            Arrays.fill(chosen, false);

            for (int i = 0; i < r; i++) {
                chosen[comb2[i]] = true;
            }

            int redIdx = 0;
            int greenIdx = 0;

            for (int i = 0; i < r + g; i++) {
                if (chosen[i]) {
                    red[redIdx++]=comb[i];
                } else {
                    green[greenIdx++] =comb[i];
                }
            }

            // red, green 그룹 분류
            bfs();

            return;
        }

        for (int i = curr; i < r + g; i++) {
            comb2[cnt] = i;
            combination2(i + 1, cnt + 1);
        }
    }



    static void bfs(){
        Deque<int[]> queue=new ArrayDeque<>();  // 확산할 좌표 저장
        int cnt=0; // 이번 탐색에서 꽃의 개수 세기
        spot[][] used=new spot[n][m];
        int[] temp;
        int x,y;
        int[] dx=new int[]{-1,1,0,0};
        int[] dy=new int[]{0,0,-1,1};

        // spot 그래프 초기화 (0초 상태 ...)
        // red 배양액 뿌리기
        for(int i=0;i<red.length;i++){
            temp=avail.get(red[i]);
            x=temp[0];
            y=temp[1];
            used[x][y]=new spot(0,"R");
            queue.add(new int[]{x,y});
        }

        // green 배양액 뿌리기
        for(int i=0;i<green.length;i++){
            temp=avail.get(green[i]);
            x=temp[0];
            y=temp[1];
            used[x][y]=new spot(0,"G");
            queue.add(new int[]{x,y});
        }

        spot pop, pre;
        String color;
        int day;
        int xx,yy;

        while(!queue.isEmpty()){
            temp=queue.removeFirst();
            x=temp[0];
            y=temp[1];
            pop=used[x][y];     // 확산할 배양액 저장
            day=pop.day;
            color=pop.color;
            if(color.equals("F")) continue; // 이미 꽃피운 자리 pass

            for(int i=0;i<4;i++){
                xx=x+dx[i];
                yy=y+dy[i];

                if(xx<0 || xx>=n || yy<0 || yy>=m) continue;
                if(garden[xx][yy]==0) continue; // 호수
                if(used[xx][yy]==null) { // 확산
                    used[xx][yy]=new spot(day+1,color);
                    queue.addLast(new int[]{xx,yy});
                }
                else{   // 다른 배양액이 뿌려짐 ( 같은색 or 다른 색 )
                    pre=used[xx][yy];
                    if(!pre.color.equals(color) && !pre.color.equals("F")){   // 다른 색의 배양액?
                        if(pre.day==day+1){ // 꽃 피우기
                            cnt++;
                            used[xx][yy]=new spot(day+1,"F");
                        }
                    }
                }

            }

        }

        if(cnt>flower){
            flower=cnt;
        }
    }
}