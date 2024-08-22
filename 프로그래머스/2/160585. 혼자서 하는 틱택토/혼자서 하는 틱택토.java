import java.util.*;


class Solution {

    public int solution(String[] board) {
        int ocnt = 0;
        int xcnt = 0;    
        int othree = 0;
        int xthree = 0;

        // 전체 보드에서 O와 X의 개수 계산
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') ocnt++;
                if (board[i].charAt(j) == 'X') xcnt++;
            }
        }

        // 행과 열을 검사하여 3개 연속된 경우를 찾음
        for(int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                if (board[i].charAt(0) == 'O') othree++;
                if (board[i].charAt(0) == 'X') xthree++;
            }
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') othree++;
                if (board[0].charAt(i) == 'X') xthree++;
            }
        }

        // 대각선 검사
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if (board[0].charAt(0) == 'O') othree++;
            if (board[0].charAt(0) == 'X') xthree++;
        }

        if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
            if (board[0].charAt(2) == 'O') othree++;
            if (board[0].charAt(2) == 'X') xthree++;
        }

        
        // (1) O, X 의 갯수 차이 > 1 
        // (2) X 의 개수 > O 의 개수 
        // (3) O, X가 동시에 3목 X 

        
        if (xcnt > ocnt) return 0;
        if (ocnt - xcnt > 1) return 0;
        if (othree > 0 && ocnt != xcnt + 1) return 0;
        if (xthree > 0 && ocnt != xcnt) return 0;
        if (othree > 0 && xthree > 0) return 0;
        
        return 1;
    }

}