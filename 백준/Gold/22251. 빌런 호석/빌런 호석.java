import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
   static int N,K,P,X;
   static Map<Integer,String> numbers=new HashMap<>();
    static char[][] toCharArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        P=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        numbers.put(0,"1101111");
        numbers.put(1,"0001001");
        numbers.put(2,"1011110");
        numbers.put(3,"1011011");
        numbers.put(4,"0111001");
        numbers.put(5,"1110011");
        numbers.put(6,"1110111");
        numbers.put(7,"1001001");
        numbers.put(8,"1111111");
        numbers.put(9,"1111011");

        int k=0;
        int tempX=X;
        int currNum;
        int cnt=0;

        toCharArray=new char[K][7];

        // 현재 층 수인 X 를 문자 배열로 변환
        for(int j=K-1; j>=0 ; j--) {
            currNum= tempX / (int) Math.pow(10,j);
            toCharArray[k++]=numbers.get(currNum).toCharArray();
            tempX=tempX % (int)Math.pow(10,j);
        }

        k=0;
        int tempI;
        int changeCnt;
        char[][] toCharArrayTemp=new char[K][7];

        for(int i=1;i<=N;i++) {
            changeCnt=0;
            tempI=i;            // 각 숫자들을 charArray 로 변환하기
            k=0;
            for(int j=K-1; j>=0 ; j--) {
                currNum= tempI / (int) Math.pow(10,j);
                toCharArrayTemp[k++]=numbers.get(currNum).toCharArray();
                tempI=tempI % (int)Math.pow(10,j);
            }

            for(int j=0;j<K;j++) {
                for(k=0;k<7;k++) {
                    if(toCharArray[j][k]!=toCharArrayTemp[j][k]) {
                        changeCnt++;
                    }
                    if(changeCnt>P) break;
                }
                if(changeCnt>P) break;
            }

            if(changeCnt<=P && i!=X) {
                cnt++;
            }

        }

        System.out.println(cnt);
    }


}