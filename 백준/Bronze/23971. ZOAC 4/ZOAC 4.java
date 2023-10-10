import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int H,W,N,M;
        st=new StringTokenizer(br.readLine()," ");
        H=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        int row,col;

        row=(int)H/(N+1);
        if(H%(N+1)!=0) row++;
        col=(int)W/(M+1);
        if(W%(M+1)!=0) col++;

        System.out.println(row*col);

    }

}