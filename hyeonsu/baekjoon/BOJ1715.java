package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int n, sum = 0;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());

        for (int i = 0; i < n; i++) {
            pq.add(stoi(br.readLine()));
        }


        //로직
        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                break;
            }

            int a = pq.poll();
            int b = pq.poll();

            sum += a + b;

            if (pq.isEmpty()) break;
            pq.add(a + b);
        }

        System.out.println(sum);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
