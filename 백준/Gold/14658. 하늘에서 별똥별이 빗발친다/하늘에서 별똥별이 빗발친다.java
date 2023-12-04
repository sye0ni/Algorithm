import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N,M,L,K;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        List<int[]> stars=new ArrayList<>(); // 별똥별 저장

        N=Integer.parseInt(st.nextToken()); // 가로
        M=Integer.parseInt(st.nextToken()); // 세로
        L=Integer.parseInt(st.nextToken()); // 트램펄린 길이
        K=Integer.parseInt(st.nextToken());

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            stars.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())}); // 별똥별 저장
        }

        int x,y;
        int xx,yy;
        int cnt=0;
        int max=0;
        int[] star;

        for(int i=0;i<K;i++){
            for(int j=0;j<K;j++){
                cnt=0;
                x=stars.get(i)[0];
                y=stars.get(j)[1];
                xx=x+L;
                yy=y+L; // (x,y) ~ (xx,yy)
//                if(xx<0 || xx>N || yy<0 || yy>M) continue;

                for(int k=0;k<K;k++){
                    star=stars.get(k);
                    if(star[0] >=x && star[0]<=xx && star[1]>=y && star[1]<=yy){
                        cnt++;
                    }
                }

                if(cnt>max){
                    max=cnt;
                }
            }
        }

        System.out.println(K-max);
    }
}