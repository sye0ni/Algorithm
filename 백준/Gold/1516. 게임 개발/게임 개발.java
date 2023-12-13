import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author 임승연
 *
 * 아이디어 : 위상정렬
 * 해결 과정
 *      * 연결된 노드들을 저장할 해시맵을 만들면서 해시맵 value에 리스트를 넣고 value 값을 변경하기 위해 replace 를 처음 써보았다
 *      * 걸리는 시간을 처리하는 부분에서 잘못되어 틀렸었다 -> 노드의 진입차수가 0이 되었을 때만 결과 값을 지정하도록 작성 해서 누락되는 경우가 있었음
 * 
 * 메모리 22500 kb	시간 316 ms
 */

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        Map<Integer,List<Integer>> nodes= new HashMap<Integer, List<Integer>>();
        List<Integer> values;

        int n=Integer.parseInt(br.readLine()); // 건물의 종류 수
        int[] time=new int[n+1]; // 소요 시간 저장 (초기값 저장)
        int[] result=new int[n+1]; // 최종 소요 시간 저장
        int[] in_degrees=new int[n+1]; // 진입차수 저장

        for(int i=1;i<=n;i++){
            nodes.put(i,new ArrayList<>());
        }

        for(int i=1;i<=n;i++){

            temp=br.readLine().split(" ");
            time[i]=Integer.parseInt(temp[0]); // 소요되는 시간 저장

            for(int j=1;j<temp.length-1;j++){ // 맨 뒤는 -1 이니까
                values=nodes.get(Integer.parseInt(temp[j]));
                values.add(i);
                nodes.replace(Integer.parseInt(temp[j]),values);
                in_degrees[i]+=1; // 진입차수 +1
            }

        } // 입력 정보 저장 완료


        // ===================================================== //

        Deque<Integer> queue=new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(in_degrees[i]==0){
                queue.addLast(i);
                result[i]=time[i];
            } // 진입차수가 0인 노드들 모두 큐에 저장
        }

        int pop;
        List<Integer> pops;

        while(true){
            if(queue.size()==0) break; // 모든 노드를 처리할 때까지 반복

            pop=queue.removeFirst(); // 진입차수가 0인 지금 처리해야하는 노드
            pops=nodes.get(pop); // 연결노드들 get

            for(int i=0;i<pops.size();i++){
                in_degrees[pops.get(i)]-=1; // 연결 노드들 진입차수 - 1
                if(in_degrees[pops.get(i)]==0){ // 그렇게 0이 된 노드들
                    queue.addLast(pops.get(i)); // 큐에 add
                }
                result[pops.get(i)]=Math.max(result[pop]+time[pops.get(i)],result[pops.get(i)]); // 기존에 저장되어 있던 값과 새롭게 계산된 값 중, 더 큰 숫자로 지정한다 -> 동시에 지어질 수 있다는 조건 때문
            }
        }

        for(int i=1;i<=n;i++){
            System.out.println(result[i]);
        }
    }
}