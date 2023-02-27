package baekjoon;

import java.util.Scanner;

public class BOJ18222 {

    static long[] pow = new long[64];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 64; i++) {
            pow[i] = (long) Math.pow(2, i);
        }
        long n = sc.nextLong();
        System.out.println(solve(n));
    }

    static long solve(long n) {
        if (n == 1) {
            return 0L;
        }

        int m = 0;
        for(int i = 63; i >= 0; i--) {
            if (pow[i] < n) {
                m = i;
                break;
            }
        }

        return 1 - solve(n - pow[m]);
    }
}
