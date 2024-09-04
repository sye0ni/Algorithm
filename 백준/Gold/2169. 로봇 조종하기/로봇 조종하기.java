import java.io.*;
import java.util.*;

// 3차원 배열로 방향별 값 저장
// 위로는 이동할 수 없으니까 위->아래 순으로 채우기

public class Main {

    static int n,m;
    static int[][] area;
    static int[][][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        area=new int[n][m];
        sum=new int[n][m][3]; // 아래 왼 오

        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                area[i][j]=Integer.parseInt(st.nextToken());
                Arrays.fill(sum[i][j],Integer.MIN_VALUE);
            }
        }

        dp();
        System.out.println(Math.max(Math.max(sum[n-1][m-1][0],sum[n-1][m-1][1]),sum[n-1][m-1][2]));
    }

    // 아래 왼 오
    static void dp() {

        // 1행 채우기 (항상 왼->오)
        Arrays.fill(sum[0][0],area[0][0]);
        for(int i=1;i<m;i++) {
            sum[0][i][1]=sum[0][i-1][1]+area[0][i];
        }

        // 위 -> 아래 순서로 채워가기
        for(int i=1;i<n;i++) {

            // 1. 위에서 내려오는 경우 먼저 계산
            for(int j=0;j<m;j++) {
                sum[i][j][0]=area[i][j]+Math.max(Math.max(sum[i-1][j][0],sum[i-1][j][1]),sum[i-1][j][2]);
            }

            // 2. 왼쪽에서 오는 경우 계산
            for(int j=1;j<m;j++) {
                // 왼쪽에서 오는 경우
                // sum[i][j][1] = 왼쪽 점의 0,1 인덱스 중 최댓값
                sum[i][j][1]=area[i][j]+Math.max(sum[i][j-1][0],sum[i][j-1][1]);
            }

            // 3. 오른쪽
            for(int j=m-2;j>=0;j--) {
                sum[i][j][2]=area[i][j]+Math.max(sum[i][j+1][0],sum[i][j+1][2]);
            }

        }

    }
}