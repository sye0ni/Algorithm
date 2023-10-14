import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static boolean[] alphabet;
    static int N,K,types;
    static int Max;
    static List<Character> chars; // antic 빼고 포함된 문자 저장
    static String[] str;
    static List<Integer> comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        alphabet=new boolean[26]; // 해당 알파벳을 사용할 수 있는지 없는지
        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        Max=0;
        types=0; // 모든 단어에 나오는 알파벳 종류 저장 ->  a,n,t,i,c 제외
        str=new String[N];
        chars=new ArrayList<>(); // 등장하는 알파벳 저장용
        comb=new ArrayList<>();

        initAlphabet();

        for(int i=0;i<N;i++){
            str[i]=br.readLine();
            str[i]=str[i].substring(4,str[i].length()-4);
            countAlphabet(str[i]);
        }

        if(K>=5) {
            if(K>types+5) { // 모든 문자를 가르쳐도 K가 클 때
                K=types+5; // K 를 낮춰준다
            }
            combination(0); // K - 5 개 뽑기
        }

        System.out.println(Max);

    }

    static void initAlphabet(){
        Arrays.fill(alphabet,false);
        // antic 담기
        alphabet['a'-'a']=true;
        alphabet['n'-'a']=true;
        alphabet['t'-'a']=true;
        alphabet['i'-'a']=true;
        alphabet['c'-'a']=true;
    }
    static void countAlphabet(String str){
        for(int i=0;i<str.length();i++){
            if(!alphabet[str.charAt(i)-'a']){
                alphabet[str.charAt(i)-'a']=true;
                types++;
                chars.add(str.charAt(i));
            }
        }
    } // 문자열에 포함된 새로운 문자 개수 세기

    static void combination(int cnt){ // 문자 인덱스 뽑기
        
        if(cnt==K-5){ // 문자열 하나하나 확인
            initAlphabet(); // antic 을 추가하기

            // 뽑은 문자들 true 로 만들기 !
            for(int i=0;i<comb.size();i++){
                alphabet[(char)chars.get(comb.get(i))-'a']=true;
            }

            int count=0,check=0;

            for(int i=0;i<str.length;i++){
                check=0;
                for(int j=0;j<str[i].length();j++){
                    if(alphabet[str[i].charAt(j)-'a']){
                        check++;
                    }
                    else{
                        break;
                    }
                }
                if(check==str[i].length()) {
                    count++;
                }
            }

            if(count>Max) {
                Max=count;
            }

            return;

        }

        for(int i=cnt;i<chars.size();i++){
            if(comb.size()>0 && comb.get(cnt-1)>=i) continue;
            comb.add(i);
            combination(cnt+1);
            comb.remove(comb.size()-1);
        }
    }

}