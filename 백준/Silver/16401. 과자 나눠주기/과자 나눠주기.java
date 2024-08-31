import java.io.*;
import java.util.*;

// M명의 조카, N개의 과자
// 모든 조카에게 같은 길이의 최대한 긴 과자
// 과자를 나눌 수는 있지만 합칠 수는 없음

// 과자의 길이 1 ~ 최댓값 가능
// upper bound

public class Main {

    static int M,N;
    static int[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        snacks=new int[N];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            snacks[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snacks);
        int left=1;
        int right=snacks[N-1];
        int mid;

        while(left<=right) {
            mid=(left+right)/2;

            if(distribute(mid)>=M) { // 길이 더 늘리기
                left=mid+1;
            } else {
                right=mid-1;
            }
        }

        if(right<1) right=0;
        System.out.println(right);
    }

    static int distribute(int len) {
        int cnt=0;

        for(int i=N-1;i>=0;i--) {
            cnt+=(snacks[i]/len);
            if(cnt>=M) break;
        }

        return cnt;
    }
}