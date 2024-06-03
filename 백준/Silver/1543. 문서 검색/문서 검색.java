import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String document=br.readLine();
        String word= br.readLine();
        int cnt=0;

        for(int i=0;i<=document.length()-word.length();i++) {
            if(document.charAt(i)==word.charAt(0)) {
                if(document.substring(i,i+word.length()).equals(word)) {
                    cnt++;
                    i=i+word.length()-1;
                }
            }
        }

        System.out.println(cnt);

    }


}