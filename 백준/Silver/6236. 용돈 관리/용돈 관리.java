import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] daily;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,M;
        long right=0;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        daily=new int[N];

        long left=0;
        for(int i=0;i<N;i++) {
            daily[i]=Integer.parseInt(br.readLine());
            if(daily[i]>left) left=daily[i];
         //   right+=daily[i];
        } // 매일 사용할 금액 저장

        long mid=0;
        long answer=0;
        right=10000*N;

        while(left<=right) {
            mid=(left+right)/2;

            if(count(mid,M)>M) { // 돈을 너무 자주 뽑아
               left=mid+1;
            }
            else {
                right=mid-1;
            }
        }

        System.out.println(left);
    }

    static int count(long mid,int M) {
        int cnt=1;
        long remain=mid;

        for(int i=0;i<daily.length;i++) {
            remain-=daily[i];

            if(remain<0){
                cnt++;
                remain=mid-daily[i];
            }
        }


        return cnt;
    }


}