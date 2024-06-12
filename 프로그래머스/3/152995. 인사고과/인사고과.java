import java.util.*;

class Solution {
    public int solution(int[][] scores) {

        List<int[]> list=new ArrayList<int[]>();
        int[] wanho=new int[]{scores[0][0],scores[0][1]}; // 완호의 점수 저장하기 
        int wanhoSum=wanho[0]+wanho[1];
        
        // 1. 빠질 멤버들 거르기 
        Arrays.sort(scores, (o1,o2)->{
            if(o1[0]!=o2[0]) {
                return o2[0]-o1[0];
            } else return o1[1]-o2[1];
        }); 

        list.add(new int[]{scores[0][0],scores[0][1]});
        
        int maxPeer=scores[0][1];
        for(int i=1;i<scores.length;i++) {
            if(scores[i][1]<maxPeer) {
                if(scores[i][0]==wanho[0] && scores[i][1]==wanho[1]) {
                    return -1; 
                } // 완호가 못 받음 
            } 
            else {
                maxPeer=scores[i][1];
                list.add(new int[]{scores[i][0],scores[i][1]});
            } 
        }
        
        
        // 2. 등수 정하기 -> 점수 합 내림차순   
        Collections.sort(list,(o1,o2)->{
            return (o2[0]+o2[1])-(o1[0]+o1[1]);
        });
        
        int rank=0; 
        int curr;
        
        for(int i=0;i<list.size();i++) {
            curr=list.get(i)[0]+list.get(i)[1]; 
            
            if(curr>wanhoSum) {
                rank++;
            }
            
            if(list.get(i)[0]==wanho[0] && list.get(i)[1]==wanho[1]) {
                break;
            }
        }
            
        return rank+1;
    }
}