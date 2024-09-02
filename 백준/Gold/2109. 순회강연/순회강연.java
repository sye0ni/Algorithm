import java.io.*;
import java.util.*;

// n개의 대학, 하루에 한 곳
// d일 내 -> p 만큼 지불

// 최대로 벌 수 있는 돈 구하기
// 50 10 20 30
// 2  1  2  1
// => 최대한 빽빽하게 큰 값으로 채우기
// p 값 내림차순, d값 내림차순

public class Main {

    static class request implements Comparable<request> {

        int p,d;
        request(int p,int d) {
            this.p=p;
            this.d=d;
        }

        @Override
        public int compareTo(request r) {
            if(this.p==r.p) {
                return r.d-this.d;
            }
            return r.p-this.p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        PriorityQueue<request> pq=new PriorityQueue<>();
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());

        int p,d;
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            p=Integer.parseInt(st.nextToken());
            d=Integer.parseInt(st.nextToken());
            pq.add(new request(p,d));
        }

        long sum=0;
        request pop;
        boolean[] isVisited=new boolean[10001];
        int idx;
        while(!pq.isEmpty()) {
            pop=pq.poll();
            if(!isVisited[pop.d]) {
                isVisited[pop.d]=true;
                sum+=pop.p;
            } else {
                idx=pop.d-1;
                while(idx>0) {
                    if(!isVisited[idx]) {
                        isVisited[idx]=true;
                        sum+=pop.p;
                        break;
                    }
                    idx--;
                }
            }
        }

        System.out.println(sum);
    }

}