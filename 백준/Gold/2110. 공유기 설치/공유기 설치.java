import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N,C;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        long[] homes=new long[N];

        // 우선 집 좌표 정렬
        for(int i=0;i<N;i++){
            homes[i]=Long.parseLong(br.readLine());
        }
        Arrays.sort(homes);

        long min=1; // 최소 거리
        long max=homes[N-1]-homes[0]; // 최대 거리
        long mid;

        while(min<=max){
            mid=(min+max)/2;

            if(countInstall(mid,homes)<C){
                max=mid-1;
            } // 모자라니까 간격 줄이기

            else{
                min=mid+1;
            } // 남으니까 간격 늘리기
        }

        System.out.println(max);
    }

    static int countInstall(long mid,long[] homes){
        int cnt=1; // 첫번째 집에는 무조건 설치함
        int pre=0;

        for(int i=1;i<homes.length;i++){
            if(homes[i]-homes[pre]>=mid){
                cnt++;
                pre=i;
            }
        }

        return cnt;
    }


}