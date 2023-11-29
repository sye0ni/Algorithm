import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        long[] size=new long[N-1]; // 도로의 길이
        long[] price=new long[N];
        long min=Long.MIN_VALUE;
        long sum=0;

        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N-1;i++){
            size[i]=Long.parseLong(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            price[i]=Long.parseLong(st.nextToken());
        }
        min=price[0];
        sum=min*size[0];

        for(int i=1;i<N-1;i++){
            if(min>price[i]){
                min=price[i];
            }
            sum+=min*size[i];
        }
        // 마지막 값 더해주기
//        sum+=min*size[N-2];
        System.out.println(sum);
    }


}