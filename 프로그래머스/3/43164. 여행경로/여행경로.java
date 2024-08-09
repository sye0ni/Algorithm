import java.util.*; 

class Solution {
    
    List<String> route=new ArrayList<>(); // 방문 경로 저장 
    Map<String,List> map=new HashMap<>();   // 출발지 -> 도착지 리스트 저장 
    Map<String,List> index=new HashMap<>(); // 티켓별 인덱스 저장 -> 중복 가능 .. 
    boolean[] isVisited; // 방문 배열 (티켓 사용 여부)
    boolean flag=false; 
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        int cnt=0;
        
        isVisited=new boolean[tickets.length];
        
        for(int i=0;i<tickets.length;i++) {
            String departure=tickets[i][0];
            String arrival=tickets[i][1];
            List<String> list;
            List<Integer> indexList;
            
            if(map.get(departure)==null) {
                list=new ArrayList<>();
                list.add(arrival);
                map.put(departure,list);
            } else {
                list=map.get(departure);
                list.add(arrival);
                Collections.sort(list); // 항상 정렬 상태를 유지하도록 
                map.replace(departure,list);
            }
            if(index.get(departure+arrival)!=null) {
                indexList=index.get(departure+arrival);
                indexList.add(i);
                index.replace(departure+arrival,indexList);
            } else { 
                indexList=new ArrayList<>();
                indexList.add(i);
                index.put(departure+arrival,indexList);
            }
        }

        route.add("ICN");
        dfs("ICN",tickets);
        
        return route.toArray(new String[route.size()]); // list to array 
    }
    
    void dfs(String curr, String[][] tickets) {
        
        if(flag) return;
        
        if(route.size()==tickets.length+1) {
            flag=true;
            return;
        }
        
        if(map.get(curr)==null) return;
        
        // 현재 위치에서 갈 수 있는 공항 중 아직 사용하지 않은 티켓에 대해 재귀 
        List<String> arrivals=map.get(curr);
        String next;
        List<Integer> indexList;
        int idx=-1;
        
        for(int i=0;i<arrivals.size();i++){
            next=arrivals.get(i);
            indexList=index.get(curr+next);

            // 아직 사용 전인 티켓 찾기 
            for(int j=0;j<indexList.size();j++) {
                if(!isVisited[indexList.get(j)]) {
                    idx=indexList.get(j);
                    isVisited[idx]=true;
                    route.add(next);

                    dfs(next,tickets);
                    if(flag) return; 

                    isVisited[idx]=false;
                    route.remove(route.size()-1);
                }
            }
            
        }
        
    }
}