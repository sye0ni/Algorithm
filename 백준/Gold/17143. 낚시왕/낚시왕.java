import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static class Shark{ // 상어 정의
        int x,y;
        int direction;
        int speed;
        int size;
        boolean die;

        public Shark(int x,int y,int speed, int direction, int size,boolean die) { //
            super();
            this.x=x;
            this.y=y;
            this.direction = direction;
            this.speed = speed;
            this.size = size;
            this.die = die;
        }
    }

    static int catchSize;
    static int r,c;
    static ArrayList<Shark> sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp=br.readLine().split(" ");
        r=Integer.parseInt(temp[0]);
        c=Integer.parseInt(temp[1]);
        int m=Integer.parseInt(temp[2]); // 상어 수

        sharks=new ArrayList<>();
        catchSize=0;// 잡은 상어의 크기

        for(int i=0;i<m;i++) { // 상어 저장
            temp=br.readLine().split(" ");
            int R=Integer.parseInt(temp[0])-1;
            int C=Integer.parseInt(temp[1])-1; // 상어의 x,y 좌표
            int s=Integer.parseInt(temp[2]);    // 속력
            int d=Integer.parseInt(temp[3]);    // 방향
            int z=Integer.parseInt(temp[4]);    // 상어 크기

            sharks.add(new Shark(R,C,s,d,z,false));
        }


        for(int j=0;j<c;j++) { // 사람 이동
            int Minidx=Integer.MAX_VALUE;
            int MinI=Integer.MAX_VALUE;

            for(int i=0;i<sharks.size();i++) {
                Shark sk=sharks.get(i);

                if(!sk.die && sk.y==j) {
               //     System.out.println(sk.x);
                    if(sk.x<Minidx) {
                        Minidx=sk.x;
                        MinI=i;
                    }
                } // 상어를 잡으면 걔 크기 합해주기
            } // 상어 잡자 !

            // 찾은 상어 잡기
            if(Minidx!=Integer.MAX_VALUE) {
                sharks.get(MinI).die=true;
              //  System.out.println("잡은 상어 인덱스 : "+Minidx);
             //   System.out.println("잡은 상어 : "+sharks.get(MinI).size);
                catchSize+=sharks.get(MinI).size; // 잡은 상어 크기 add
            }
            // 상어 움직여!
            moveSharks();
        }

        System.out.println(catchSize);
    }

    static void moveSharks() { // 모든 상어들을 옮겨줍니다 -> 배열 복사해서 옮기기

        int[][] copyGraph=new int[r][c];
        //    int[] ret=new int[3];

        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                copyGraph[i][j]=-1;
            }
        } // 시뮬레이션 용 추가 생성

        for(int i=0;i<sharks.size();i++) {
            Shark sk=sharks.get(i);
            if(sk.die) continue; // 이미 죽은 상어면 다음 상어

            moveOneShark(i);

            int x=sk.x;
            int y=sk.y;
            int d=sk.direction;
//            System.out.println("움직인 위치 : "+x+","+y);
//            System.out.println("바뀐 방향 : "+d);
//
//            System.out.println("움직일 좌표의 값!!: "+copyGraph[x][y]);
            if(copyGraph[x][y]!=-1) { // 움직일 좌표에 다른 상어가 있는 경우
                if(compareSize(i,copyGraph[x][y])) {
                    sk.die=true;
                } // 기존에 있던 상어가 더 크다면 변화 없어 , 지금 상어 죽여
                else{
                    sharks.get(copyGraph[x][y]).die=true;
                    copyGraph[x][y]=i;
                } // 새로운 상어가 더 크면 기존 상어 죽이고 새 상어 삽입
            }
            else{
                copyGraph[x][y]=i;
            }
         //   sk.direction=d;
        }
   //     System.out.println();
    }

    static void moveOneShark(int idx) { // 상어를 옮기자

        // 문제에서 주어진대로 상하우좌 가 1,2,3,4 인덱스를 갖도록 지정
        int[][] dirs= {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
        Shark sk=sharks.get(idx); // 움직일 상어
     //   System.out.println("움직일 상어의 크기 : "+sk.size);
        int d=sk.direction;
        int s=sk.speed;

        for(int i=0;i<s;i++) { // 상어 움직여
            // border 에서 방향 change
            if(d==1 && sk.x==0) d=2;
            else if(d==2 && sk.x==r-1) d=1;
            else if(d==3 && sk.y==c-1) d=4;
            else if(d==4 && sk.y==0) d=3;

            sk.x+=dirs[d][0];
            sk.y+=dirs[d][1]; // 현재의 방향으로 움직이기
            sk.direction=d;
        }

    }

    static boolean compareSize(int newshark,int oldshark) {

        // 기존 상어가 더 크면 true 반환
        int newSize=sharks.get(newshark).size;
        int oldSize=sharks.get(oldshark).size;

        return oldSize>newSize;
    }

}