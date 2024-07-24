import java.util.*;

class Book implements Comparable<Book>{
    int start, end;

    public Book(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Book b){
        if(this.start != b.start){
            return Integer.compare(this.start, b.start);
        }
        else{
            return Integer.compare(this.end, b.end);
        }
    }
}

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Book[] books = new Book[book_time.length];

        for(int i = 0; i < book_time.length; i++){
            int start = Integer.parseInt(book_time[i][0].substring(0, 2)) * 60;
            start += Integer.parseInt(book_time[i][0].substring(3, 5));
            int end = Integer.parseInt(book_time[i][1].substring(0, 2)) * 60;
            end += Integer.parseInt(book_time[i][1].substring(3, 5));
            books[i] = new Book(start, end); 
        }

        Arrays.sort(books); // 시작 시간 오름차순 정렬

        for(int i = 0; i < books.length; i++) {
            if(!pq.isEmpty() && pq.peek() <= books[i].start){
                pq.poll();  
            }
            pq.add(books[i].end + 10); // 청소 시간 10분 추가
        }

        return pq.size();
    }
}
