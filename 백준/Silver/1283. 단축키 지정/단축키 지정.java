import java.io.*;
import java.util.*;

// 대문자 + 32 = 소문자

public class Main {

    static Set<Character> set=new HashSet<>(); // 대문자로 저장
    static Map<Integer,Integer> map=new HashMap<>(); // i번째 문자열의 단축키 인덱스 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        String st;
        String[] arr=new String[N];

        for(int i=0;i<N;i++) {
            st=br.readLine();
            arr[i]=st;
            findShortKey(st,i);
        }

        for(int i=0;i<N;i++) {
            Integer idx=map.get(i);
            if(idx==null) System.out.println(arr[i]);
            else {
                st="";
                st+=arr[i].substring(0,idx);
                st+="[";
                st+=arr[i].substring(idx,idx+1);
                st+="]";
                st+=arr[i].substring(idx+1);
                System.out.println(st);
            }
        }

    }

    static void findShortKey(String str, int idx) {
        StringTokenizer st=new StringTokenizer(str);  // 공백 기준으로 자르기
        boolean flag=false;
        String curr;
        char ch;
        int seq=0;
        int len=0;

        // 첫번째 문자가 가능한지 확인
        while(st.hasMoreTokens()) {
            if(flag) break;
            curr=st.nextToken();

            ch=curr.charAt(0);
            if(ch>='a' && ch<='z') ch-=32;

            if(!set.contains(ch)) {
                flag=true;
                set.add(ch);
                map.put(idx,seq+len);
            } // 단축키 설정

            seq++;
            len+=curr.length();
        }

        if(flag) return;

        for(int i=1;i<str.length();i++) {
            ch=str.charAt(i);

            if(ch==' ') continue;
            if(ch>='a' && ch<='z') ch-=32;

            if(!set.contains(ch)) {
                set.add(ch);
                map.put(idx,i);
                break;
            }
        }

    }

}