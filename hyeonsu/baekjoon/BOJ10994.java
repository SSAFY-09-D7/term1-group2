package baekjoon;

import java.io.*;
import java.util.Scanner;

public class BOJ10994 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static boolean[][] star;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        star = new boolean[(n - 1) * 4 + 1][(n - 1) * 4 + 1];

        recur(n, 0, star.length - 1);

        for (int i = 0; i < star.length; i++) {
            for (int j = 0; j < star[0].length; j++) {
                bw.append(star[i][j] ? '*' : ' ');
            }
            bw.append('\n');
        }
        bw.flush();
        bw.close();
    }

    static void recur(int depth, int start, int end) {
        if (depth == 1) {
            star[start][end] = true;
            return;
        }

        for (int i = start; i <= end; i++) {
            for (int j = start; j <= end; j++) {
                if (i == start || i == end || j == start || j == end) {
                    star[i][j] = true;
                }
            }
        }
        recur(depth - 1, start + 2, end - 2);
    }
}
