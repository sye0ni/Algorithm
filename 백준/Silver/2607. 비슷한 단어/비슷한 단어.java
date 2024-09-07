import java.io.*;
import java.util.*;

// 한 문자만 더하거나, 뺐을때, 다른 문자로 바꾸었을때 같음

public class Main {

    static int cnt=0;
    static int[] standardFreq=new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String[] arr=new String[n];

        for(int i=0;i<n;i++) {
            arr[i]=br.readLine();
        }

        char ch;
        for(int i=0;i<arr[0].length();i++) {
            ch=arr[0].charAt(i);
            standardFreq[ch-'A']++;
        } // 첫번째 단어 저장

        for(int i=1;i<n;i++) {
            compare(arr[0],arr[i]);
        }

        System.out.println(cnt);
    }

    static void compare(String std, String str) {
        int[] compFreq=new int[26];
        char ch;

        for(int i=0;i<str.length();i++) {
            ch=str.charAt(i);
            compFreq[ch-'A']++;
        }

        int differences=0; // 문자 빈도 차이
        for(int i=0;i<26;i++) {
            differences+=Math.abs(standardFreq[i]-compFreq[i]);
        }
        int lenDiff=Math.abs(std.length()-str.length());
        
        // 1. 길이가 같고, 문자 하나만 교체하는 경우
        if(lenDiff==0 && differences<=2) cnt++;
        else if(lenDiff==1 && differences==1) cnt++;
    }
}