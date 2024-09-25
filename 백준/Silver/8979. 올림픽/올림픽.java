import java.io.*;
import java.util.*;

// 금 > 은 > 동
// 동일한 메달은 동일 등수
// 국가 클래스 정의 : 금, 은, 동 개수
// 국가 담은 우선순위큐 정의

public class Main {

    static class Nation implements Comparable<Nation>{

        int num;
        int gold;
        int silver;
        int bronze;


        Nation(int num, int gold,int silver,int bronze) {
            this.num=num;
            this.gold=gold;
            this.silver=silver;
            this.bronze=bronze;
        }


        @Override
        public int compareTo(Nation n){
            if(this.gold!=n.gold) {
                return n.gold-this.gold;
            } else if(this.silver!=n.silver) {
                return n.silver-this.silver;
            }
            return n.bronze-this.bronze;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        PriorityQueue<Nation> pq=new PriorityQueue<>();

        int num,gold,silver,bronze;
        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            num=Integer.parseInt(st.nextToken());
            gold=Integer.parseInt(st.nextToken());
            silver=Integer.parseInt(st.nextToken());
            bronze=Integer.parseInt(st.nextToken());
            pq.add(new Nation(num,gold,silver,bronze));
        }

        Nation curr;
        Nation prev=null;

        int rank=1;
        int cnt=0;
        while(!pq.isEmpty()) {
            curr=pq.poll();
            cnt++;
            if(prev!=null) {
                if(!checkIfEqual(curr,prev)) { // 동순위 아니면 랭킹 올려주기
                    rank=cnt;
                }
            }
            if(curr.num==K) {
                System.out.println(rank);
                break;
            }
            prev=curr;
        }


    }

    static boolean checkIfEqual(Nation curr,Nation prev) {
        if(curr.gold==prev.gold && curr.silver==prev.silver && curr.bronze==prev.bronze) {
            return true;
        }
        return false;
    }
}