import java.io.*;
import java.util.*;

// 가장 늦게 사용 예정인 것 (혹은 앞으로 사용할 일 없는 것) 을 제거하기

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        List<Integer>[] electronics=new ArrayList[k+1];

        for(int i=0;i<=k;i++) electronics[i]=new ArrayList<>();

        st=new StringTokenizer(br.readLine());
        int name;
        int[] sequence=new int[k];
        for(int i=0;i<k;i++) {
            name=Integer.parseInt(st.nextToken());
            electronics[name].add(i); // 순번 저장
            sequence[i]=name; // 순서 저장
        }

        Set<Integer> multi=new HashSet<>(); // 현재 사용중인 전기용품 저장
        int cnt=0;

        for(int i=0;i<k;i++) {
            if(multi.contains(sequence[i])) {
                electronics[sequence[i]].remove(0);
                continue;
            }

            if(multi.size()<n) {
                multi.add(sequence[i]);
                electronics[sequence[i]].remove(0); // 맨 앞 인덱스 삭제
            } else { // 가장 후순위 제거

                int max=-1;
                int remove=-1;
                for(int item:multi) {
                    if(electronics[item].isEmpty()) {
                        remove=item;
                        break;
                    } else if (electronics[item].get(0)>max) {
                        max=electronics[item].get(0);
                        remove=item;
                    }
                }
                cnt++;
                multi.remove(remove);
                multi.add(sequence[i]);
                electronics[sequence[i]].remove(0);
            }
        }

        System.out.println(cnt);
    }

}