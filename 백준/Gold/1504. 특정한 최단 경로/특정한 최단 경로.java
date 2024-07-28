import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int INF = Integer.MAX_VALUE;
    static int n, e;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int[] ret1 = dijkstra(1);
        int[] retu = dijkstra(u);
        int[] retv = dijkstra(v);

        long res1 = (long) ret1[u] + retu[v] + retv[n];
        long res2 = (long) ret1[v] + retv[u] + retu[n];

        long fin = Math.min(res1, res2);
        if (fin >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(fin);
        }
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curr = current.vertex;
            int w = current.weight;

            if (distance[curr] < w) continue;

            for (Node neighbor : graph[curr]) {
                int pos = neighbor.vertex;
                int dist = neighbor.weight;

                if (w + dist < distance[pos]) {
                    distance[pos] = w + dist;
                    pq.offer(new Node(pos, distance[pos]));
                }
            }
        }
        return distance;
    }
}