import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine()); // 빌딩의 수
        int[] arr=new int[N];
        int max=0, cnt;

        st=new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            cnt=check(arr,i);
            if(cnt>max) max=cnt;
        }

        System.out.println(max);

    }

    static int check(int[] arr,int curr) { /// curr 번 건물에서 볼 수 있는 건물 수 세서 리턴

        int cnt=0;

        double slope;
        double min=Double.MAX_VALUE;
        double max=Double.MIN_VALUE;

        // 앞쪽 확인 : 앞에서 나온 최소의 기울기보다 더 작으면 가능
        for(int i=curr-1;i>=0;i--){
            slope=(double)(arr[curr]-arr[i])/(curr-i);

            if(i==curr-1 || slope<min){
                min=slope;
                cnt++;
            }
        }
        
       	// 뒤쪽 확인 : 앞에서 나온 최대의 기울기보다 더 크면 가능
        for(int i=curr+1;i<arr.length;i++){
            slope=(double)(arr[curr]-arr[i])/(curr-i);

            if(i==curr+1 || slope>max){
                max=slope;
                cnt++;
            }
        }

        return cnt;
    }
}