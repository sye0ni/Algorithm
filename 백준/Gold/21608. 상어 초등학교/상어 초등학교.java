import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 *
 */
public class Main {

    static class Seat implements Comparable<Seat>{
        int x,y,empty,pref;

        public Seat(int x,int y,int empty,int pref){
            this.x=x;
            this.y=y;
            this.empty=empty;
            this.pref=pref;
        }


        @Override
        public int compareTo(Seat o) {
            if(this.pref!=o.pref){
                return Integer.compare(o.pref,this.pref);
            }
            if(this.empty!=o.empty){
                return Integer.compare(o.empty,this.empty);
            }
            if(this.x!=o.x){
                return Integer.compare(o.x,this.x);
            }
            return Integer.compare(o.y,this.y);
        }
    }


    static int[][] preference;   // 학생별 선호도 조사
    static int[] sequence; // 자리 배정 순서
    static int[][] seat; // 자리
    static int N;
    static PriorityQueue<Seat> pq;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        sequence=new int[N*N]; // 자리를 배정할 순서
        preference=new int[N*N][4];
        seat=new int[N][N];
        pq=new PriorityQueue<>();
        Seat mySeat;

        for(int i=0;i<N;i++){
            Arrays.fill(seat[i],-1);
        } // 빈 자리는 -1

        for(int i=0;i<N*N;i++){
            st=new StringTokenizer(br.readLine()," ");
            sequence[i]=Integer.parseInt(st.nextToken())-1;
            for(int j=0;j<4;j++){
                preference[sequence[i]][j]=Integer.parseInt(st.nextToken())-1;
            }
        } // 목록 생성 완료

        for(int i=0;i<N*N;i++){
            pq.clear();
            // 모든 자리 보면서 seat 우선순위 큐에 add
            // 4방탐색하여 빈 자리 , 좋아하는 친구 수 찾기
            searchSeat(sequence[i]); // i 번 학생의 자리 찾기
            mySeat=pq.poll();
//            System.out.println(mySeat.x+","+ mySeat.y);
            seat[mySeat.x][mySeat.y]=sequence[i];
        }

        // 만족도 총 합 구하기
        int sum=0;
        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                cnt=0;

                if(seat[i][j]==-1) continue;
                for(int k=0;k<4;k++){ // 4방탐색
                    if(i+dx[k]<0 || i+dx[k]>=N || j+dy[k]<0 || j+dy[k]>=N) continue;
                    for(int t=0;t<4;t++){
                        if(preference[seat[i][j]][t]==seat[i+dx[k]][j+dy[k]]){
                            cnt+=1;
                        }
                    } // 좋아하는 친구 수 세기
                }

                if(cnt>0){
                    sum+=Math.pow(10,cnt-1);
                }

            }
        }
        System.out.println(sum);
    }

    static void searchSeat(int curr){ // curr 번 학생의 자리 탐색
        int emp,pre;

//        System.out.println(curr+"의 자리 찾기");
//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(seat[i]));
//        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                emp=0;
                pre=0; // 비어 있는 자리, 좋아하는 친구 있는 자리 수

                if(seat[i][j]!=-1) continue; // 현재 비어있는 자리에 대해서만 탐색

                for(int k=0;k<4;k++){ // 4방 탐색
                    if(i+dx[k]<0 || i+dx[k]>=N || j+dy[k]<0 || j+dy[k]>=N) continue;
                    if(seat[i+dx[k]][j+dy[k]]==-1){
                        emp+=1;
                        continue;
                    }
                    for(int t=0;t<4;t++){
//                        System.out.println(preference[curr][t]+"를 선호해요");
//                        System.out.println(seat[i+dx[k]][j+dy[k]]);
                        if(preference[curr][t]==seat[i+dx[k]][j+dy[k]]){
                            pre+=1;
                            break;
                        }
                    } // 좋아하는 친구인가?
                } // 4방탐색
//                System.out.println("탐색 결과: "+i+","+j+","+emp+","+pre);
                pq.add(new Seat(i,j,emp,pre));
            } // j
        } // i

    }
}