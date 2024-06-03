import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc=Integer.parseInt(br.readLine());
        long sum;
        int[] stock;
        int max,n;

        for(int i=0;i<tc;i++) {
            n=Integer.parseInt(br.readLine());
            st=new StringTokenizer(br.readLine());
            stock=new int[n];
            sum=0;
            max=0;

            for(int j=0;j<n;j++) stock[j]=Integer.parseInt(st.nextToken());

            for(int j=n-1;j>=0;j--) {
                if(stock[j]>max) {
                    max=stock[j];
                } else {
                    sum+=max-stock[j];
                }
            }
            System.out.println(sum);
        }
    }
}