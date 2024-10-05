import java.io.*;
import java.util.*;

// 도로 길이의 합이 가장 작은 사이클 찾기
// 플로이드 워셜

// 1. 그래프 만들기
// 2. 플로이드 워셜
// 3. 최소 사이클 찾기

public class Main {

    static int V,E;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        // 배열 초기화
        distance=new int[V+1][V+1];
        for(int i=0;i<=V;i++) {
            for(int j=0;j<=V;j++) {
                if(i==j) continue;
                distance[i][j]=987654321;
            }
        }

        // 1. 그래프 생성
        int a,b,c;
        for(int i=0;i<E;i++) {
            st=new StringTokenizer(br.readLine());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());
            distance[a][b]=c;
        }

        // 2. 플로이드 워셜
        floydwarshall();

        // 3. i-j-i 거리 최솟값 찾기
        int min=987654321;
        for(int i=1;i<=V;i++) {
            for(int j=1;j<=V;j++) {
                if(i==j) continue;
                else {
                    if(distance[i][j]!=987654321 && distance[j][i]!=987654321) {
                        min=Math.min(min,distance[i][j]+distance[j][i]);

                    }
                }
            }
        }

        if(min==987654321) min=-1;
        System.out.println(min);
    }


    static void floydwarshall() {

        // 경유지 - 출발지 - 도착지
        for(int k=1;k<=V;k++) {
            for(int i=1;i<=V;i++) {
                for(int j=1;j<=V;j++) {
                    if (distance[i][k]!=987654321 && distance[k][j]!=987654321) {
                        distance[i][j]=Math.min(distance[i][j],distance[i][k]+distance[k][j]);
                    }
                }
            }
        }


    }

}