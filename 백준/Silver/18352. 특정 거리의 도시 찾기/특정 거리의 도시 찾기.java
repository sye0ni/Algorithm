import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * bfs
 * 327812kb 1476ms
 */
public class Main {

    static ArrayList<Integer>[] graph;
    static int[] visited;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        int X=Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();
        visited=new int[N];
        Arrays.fill(visited,-1);

        graph=new ArrayList[N];
        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            graph[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
        } // 그래프 생성

        BFS(X-1);
        for(int i=0;i<N;i++) {
            if(visited[i]==K) sb.append((i+1)+"\n");
        }
        if(sb.length()==0) System.out.println(-1); // 찾는 도시가 없다면 -1 출력
        else System.out.println(sb);
    }

    static void BFS(int curr){
        ArrayDeque<Integer> queue=new ArrayDeque<>();
        queue.addLast(curr);
        visited[curr]=0;

        while(!queue.isEmpty()){

            int pop=queue.removeFirst();
            for(int i=0;i<graph[pop].size();i++){
                int c=graph[pop].get(i);
                if(visited[c]==-1){
                    visited[c]=visited[pop]+1;
                    if(visited[c]==K) continue; // 여기서 갈 수 있는 점들은 어차피 거리가 K보다 커지기 때문에 큐에 담아서 더 탐색할 필요가 없다 
                    queue.addLast(c);
                }// 방문하지 않은 점이면 큐에 추가
            }

        }
    }
}