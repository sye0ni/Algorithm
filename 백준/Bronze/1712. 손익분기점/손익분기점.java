import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A,B,C;
        A=Long.parseLong(st.nextToken());
        B=Long.parseLong(st.nextToken());
        C=Long.parseLong(st.nextToken());
        long x;

        if(B==C){
            x=-1;
        }
        else{
            x=(long)(A/(C-B));
            if(B>C) x=-1;
            else x++;
        }

        System.out.println(x);
    }
}