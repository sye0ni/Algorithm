import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int min,max,mid;
        min=0;
        max=0;
        long sum=0;
        int[] arr=new int[N];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            if(arr[i]>max) max=arr[i];
        }

        while(true){

            if(min>max) break;

            sum=0;
            mid=(min+max)/2;

            for(int i=0;i<N;i++){
                if(arr[i]>mid) sum+=arr[i]-mid;
            }
            if(sum>=M){ // 더 자르기
                min=mid+1;
            }
            else{ // 덜 자르기
                max=mid-1;
            }

        }
        System.out.println(max);

    }

}