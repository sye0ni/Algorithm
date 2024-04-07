import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp
 * [s,e] 가 팰린드롬인지 확인하기 위해 [s+1,e-1] 을 활용
 * 길이 2~N 까지 팰린드롬 여부를 미리 모두 저장할 때,
 * 앞의 결과를 이용한다
 */

public class Main {

    static List<int[]> dp;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        int M,S,E;
        N=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        arr=new int[N];
        for(int i=0;i<N;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        // dp 배열 생성
        dp=new ArrayList<>();
        dp.add(new int[]{});
        dp.add(new int[]{});
        for(int i=2;i<=N;i++) {
            setPalindrome(i);
        }

        M=Integer.parseInt(br.readLine());
        int length;
        for(int i=0;i<M;i++) {
            st=new StringTokenizer(br.readLine());
            S=Integer.parseInt(st.nextToken())-1;
            E=Integer.parseInt(st.nextToken())-1;
            length=E-S+1;
            if(length==1) sb.append(1+"\n");
            else {
                sb.append(dp.get(length)[S]+"\n");
            }
        }

        System.out.println(sb.toString());

    }

    static void setPalindrome(int length){
        int[] tempDp=new int[N];
        Arrays.fill(tempDp,0);

        for(int i=0;i<=N-length;i++) {
            if(arr[i]==arr[i+length-1]) {
                if(length==2 || length==3) {
                    tempDp[i]=1;
                    continue;
                }
                if(dp.get(length-2)[i+1]==1) {
                    tempDp[i]=1;
                }
            }
        }

        dp.add(tempDp);
    }
}