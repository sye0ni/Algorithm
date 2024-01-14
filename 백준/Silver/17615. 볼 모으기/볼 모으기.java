import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static class ball{
        char color;
        int gNum;

        public ball(char color,int gNum){
            this.color=color;
            this.gNum=gNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        String str=br.readLine();

        Deque<ball> stack=new ArrayDeque<>();
        int red=0;
        int redG=1;
        int blue=0;
        int blueG=1;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='R'){ // red
                if(i==0 || str.charAt(i-1)=='R'){
                    stack.addLast(new ball('R',redG));
                }
                else{
                    stack.addLast(new ball('R',++redG));
                }
                red++;
            }
            else{ // blue
                if(i==0 || str.charAt(i-1)=='B'){
                    stack.addLast(new ball('B',blueG));
                }
                else{
                    stack.addLast(new ball('B',++blueG));
                }
                blue++;
            }
        }
        ball top=stack.removeLast();
        char lastCol=top.color;
        int lastGn=top.gNum;
        if(lastCol=='R') red--;
        if(lastCol=='B') blue--;

        int answer=0;
        if(lastGn>1){
            while(true){
                top=stack.removeLast();
                if(top.color!=lastCol || top.gNum!=lastGn) break;
                if(lastCol=='R') red--;
                if(lastCol=='B') blue--;
            }
            answer=Math.min(red,blue);
        }   // 앞에서부터

        // 뒤에서부터 add
        stack.clear();
        red=0;
        redG=1;
        blue=0;
        blueG=1;

        for(int i=str.length()-1;i>=0;i--){
            if(str.charAt(i)=='R'){ // red
                if(i==str.length()-1 || str.charAt(i+1)=='R'){
                    stack.addLast(new ball('R',redG));
                }
                else{
                    stack.addLast(new ball('R',++redG));
                }
                red++;
            }
            else{ // blue
                if(i==str.length()-1 || str.charAt(i+1)=='B'){
                    stack.addLast(new ball('B',blueG));
                }
                else{
                    stack.addLast(new ball('B',++blueG));
                }
                blue++;
            }
        }
        top=stack.removeLast();
        lastCol=top.color;
        lastGn=top.gNum;
        int answer2=0;
        if(lastCol=='R') red--;
        if(lastCol=='B') blue--;

        if(lastGn>1){
            while(true){
                top=stack.removeLast();
                if(top.color!=lastCol || top.gNum!=lastGn) break;
                if(lastCol=='R') red--;
                if(lastCol=='B') blue--;
            }
            answer2=Math.min(red,blue);
        }

        System.out.println(Math.min(answer,answer2));
    }
}