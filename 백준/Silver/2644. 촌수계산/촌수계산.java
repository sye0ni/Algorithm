import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int n;
    static int s,e;
    static List<Integer>[] graph;
    static int depth=-1;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        s=Integer.parseInt(st.nextToken());
        e=Integer.parseInt(st.nextToken());

        int m=Integer.parseInt(br.readLine());
        graph=new List[n+1];
        isVisited=new boolean[n+1];
        for(int i=0;i<=n;i++) graph[i]=new ArrayList<>();

        int a,b;
        for(int i=0;i<m;i++) {
            st=new StringTokenizer(br.readLine());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs();
        System.out.println(depth);
    }

    static void bfs(){

        Deque<int[]> queue=new ArrayDeque<>();
        queue.addLast(new int[]{s,0}); // 좌표와 depth 저장
        isVisited[s]=true;

        int[] temp;
        int curr,currDist,next;

        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            curr=temp[0];
            currDist=temp[1];

            if(curr==e) {
                depth=currDist;
                break;
            }

            for(int i=0;i<graph[curr].size();i++) {
                next=graph[curr].get(i);
                if(isVisited[next]) continue;
                queue.addLast(new int[]{next,currDist+1});
                isVisited[next]=true;
            }
        }

    }
}