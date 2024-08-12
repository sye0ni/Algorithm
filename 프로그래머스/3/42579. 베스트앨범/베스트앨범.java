import java.util.*;

// Map<String,PriorityQueue> 로 장르별 음악 저장 -> 많이 재생된 음악 2개, 1개면 1개만 선택 
// Map<String, Integer> 로 장르별 재생 횟수 저장 
// List<Integer> 에 앨범 만들고 배열로 변환하여 반환 

// hashmap 의 value 만 가져오기 -> map.values() -> Collection 반환 
// key 가져오기 -> map.keySet() -> Set 반환 


class Solution {
    
    class Music implements Comparable<Music> {
        int play;
        int index;
        
        Music(int play,int index) {
            this.play=play;
            this.index=index;
        }
        
        @Override
        public int compareTo(Music music) {
            if(this.play==music.play) {
                return this.index-music.index; // 재생 횟수가 같으면 번호가 낮은 순 
            } 
            return music.play-this.play; // 많이 재생된 순서 
        }
    }
    
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String,PriorityQueue<Music>> dictionary=new HashMap<>();
        Map<String,Integer> playCountPerGenre=new HashMap<>();
        List<Integer> album=new ArrayList<>();
        
        String genre;
        int play;
        Music music;
        for(int i=0;i<genres.length;i++) {
            genre=genres[i];
            play=plays[i];
            
            music=new Music(play,i);
            
            if(playCountPerGenre.get(genre)==null) {
                // 장르 추가 
                playCountPerGenre.put(genre,play);
                // 음악 추가 
                PriorityQueue<Music> pq=new PriorityQueue<>();
                pq.add(music);
                dictionary.put(genre,pq);
            } 
            else {
                int currPlay=playCountPerGenre.get(genre);
                playCountPerGenre.replace(genre,currPlay+play); // 재생 시간 추가 
                PriorityQueue<Music> pq=dictionary.get(genre);  
                // 음악 추가 
                pq.add(music);
                dictionary.replace(genre,pq);
            }
        }
        
        // 1. 장르별 총 재생 횟수를 기준으로 내림차순 정렬
        List<Map.Entry<String, Integer>> genreRanking = new ArrayList<>(playCountPerGenre.entrySet());
        genreRanking.sort((a, b) -> b.getValue() - a.getValue());
        
        // 2. 상위 장르부터 해당 장르의 상위 2곡을 앨범에 추가
        for (Map.Entry<String, Integer> entry : genreRanking) {
            String currGenre = entry.getKey();
            PriorityQueue<Music> pq = dictionary.get(currGenre);

            album.add(pq.poll().index);

            if (pq.size() > 0) {
                album.add(pq.poll().index);
            }
        }
        
         // 결과 배열로 변환하여 반환
        int[] answer = new int[album.size()];
        for (int i = 0; i < album.size(); i++) {
            answer[i] = album.get(i);
        }
        
        return answer;
    }
}