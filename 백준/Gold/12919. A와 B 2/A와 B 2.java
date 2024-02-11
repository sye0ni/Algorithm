import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;


/**
 * 문제를 푸는 방법을 생각하는건 어렵지 않았는데
 * 자바에는 문자열을 뒤집는 함수가 없어서 문자열을 뒤집기 위한 방법을 기억해야겠다 !
 * StringBuffer sb = new StringBuffer(string);
 * String reverseStr=sb.reverse().toString();
 */
public class Main {

    static String S;
    static String T;
    static int flag=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        S=br.readLine();
        T= br.readLine();

        changeAtoB(T,S);
        System.out.println(flag);
    }

    static void changeAtoB(String curr,String goal) {

        if(curr.equals(goal)) {
            flag=1;
            return;
        }

        if(curr.length()==1) return;

        if(curr.charAt(curr.length()-1)=='A') {
            changeAtoB(curr.substring(0,curr.length()-1),goal);
        }


        String reverseStr=new StringBuffer(curr).reverse().toString();
        if(reverseStr.charAt(reverseStr.length()-1)=='B') {
            changeAtoB(reverseStr.substring(0,reverseStr.length()-1),goal);
        }

        return;

    }

}