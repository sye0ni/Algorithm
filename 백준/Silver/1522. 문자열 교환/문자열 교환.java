import java.io.*;
import java.util.*;

// 너무 어렵게 생각했음 ㅜㅜ
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a_cnt=0;

        String str=br.readLine();
        int min=Integer.MAX_VALUE;
        int size=str.length();

        for(int i=0;i<size;i++) {
            if(str.charAt(i)=='a') a_cnt++;
        }

        int cnt;
        for(int i=0;i<size;i++) {
            cnt=0;
            for(int j=i;j<i+a_cnt;j++) {
                if(str.charAt(j%size)=='b') cnt++;
            }
            if(cnt<min) min=cnt;
        }

        System.out.println(min);
    }
}