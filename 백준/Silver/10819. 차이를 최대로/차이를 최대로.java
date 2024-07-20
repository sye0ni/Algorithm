import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 순열

public class Main {

    static int max=-1;
    static int n;
    static int[] arr;
    static boolean[] isVisited;
    static int[] perm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        isVisited=new boolean[n];
        perm=new int[n];

        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        permutation(0);
        System.out.println(max);
    }

    static void permutation(int index) { // 현재 인덱스

        if (index == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(perm[i] - perm[i + 1]);
            }
            if (sum > max) {
                max = sum;
            }
            return;
        }

        for(int i=0;i<n;i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                perm[index] = arr[i];
                permutation(index + 1);
                isVisited[i] = false;
            }
        }
    }
}