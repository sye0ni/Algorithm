import java.io.*;
import java.util.*;

// dp : 3차원 배열로 방향별 값 저장
// 위로는 이동할 수 없으니까 위->아래 순으로 채우기


public class Main {

    static class Node implements Comparable<Node> {
        int site;
        int distance;
        List<Integer> route;

        Node(int site, int distance, List<Integer> route) {
            this.site=site;
            this.distance=distance;
            this.route=route;
        }

        @Override
        public int compareTo(Node n) { // 거리 내림차순
            return this.distance-n.distance;
        }
    }

    static class City {
        int dest;
        int cost;

        City(int dest,int cost) {
            this.dest=dest;
            this.cost=cost;
        }
    }

    static int n,m;
    static List<City>[] map;
    static int start,end;
    static int[] dist; // 방문처리 용도로도 사용함
    static List<Integer> answer=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine()); // 도시 개수
        m=Integer.parseInt(br.readLine()); // 버스 개수
        map=new ArrayList[n+1];
        for(int i=0;i<=n;i++) map[i]=new ArrayList<City>();
        dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        int a,b,c;
        StringTokenizer st;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());
            map[a].add(new City(b,c));
        }

        st=new StringTokenizer(br.readLine());
        start=Integer.parseInt(st.nextToken());
        end=Integer.parseInt(st.nextToken());

        dijkstra();
        System.out.println(dist[end]);
        System.out.println(answer.size());
        for(int i=0;i<answer.size();i++) System.out.print(answer.get(i)+" ");
    }

    static void dijkstra(){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        dist[start]=0; // 시작점
        List<Integer> init=new ArrayList<>();
        init.add(start);
        pq.add(new Node(start,0,init));

        while(!pq.isEmpty()) {
            Node curr=pq.poll();
            
            if(curr.distance>dist[curr.site]) continue;
            
            List<City> connected=map[curr.site];
            int prevCost=curr.distance;

            for(int i=0;i<connected.size();i++) { // 연결된 땅에 대해 확인
                City next=connected.get(i);
                int nextSite=next.dest;
                int nextCost=next.cost;
                List<Integer> currRoute=curr.route;

                if(dist[nextSite] > prevCost+nextCost) {
                    List<Integer> newRoute=new ArrayList<>(currRoute);
                    newRoute.add(nextSite);

                    if(nextSite==end){
                        answer=new ArrayList<>(newRoute);
                    } else {
                        pq.add(new Node(nextSite,prevCost+nextCost,newRoute));
                    }
                    dist[nextSite]=prevCost+nextCost;
                }
            }
        }

    }
}