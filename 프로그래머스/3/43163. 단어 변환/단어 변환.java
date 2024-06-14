import java.util.*;

class Solution {
    
    static boolean[] isVisited;
    static List<Integer>[] graph;
    static int answer;
    static int destination;
    
    public int solution(String begin, String target, String[] words) {
        
        graph=new ArrayList[words.length+1];
        for(int i=0;i<=words.length;i++) {
            graph[i]=new ArrayList<Integer>();
        }
        
        String[] realWords=new String[words.length+1];
        isVisited=new boolean[realWords.length];
        realWords[0]=begin;
        destination=-1;
        for(int i=1;i<=words.length;i++) {
            realWords[i]=words[i-1];
            if(realWords[i].equals(target)) destination=i;
        }
        
        // 각 노드간의 연관관계 찾기 
        for(int i=0;i<realWords.length;i++) {
            for(int j=i+1;j<realWords.length;j++) {
                if(check(realWords[i],realWords[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        System.out.println(destination);
        if(destination==-1) return 0;
        
        bfs(0); 
        
        return answer;
    }
    
    static boolean check(String a,String b) {
        if(a.length()!=b.length()) return false;
        
        int cnt=0;
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)!=b.charAt(i)) cnt++;
            if(cnt>1) break; 
        }
        
        return cnt==1;
    }
    
    static void bfs(int start) {
        
        Deque<int[]> queue=new ArrayDeque<>();
        queue.addLast(new int[]{start,0});
        isVisited[start]=true;
        int curr, next, depth;
        int[] temp;
        
        while(!queue.isEmpty()) {
            temp=queue.removeFirst();
            curr=temp[0];
            depth=temp[1];
            
            if(curr==destination){
                answer=depth;
                break;
            } 
            
            for(int i=0;i<graph[curr].size();i++) {
                next=graph[curr].get(i);
                if(!isVisited[next]) {
                    queue.addLast(new int[]{next,depth+1});
                    isVisited[next]=true;
                }
            }
        }
    }
}