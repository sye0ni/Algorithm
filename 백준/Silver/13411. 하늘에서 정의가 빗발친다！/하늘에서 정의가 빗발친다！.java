import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        Map<Double,List<Integer>> map=new HashMap<>();
        N=Integer.parseInt(br.readLine());
        int X,Y,V;
        List<Integer> val;

        for(int i=1;i<=N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            X=Integer.parseInt(st.nextToken());
            Y=Integer.parseInt(st.nextToken());
            V=Integer.parseInt(st.nextToken());

            double dist=Math.sqrt(X*X+Y*Y);
            double sec=dist/V;
            if(map.containsKey(sec)){
                val=map.get(sec);
                val.add(i);
                map.replace(sec,val);
            }
            else{
                List<Integer> newList=new ArrayList<>();
                newList.add(i);
                map.put(sec,newList);
            }
        }

        List<Double> keys= new ArrayList<>(map.keySet());
        keys.sort((k1,k2)->k1.compareTo(k2));

        for(int i=0;i<keys.size();i++){
            for(int j=0;j<map.get(keys.get(i)).size();j++){
                sb.append(map.get(keys.get(i)).get(j));
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}