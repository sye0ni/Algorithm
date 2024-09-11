import java.io.*;
import java.util.*;

// 최대한 다양한 초밥 먹기

// 크기가 k 인 슬라이딩 윈도우 (0부터 n-1까지)
// 움직일때마다 초밥 종류 구하기
// 1. hashmap 에 "초밥종류":개수 저장
// 2. front 초밥 개수 빼기 -> 0개 되면 맵에서 삭제
// 3. back 초밥 개수 더하기 -> 없으면 추가
// 4. 이동 후 hashmap 크기 구하기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,d,k,c;
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        int[] sushi=new int[N];
        for(int i=0;i<N;i++) sushi[i]=Integer.parseInt(br.readLine());

        Map<Integer,Integer> map=new HashMap<>();

        // 초기 상태
        for(int i=0;i<k;i++) {
            map.put(sushi[i],map.getOrDefault(sushi[i],0)+1);
        }

        int maxType=map.size();
        if(!map.containsKey(c)) maxType++;

        int back;
        int frontCnt;
        int size;
        for(int front=0;front<=N-1;front++) {
            frontCnt=map.get(sushi[front]);
            if(frontCnt==1) {
                map.remove(sushi[front]);
            } else {
                map.put(sushi[front],frontCnt-1);
            }
            back=(front+k)%N;
            if(!map.containsKey(sushi[back])) {
                map.put(sushi[back],1);
            } else {
                map.put(sushi[back],map.get(sushi[back])+1);
            }

            // 가짓수 세기
            size=map.size();
            if(!map.containsKey(c)) size++;
            if(size>maxType) maxType=size;
        }

        System.out.println(maxType);
    }
}