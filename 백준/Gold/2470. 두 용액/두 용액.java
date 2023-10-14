import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;
        long sum,min=Long.MAX_VALUE;
        long[] solution;

        N=Integer.parseInt(br.readLine());
        solution=new long[N];
        int left,right,saveLeft=0,saveRight=0;

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            solution[i]=Long.parseLong(st.nextToken());
        }
        Arrays.sort(solution); // 오름차순 정렬

        left=0;
        right=N-1;

        while(left<right){
         //   System.out.println(left+","+right);

            sum=solution[left]+solution[right];

            if(Math.abs(sum)<min) { // 최솟값 변경
                min=Math.abs(sum);
                saveLeft=left;
                saveRight=right;
            }

            if(sum<0){ // 왼쪽에 더 쏠려있음
                left++;
            }
            else if(sum>0){
                right--;
            }
            else{ // sum==0
                saveLeft=left;
                saveRight=right;
                break;
            }
        }

        System.out.println(solution[saveLeft]+" "+solution[saveRight]);
    }


}