import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int H,W;
        int[] height;

        st=new StringTokenizer(br.readLine());
        H=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());

        height=new int[W];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++){
            height[i]=Integer.parseInt(st.nextToken());
        }

        int sum=0;
        int left=0,right=0;

        for(int i=1;i<W-1;i++){
            left=-1;
            right=-1;
            for(int j=0;j<i;j++){
                if(height[j]>height[i]){
                    if(height[j]>left){
                        left=height[j];
                    }
                }
            } // 왼쪽 최댓값 찾기
            for(int j=i+1;j<W;j++){
                if(height[j]>height[i]){
                    if(height[j]>right){
                        right=height[j];
                    }
                }
            } // 오른쪽
//            System.out.println(i+"에서의 값:"+left+","+right);
            if(left==-1 || right==-1) continue;
            sum+=(Math.min(left,right)-height[i]);
//            System.out.println(sum);
        }

        System.out.println(sum);
    }
}