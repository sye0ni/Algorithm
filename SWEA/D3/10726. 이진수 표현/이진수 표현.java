import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N,M;
        int tc;
        StringTokenizer st;
        int test;
        StringBuilder sb=new StringBuilder();
        tc=Integer.parseInt(br.readLine());

        for(int i=1;i<=tc;i++){
            st=new StringTokenizer(br.readLine());
            N=Long.parseLong(st.nextToken());
            M=Long.parseLong(st.nextToken());   // M의 이진수 표현의 마지막 N 비트가 모두 1인지

            test=(1<<N)-1;
            if((test&M)==test){
                sb.append("#"+i+" ON");
            } else{
                sb.append("#"+i+" OFF");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}