import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int max=0;  // 최장 길이
        Map<Integer,Integer> map=new HashMap<>();
        int[] arr=new int[N];
        int left=0;
        int right=0;

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int temp;
        while(true){

//            System.out.println(left+"->"+right);
            if(right==N) break;

            if(map.get(arr[right])==null){
                map.put(arr[right],1);
                right++;
//                System.out.println("1번 ");
            }
            else{
                temp=map.get(arr[right]);
//                System.out.println("나의 값 : "+temp);
//                System.out.println("그때의 포인터 : "+left+"->"+right);
                temp++;
                if(temp<=K){ // 길이 연장
                    map.replace(arr[right],temp);
                    right++;
//                    System.out.println("2번 ");
                }
                else{ // left 당기기
                    while(arr[left]!=arr[right]){
                        map.replace(arr[left],map.get(arr[left])-1);
                        left++;
                    }
                    map.replace(arr[left],map.get(arr[left])-1);
                    left++;
//                    System.out.println("3번 ");
                }
            }

            if(right-left+1 > max){
//                System.out.println("최댓값 갱신 !! "+left+"->"+max);
                max=right-left;
            }

        }


        System.out.println(max);


    }

}