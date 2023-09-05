import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static ArrayList<Edge>[] graph;
    static boolean[] selected;
    static int sum;

    static class Edge{ // 간선 정의
        int from,to,weight;

        public Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());
        String[] temp;

        graph=new ArrayList[N];
        selected=new boolean[N];
        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
        sum=0;

        for(int i=0;i<M;i++){ // 그래프 생성
            temp=br.readLine().split(" ");
            int a=Integer.parseInt(temp[0])-1;
            int b=Integer.parseInt(temp[1])-1;
            int c=Integer.parseInt(temp[2]);
            graph[a].add(new Edge(a,b,c));
            graph[b].add(new Edge(b,a,c));
        }

        Prim();
        System.out.println(sum);
    }

    static void Prim(){

        PriorityQueue<Edge> pq=new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight,o2.weight);
            }
        }); // 간선의 가중치 오름차순으로 저장할 우선순위 큐 생성

        int edgeCnt=0; // edgeCnt=N-1 이 될 때까지 수행

        // 0번 정점 선택
        selected[0]=true;
        for(int i=0;i<graph[0].size();i++) pq.add(graph[0].get(i)); // 0번 정점에 연결된 간선들 큐에 add

        while(edgeCnt<N-1){
            Edge pop=pq.poll();
            if(selected[pop.to]) continue; // 이미 선택된 정점들을 연결하는 간선이면 다른 간선 선택
            selected[pop.to]=true;
            sum+=pop.weight;
            edgeCnt++;

            for(int i=0;i<graph[pop.to].size();i++) {
                Edge tmp=graph[pop.to].get(i);
                if(selected[tmp.to]) continue;
                pq.add(tmp);
            }
        }
    }
}