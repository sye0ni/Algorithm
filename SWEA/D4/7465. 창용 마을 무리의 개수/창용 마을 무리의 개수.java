import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어 
 * union find 
 */

public class Solution {
	
	static int N;
	static int[] parents;
	 
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        int M;
        int a,b;
        
        List<Integer> list=new ArrayList<>();
        
        int tc=Integer.parseInt(br.readLine()); // tc
       
        for(int T=1;T<=tc;T++) {
        	st=new StringTokenizer(br.readLine()," ");
        	N=Integer.parseInt(st.nextToken());
        	M=Integer.parseInt(st.nextToken());
        	parents=new int[N];
        	list.clear();
        
        	for(int i=0;i<N;i++) parents[i]=i;
        	for(int i=0;i<M;i++) {
        		st=new StringTokenizer(br.readLine()," ");
        		union(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
        	} // union 
        

        	// 그룹 찾기 
        	int temp;
        	for(int i=0;i<N;i++) {
        		temp=find(i);
        		if(!list.contains(temp)) {
        			list.add(temp);
        		}
        	}
        	
        	sb.append("#"+T+" "+list.size()+"\n");
        	
        }
        
        System.out.println(sb.toString());
        
	}
	
	static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x]=find(parents[x]);
	}
	
	static void union(int x,int y) {
		x=find(x);
		y=find(y);
		
		if(x!=y) {
			if(x>y) {
				parents[x]=y;
			}
			else parents[y]=x;
		}
	}
	
}