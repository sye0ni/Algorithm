import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 내림차순 정렬하는 방법이 생각나지 않아서 오름차순 정렬 뒤 뒤에서 K번째 큰 수 출력했습니다 ,, 
 * 
 * 1. 한 변에 있는 숫자만큼만 회전한다 
 * 2. 16진수 숫자로 변경하고 나올 수 있는 숫자들 저장 ( 중복검사 )
 */

public class Solution {

	static char[] lock;
	static int N,K;
	static List<Integer> queue;
	static HashMap<Character,Integer> map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        String line;
        
        int tc=Integer.parseInt(br.readLine()); // tc
        int size; // 한 변의 길이 
        int pow;
        char temp; // 임시 값
        
        map=new HashMap<>();
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        
        queue=new ArrayList<>();
         
        for(int T=1;T<=tc;T++) {
        	st=new StringTokenizer(br.readLine()," ");
        	N=Integer.parseInt(st.nextToken());
        	K=Integer.parseInt(st.nextToken());
        	size=N/4;
        	
    		queue.clear();
        	lock=new char[N]; 
        	line=br.readLine();
        	for(int i=0;i<line.length();i++) {
        		lock[i]=line.charAt(i);
        	} // 자물쇠 초기상태 
        	
        	for(int i=0;i<size;i++) { // 한 변에 있는 숫자의 수만큼만 반복하면 모든 경우가 나옴

        		temp=lock[N-1]; // 맨 뒤 값 저장 
        		for(int j=N-1;j>0;j--) { // 앞에서 뒤로 한칸씩 땡김 
        			lock[j]=lock[j-1];
        		}
        		lock[0]=temp;
        		// 1회전 완료 

        		int num=0;
        		int chg=0;
        		for(int j=0;j<=N-size;j+=size) { // 한 변씩 확인하며 숫자로 바꿔주기 
        			pow=size-1;
        			num=0;
        			for(int k=j;k<j+size;k++) {
        				if(lock[k]>='0' && lock[k]<='9') { // 숫자라면
        					num+=(lock[k]-'0')*Math.pow(16, pow);
        				} 
        				else { // 16진수 문자라면 
        					chg=map.get(lock[k]);
        					num+=(lock[k]-'A'+10)*Math.pow(16,pow);
        				}
        				pow--;
        			}
        			if(!queue.contains(num)) {
        				queue.add(num);
        			}
        		}
        	}
        	
        	Collections.sort(queue);

        	sb.append("#"+T+" "+queue.get(queue.size()-K)+"\n");
        }
        
        System.out.println(sb.toString());
	}
}