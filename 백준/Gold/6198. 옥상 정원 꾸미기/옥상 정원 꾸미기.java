import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        long[] height = new long[N];
        Deque<Long> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            height[i] = Long.parseLong(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            // Pop elements from stack while they are shorter than the current building
            while (!stack.isEmpty() && stack.peek() <= height[i]) {
                stack.pop();
            }
            // The number of visible buildings is the size of the stack after the pops
            sum += stack.size();
            // Push the current building to the stack
            stack.push(height[i]);
        }

        System.out.println(sum);
    }
}