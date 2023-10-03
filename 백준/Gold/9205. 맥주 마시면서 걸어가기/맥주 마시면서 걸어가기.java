import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        int t=Integer.parseInt(br.readLine()); // testcase
        int[][] graph;
        int n;
        List<int[]> spots=new ArrayList<>();

        for(int i=0;i<t;i++){
            n=Integer.parseInt(br.readLine());
            n=n+2;
            graph=new int[n][n];
            spots.clear();

            for(int j=0;j<n;j++){
                st=new StringTokenizer(br.readLine()," ");
                spots.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
            } // 좌표 모두 입력 받음

            for(int j=0;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    int[] a=spots.get(j);
                    int[] b=spots.get(k);

                    graph[j][k]=Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
                    graph[k][j]=graph[j][k];
                }
            } // 그래프 초기값 set

            // 경 출 도 순ㅅ ㅓ
            for(int k=0;k<n;k++){
                for(int ii=0;ii<n;ii++){
                    for(int j=0;j<n;j++){
                        if(ii==j || k==ii || k==j) continue;
                        if(graph[ii][k]<=1000 && graph[k][j]<=1000){
                            graph[ii][j]=1000;
                        }
                    }
                }
            }

            if(graph[0][n-1]<=1000) System.out.println("happy");
            else System.out.println("sad");
        }

    }

}