import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sudoku;
    static List<Integer> zeros;
    static int check;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line;

        sudoku=new int[9][9];
        zeros=new ArrayList<>();
        check=0;

        for(int i=0;i<9;i++){
            line=br.readLine();
            for(int j=0;j<9;j++){
                sudoku[i][j]=(int)line.charAt(j)-'0';
                if(sudoku[i][j]==0){
                    zeros.add(i*9+j);
                }
            } // 스도쿠 입력 받아 채우기
        }

        backtracking(0);    // 가장 앞의 zeros 부터 채우기
    }

    static void backtracking(int idx){

      //  System.out.println(idx);
        if(check==1) return; // 한 번만 실행

        if(idx==zeros.size()){
            // print
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(sudoku[i][j]);
                }
                System.out.println();
            }
            check=1;
            return;
        }   // 다 채움

        for(int i=1;i<=9;i++){

            if(!checkValid(idx,i)){
              //  System.out.println("조건 성립 안함");
                continue;
            } // 스도쿠 조건 성립 x

            int row=zeros.get(idx)/9;
            int col=zeros.get(idx)%9;   // 숫자를 넣을 row, col 구하기
            sudoku[row][col]=i;         // 채우기
            backtracking(idx+1);
            if(check==1) return; // 한 번만 실행
            sudoku[row][col]=0;

        }

    }

    static boolean checkValid(int idx,int n){ // idx 자리에 n을 넣을 수 있는지 확인
        boolean flag=true;
        int row=zeros.get(idx)/9;
        int col=zeros.get(idx)%9;
       /// System.out.println(row+","+col);

        // check row & col
        for(int i=0;i<9;i++){
            if(sudoku[row][i]==n || sudoku[i][col]==n){
                flag=false;
                break;
            }
        }

        // 내부 사각형 체크
        if(flag){
            int start_row=row-(row%3);
            int start_col=col-(col%3);

            for(int i=start_row;i<start_row+3;i++){
                for(int j=start_col;j<start_col+3;j++){
                    if(sudoku[i][j]==n){
                        flag=false;
                        break;
                    }
                }
                if(!flag) break;
            }
        }
        return flag;
    }
}