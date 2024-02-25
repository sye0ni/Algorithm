import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 그리디 알고리즘
 *
 * 앞에서부터 보면서
 * (1) 부호 바뀌면 바로 전환하고 그 절댓값만큼 카운트 플러스
 * (2) max와 같은 부호인데 나보다 절댓값이 크면
 * -> 그대로 진행
 * (2-1) max와 같은 부호인데 나보다 절댓값이 작으면
 * -> 나 로 max 바꾸기, 내 절댓값과 기존 max 의 절댓값을 뺀 값을 카운트에 더하기
 * (3) 0이면
 * -> max =0 인지, 현재값이 0인지 구분하여 처리
 */

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());  // 줄의 개수

        int[] curr=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            curr[i]=Integer.parseInt(st.nextToken());
        }

        int[] goal=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            goal[i]=Integer.parseInt(st.nextToken());
        }

        int cnt=0;
        int prev=goal[0]-curr[0];
        cnt+=Math.abs(prev);

        int temp;

        for(int i=1;i<n;i++) {
//            System.out.println(cnt);
            temp=goal[i]-curr[i];   // 현재 옮겨야하는 정도

            if(temp*prev<0) {    // 다른 부호
                cnt+=Math.abs(temp);
            } else if (Math.abs(prev)<Math.abs(temp)) {
                cnt+=(Math.abs(temp)-Math.abs(prev));
            }

            prev=temp;
        }

        System.out.println(cnt);
    }

}