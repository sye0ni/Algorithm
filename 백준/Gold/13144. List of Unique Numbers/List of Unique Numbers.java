import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int[] arr=new int[N];
        boolean[] isVisited=new boolean[100001];
        long cnt=0;

        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) arr[i]=Integer.parseInt(st.nextToken());

        int end=0;

        for(int i=0;i<N;i++) {
            while(end<N) {
                if(isVisited[arr[end]]) break;
                isVisited[arr[end]]=true;
                end++;
            }
            cnt+=end-i;
            isVisited[arr[i]]=false;
        }

        System.out.println(cnt);

    }

}