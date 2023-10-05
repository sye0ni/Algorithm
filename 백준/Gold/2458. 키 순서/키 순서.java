import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
 
    static List<Integer>[] graph;
    static int[] degrees;
    static int[] in;
    static int[] out;
    static boolean[] isVisited;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
         
       
        int n,m,a,b;
        int cnt=0;
        int result=0;
         
         
    st=new StringTokenizer(br.readLine()," ");
    n=Integer.parseInt(st.nextToken()); // 사람 수 
    in=new int[n];
    out=new int[n]; // 진출차수 저장 
    degrees=new int[n]; // topology sort 용 
    cnt=0;
    graph=new ArrayList[n];
    isVisited=new boolean[n];
    result=0;
     
    for(int i=0;i<n;i++) {
        graph[i]=new ArrayList<>();
    }
     
    m=Integer.parseInt(st.nextToken()); // 비교 횟수 
     
    for(int i=0;i<m;i++) {
        st=new StringTokenizer(br.readLine()," ");
        a=Integer.parseInt(st.nextToken())-1;
        b=Integer.parseInt(st.nextToken())-1; // a<b
        out[a]++;
        graph[a].add(b); 
    } // 그래프 생성
             
            for(int i=0;i<n;i++) { 
                isVisited[i]=true;
                cnt=0;
                dfs(i);
                for(int j=0;j<n;j++) {
                    if(isVisited[j] && i!=j) {
                        cnt++;
                        in[j]++;
                    }       
                }
                out[i]=cnt;
                Arrays.fill(isVisited, false);
            }
 
            for(int i=0;i<n;i++) {
                if(in[i]+out[i]==n-1) {
                    result++;
                }
            }
            System.out.println(result);
        }
         

 
    static void dfs(int curr) {
         
        for(int i=0;i<graph[curr].size();i++) {
            if(isVisited[graph[curr].get(i)]) continue;
            isVisited[graph[curr].get(i)]=true;
            dfs(graph[curr].get(i));
        }
    }
}