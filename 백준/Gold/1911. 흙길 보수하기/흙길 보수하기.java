import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());

        int[][] pool=new int[N][2]; // N 개의 웅덩이 저장

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            pool[i][0]=Integer.parseInt(st.nextToken());
            pool[i][1]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pool,(o1, o2) -> {
            return (int) (o1[0]-o2[0]);
        }); // 시작점 기준 오름차순 정렬

        int cnt=0; // 필요한 널빤지 수
        int curr=0;

        for(int[] arr:pool){

            if(arr[0]>curr){ // 시작점 초기화
                curr=arr[0];
            }

            if(arr[1]>curr){
                while(arr[1]>curr){
                    curr+=L;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }

}