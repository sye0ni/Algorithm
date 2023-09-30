import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * nCn/2 를 통해 우선 팀을 나눈다.
 * 나눈 뒤, 가능한 모든 조합을 구하여 계산
 * 20C10 이 18만 정도로 시간내에 가능하다
 */
public class Main {

    static int[][] graph;
    static int N;
    static List<Integer> comb,notComb;
    static List<Integer> subComb,subNotComb;
    static boolean[] visited,subVisited;
    static int min,sumComb,sumNotComb;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        comb=new ArrayList<>();
        notComb=new ArrayList<>();
        subComb=new ArrayList<>();
        subNotComb=new ArrayList<>();
        min=Integer.MAX_VALUE;

        N=Integer.parseInt(br.readLine());
        graph=new int[N][N];
        visited=new boolean[N];
        subVisited=new boolean[N/2];
        sumComb=0;
        sumNotComb=0;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        } // 그래프 생성

        combination(0); // N/2 까지 뽑기
        System.out.println(min);
    }

    static void combination(int cnt){
        if(cnt==N/2){
            // 뽑히지 않은 팀 생성
            notComb.clear();
            for(int i=0;i<N;i++){
                if(!visited[i]){
                    notComb.add(i);
                }
            }

            // 능력치 차이 계산 -> 각 팀에서 가능한 조합 모두 구하여 값 더하기
            sumComb=0;
            sumNotComb=0;
            subComb.clear();
            subVisited=new boolean[N/2];
            combination2(0);
            if(Math.abs(sumComb-sumNotComb)<min){
                min=Math.abs(sumComb-sumNotComb);
            }
            return;
        }

        for(int i=cnt;i<N;i++){
            if(comb.size()>0 && comb.get(comb.size()-1)>=i) continue;
            comb.add(i);
            visited[i]=true;
            combination(cnt+1);
            visited[comb.get(comb.size()-1)]=false;
            comb.remove(comb.size()-1);
        }
    }

    static void combination2(int cnt){ // n/2C2 가능한 조합 모두 뽑
        if(cnt==2){
            int a=subComb.get(0);
            int b= subComb.get(1);

            sumComb+=graph[comb.get(a)][comb.get(b)];
            sumComb+=graph[comb.get(b)][comb.get(a)];

            sumNotComb+=graph[notComb.get(a)][notComb.get(b)];
            sumNotComb+=graph[notComb.get(b)][notComb.get(a)];

            return;
        }

        for(int i=cnt;i<N/2;i++){
            if(subComb.size()>0 && subComb.get(subComb.size()-1)>=i) continue;
            subComb.add(i);
            subVisited[i]=true;
            combination2(cnt+1);
            subVisited[subComb.get(subComb.size()-1)]=false;
            subComb.remove(subComb.size()-1);
        }
    }
}