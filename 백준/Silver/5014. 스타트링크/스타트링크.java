import java.io.*;
import java.util.*;

// 총 F층 , S층 -> G층으로 이동
// 위로 U칸 , 아래로 D칸

public class Main {

    static int F,G,S,U,D;
    static int[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        F=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());
        G=Integer.parseInt(st.nextToken());
        U=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());

        BFS();

        if(isVisited[G]==Integer.MAX_VALUE) System.out.println("use the stairs");
        else System.out.println(isVisited[G]);
    }

    static void BFS() {
        isVisited=new int[F+1];
        Arrays.fill(isVisited, Integer.MAX_VALUE);

        Deque<Integer> queue=new ArrayDeque<>();
        queue.addLast(S);
        isVisited[S]=0;

        int curr,next;

        while(!queue.isEmpty()) {
            curr=queue.removeFirst();

            next=curr+U;
            if(next<=F && isVisited[next]>isVisited[curr]+1) {
                queue.addLast(next);
                isVisited[next]=isVisited[curr]+1;
            }

            next=curr-D;
            if(next>=1 && isVisited[next]>isVisited[curr]+1) {
                queue.addLast(next);
                isVisited[next]=isVisited[curr]+1;
            }

        }
    }

}