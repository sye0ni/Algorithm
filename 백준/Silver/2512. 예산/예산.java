import java.io.*;
import java.util.*;

// parametric search
// 1~M 탐색하며 그 가격으로 가능한지 확인하기

public class Main {

    static int[] areas;
    static int N,M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        N=Integer.parseInt(st.nextToken());

        areas=new int[N];
        st=new StringTokenizer(br.readLine());

        int right=0; // 최대 금액 => 최댓값

        for(int i=0;i<areas.length;i++) {
            areas[i]=Integer.parseInt(st.nextToken());
            if(areas[i]>right) right=areas[i];
        }
        M=Integer.parseInt(br.readLine());
        
        int left=1;
        int mid=0;
        
        while(left<=right) {
            mid=(left+right)/2;
            
            if(distribute(mid)) { // 분배 가능
                left=mid+1;
            } else {
                right=mid-1;
            }
        }

        System.out.println(right);
    }
    
    static boolean distribute(int mid) {
        boolean flag=false;
        long sum=0;
        
        for(int i=0;i<N;i++) {
            if(areas[i]<mid) sum+=areas[i];
            else sum+=mid;
        }
        
        if(sum<=M) flag=true;
        return flag;
    }
}