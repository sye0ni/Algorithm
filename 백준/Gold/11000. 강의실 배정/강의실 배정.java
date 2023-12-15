import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1[1],o2[1]);
            }
        });

        PriorityQueue<Integer> addPq=new PriorityQueue<>();

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            pq.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        } // 입력 받아 pq 에 삽입

        int temp;
        int cnt=0; // 필요한 강의실 개수
        int[] input;
        
        // pq 에는 시작 시간 오름차순, 종료 시간 오름차순 정렬
        while(!pq.isEmpty()){
            input=pq.poll(); // 추가할 강의

            if(addPq.size()>0){
                temp=addPq.peek();
                if(temp<=input[0]) { // 먼저 끝나면?
                    addPq.poll();
                    cnt--;
                }
            }

            addPq.add(input[1]); // 지금 강의 담기
            cnt++;

        }

        System.out.println(cnt);
    }

}