import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * KRUSKAL 알고리즘 사용
 *
 */
public class Main {

    static class Edge implements Comparable<Edge>{
        int from,to,weight;

        public Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    static Edge[] list;
    static long sum;
    static int N; // 정점 수
    static int M;

    static int[] parent; // union find

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        int s,e,w;
        sum=0; // mst 최종 비용 저장
//        pq=new PriorityQueue<>();
        parent=new int[N];
        list=new Edge[M];

        for(int i=0;i<N;i++) parent[i]=i; // 자기 자신으로 초기화

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            s=Integer.parseInt(st.nextToken())-1;
            e=Integer.parseInt(st.nextToken())-1;
            w=Integer.parseInt(st.nextToken());
//            pq.add(new Edge(s,e,w));
            list[i]=new Edge(s,e,w);
        } // 입력 완료

        Arrays.sort(list);
        kruskal();
        System.out.println(sum);
    }

    static void kruskal(){
        int edgeCnt=0;
        Edge pop;

        for(int i=0;i<M;i++){
            pop=list[i];
            if(find(pop.to)==find(pop.from)) continue;

            union(pop.to,pop.from);
            edgeCnt+=1;
            if(edgeCnt==N-1) break;
            sum+=pop.weight;
        }
    }

    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    } // 루트 노드 찾기

    static void union(int x,int y){
        x=find(x);
        y=find(y);

        if(x>y) parent[y]=x;
        else parent[x]=y; // 작은 수 -> 큰 수로 합치기
    }

}