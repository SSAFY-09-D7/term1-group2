package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2164 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Integer> dq = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = n; i > 0; i--) {
            dq.add(i);
        }
        while(dq.size() > 1) {
            dq.pollLast();
            dq.addFirst(dq.pollLast());
        }

        System.out.println(dq.poll());
    }
}
