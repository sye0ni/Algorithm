import java.io.*;
import java.util.*;

// 1. 1번 스위치 누르기
// 1번 전구가 다른 상태면 2번 전구 무조건 눌러야하고,
// 2번 전구가 다른 상태면 3번 전구 무조건 눌러야 하고,,
// 이런 식으로 한 칸 뒤를 보면서 끝까지 가기

// 2. 1번 스위치 안누르기
// 마찬가지...

public class Main {

    static int cnt;
    static String goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N;
        N=Integer.parseInt(st.nextToken());

        String curr=br.readLine();
        goal=br.readLine();

        int min=Integer.MAX_VALUE;
        char[] copyArr=curr.toCharArray();

        // 1. 1번 스위치 누르기
        if(copyArr[0]=='0') copyArr[0]='1';
        else if(copyArr[0]=='1') copyArr[0]='0';
        if(copyArr[1]=='0') copyArr[1]='1';
        else if(copyArr[1]=='1') copyArr[1]='0';
        cnt=1;

        copyArr=translate(copyArr);

        if(new String(copyArr).equals(goal)) {
            if(cnt<min) min=cnt;
        }

        // 2. 1번 스위치 안누르기
        copyArr=curr.toCharArray();
        cnt=0;
        copyArr=translate(copyArr);

        if(new String(copyArr).equals(goal)) {
            if(cnt<min) min=cnt;
        }

        if(min==Integer.MAX_VALUE) min=-1;
        System.out.println(min);
    }

    static char[] translate(char[] arr) {

        for(int i=1;i<arr.length;i++) {
            if(goal.charAt(i-1)!=arr[i-1]) { // i-1 , i , i+1 상태 다 변경
                cnt++;
                if(arr[i-1]=='0') arr[i-1]='1';
                else if(arr[i-1]=='1') arr[i-1]='0';
                if(arr[i]=='0') arr[i]='1';
                else if(arr[i]=='1') arr[i]='0';
                if(i==arr.length-1) continue;
                if(arr[i+1]=='0') arr[i+1]='1';
                else if(arr[i+1]=='1') arr[i+1]='0';
            }
        }

        return arr;
    }

}