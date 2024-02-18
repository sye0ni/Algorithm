import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 모든 점 사이의 최단 경로를 구하는 floyd warshall
 */
public class Main {

    static double[][] distance;
    static double[][] distanceNaeilro;
    static Map<String,Integer> cityNum;
    static String[] travel;
    static int N,M;

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int R;
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken()); // 도시의 수
        R=Integer.parseInt(st.nextToken()); // 내일로 가격

        cityNum=new HashMap<>();

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) {
            String name=st.nextToken();
            cityNum.put(name,i);
        }   // 도시 입력 받고 고유 번호 지정하여 저장

        distance=new double[N][N];
        distanceNaeilro=new double[N][N];

        for(int i=0;i<N;i++) {
            Arrays.fill(distance[i],Double.MAX_VALUE);
            Arrays.fill(distanceNaeilro[i],Double.MAX_VALUE);
            distance[i][i]=0;
            distanceNaeilro[i][i]=0;
        }

        M=Integer.parseInt(br.readLine()); // 여행할 도시
        travel=new String[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            travel[i]=st.nextToken();
        }   // 여행 경로 저장

        int K=Integer.parseInt(br.readLine());  // 교통수단의 수

        String type,start,end;
        int cost;

        for(int i=0;i<K;i++) {
            st=new StringTokenizer(br.readLine());

            type=st.nextToken();
            start=st.nextToken();
            end=st.nextToken();
            cost=Integer.parseInt(st.nextToken());

            distance[cityNum.get(start)][cityNum.get(end)]=Math.min(cost,distance[cityNum.get(start)][cityNum.get(end)]);
            distance[cityNum.get(end)][cityNum.get(start)]=Math.min(cost,distance[cityNum.get(end)][cityNum.get(start)]);
            distanceNaeilro[cityNum.get(start)][cityNum.get(end)]=Math.min(cost,distanceNaeilro[cityNum.get(start)][cityNum.get(end)]);
            distanceNaeilro[cityNum.get(end)][cityNum.get(start)]=Math.min(cost,distanceNaeilro[cityNum.get(end)][cityNum.get(start)]);

            if(type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun")) {
                distanceNaeilro[cityNum.get(start)][cityNum.get(end)]=0;
                distanceNaeilro[cityNum.get(end)][cityNum.get(start)]=0;
            }
            else if(type.equals("S-Train") || type.equals("V-Train")) {
                distanceNaeilro[cityNum.get(start)][cityNum.get(end)]=Math.min(cost/2.0 , distanceNaeilro[cityNum.get(start)][cityNum.get(end)]);
                distanceNaeilro[cityNum.get(end)][cityNum.get(start)]=Math.min(cost/2.0, distanceNaeilro[cityNum.get(end)][cityNum.get(start)]);
            }
        }   // 비용 입력 완료


        double costFirst=floyd_warshall(distance);
        double costSecond=floyd_warshall(distanceNaeilro) + R;

        if(costFirst > costSecond) System.out.println("Yes");
        else System.out.println("No");
    }

    static double floyd_warshall(double[][] arr) {

        for(int middle=0;middle<N;middle++) {  // 경유지
            for(int start=0;start<N;start++) {
                for(int end=0;end<N;end++) {
                    if(arr[start][middle]==Double.MAX_VALUE || arr[middle][end]==Double.MAX_VALUE) continue;
                    arr[start][end]=Math.min(arr[start][end],arr[start][middle]+arr[middle][end]);
                }
            }
        }
        String currStart, currEnd;
        double sum=0;
        double temp=0;

        for(int i=0;i<travel.length-1;i++) {
            currStart=travel[i];
            currEnd=travel[i+1];
            temp=arr[cityNum.get(currStart)][cityNum.get(currEnd)];
            if(temp==Double.MAX_VALUE) {
                sum=Double.MAX_VALUE;
                break;
            }
            sum+=temp;
        }
        return sum;
    }
}