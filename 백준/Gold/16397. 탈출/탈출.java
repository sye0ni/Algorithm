import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * bfs 활용
 */

public class Main {

    static int N,T,G;
    static Deque<int[]> queue=new ArrayDeque<>();
    static int count;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        isUsed=new boolean[100000];
        N=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());
        G=Integer.parseInt(st.nextToken());

        bfs();
        if(count==-1) System.out.println("ANG");
        else System.out.println(count);
    }

    static void bfs(){
        int[] pop;
        int level,num;
        String intTostr;
        int digit, big;

        count=-1;
        queue.add(new int[]{N,0});

        while(!queue.isEmpty()) {
            pop=queue.removeFirst();
            num=pop[0];
            level=pop[1];
            isUsed[num]=true;

            if(num==G) {
                count=level;
                break;
            }

            // 계산 및 level + 1 해보기
            if(num+1<=99999) {  // 버튼 A
                if(level+1 <= T) {
                    if(!isUsed[num+1]){
                        queue.addLast(new int[]{num+1,level+1});
                        isUsed[num+1]=true;
                    }
                }
            }
            if(num!=0 && num*2<=99999) {  // 버튼 B
                if(level+1 <= T ) {
                    intTostr=Integer.toString(num*2);
                    digit=intTostr.length();    // 자릿수
                    big= (int) ((num*2)/(Math.pow(10,digit-1))); // 가장 큰 자리 값
                    String newAdd=(big-1)+intTostr.substring(1);
                    if(!isUsed[Integer.parseInt(newAdd)]){
                        queue.addLast(new int[]{Integer.parseInt(newAdd),level+1});
                        isUsed[Integer.parseInt(newAdd)]=true;
                    }
                }
            }
        } // while 문
    }
}