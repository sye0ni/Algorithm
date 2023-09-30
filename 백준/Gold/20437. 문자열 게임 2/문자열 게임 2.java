import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static Map<Character,List<Integer>> map;
    static int game3,game4;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        String str;
        List<Integer> arr;
        map=new HashMap<>();
        int K;
        int temp;
        int pre,back;

        for(int i=0;i<t;i++){   // t회 반복
            str=br.readLine();
            K=Integer.parseInt(br.readLine());
            map.clear();
            game3=Integer.MAX_VALUE;
            game4=Integer.MIN_VALUE;

            for(int j=0;j<str.length();j++){
                if(map.get(str.charAt(j))==null){
                    arr=new ArrayList<>();
                }
                else{
                    arr=map.get(str.charAt(j));
                }
                arr.add(j);
                map.put(str.charAt(j),arr);
            } // 최초에 문자열을 처음부터 끝까지 확인하여, 문자별로 등장 인덱스를 저장

            for(Character c:map.keySet()){
                if(map.get(c).size()>=K){
                    arr=map.get(c);
                    for(int j=0;j<=arr.size()-K;j++){
                        pre=j;
                        back=j+K-1;
                        temp=arr.get(back)-arr.get(pre)+1;

                        if(temp<game3) game3=temp;
                        if(temp>game4) game4=temp;
                    }
                }
            }

            if(game3==Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(game3+" "+game4+"\n");
        }
        System.out.println(sb);
    }

}