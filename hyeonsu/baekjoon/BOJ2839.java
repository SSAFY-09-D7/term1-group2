package baekjoon;

import java.util.Scanner;

public class BOJ2839 {

    static Scanner sc = new Scanner(System.in);
    static int[] dp;

    public static void main(String[] args) {
        int n = sc.nextInt();
        dp = new int[n + 1];
        System.out.println("greedy : " + greedy(n));
        System.out.println("calculate : " + calc(n));
        int ret = dp_topDown(n) == Integer.MAX_VALUE ? -1 : dp_topDown(n);
        System.out.println("top_down : " + ret);
        System.out.println("bottom_up : " + dp_bottomUp(n));
    }

    static int greedy(int n) {
        int cnt = 0;
        while (true) {
            if (n % 5 == 0) {
                cnt += n / 5;
                return cnt;
            }
            if (n < 0) return -1;
            n = n - 3;
            cnt++;
        }

    }

    static int calc(int n) {
        int cnt = n / 5;
        int remainder = n % 5;

        if (n < 3) return -1;
        if (n == 4 || n == 7) return -1;
        if (remainder == 1) {
            return cnt + 1;
        }
        if (remainder == 2) {
            return cnt + 2;
        }
        if (remainder == 3) {
            return cnt + 1;
        }
        if (remainder == 4) {
            return cnt + 2;
        }
        return cnt;
    }

    static int dp_bottomUp(int n) {
        for (int i = 1; i <= n; i++) {
            if (i < 3 || i == 4 || i == 7) {
                dp[i] = Integer.MAX_VALUE;
                continue;
            }
            if (i == 3 || i == 5) {
                dp[i] = 1;
                continue;
            }
            dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
        }
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    static int dp_topDown(int n) {
        if (n == 4 || n == 7) return dp[n] = Integer.MAX_VALUE;
        if (n < 3) return Integer.MAX_VALUE;
        if (dp[n] != 0) return dp[n];
        if (n == 3 || n == 5) return dp[n] = 1;
        return dp[n] = Math.min(dp_topDown(n - 5), dp_topDown(n - 3)) + 1;
    }
}
