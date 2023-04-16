package BAEKJOON;

import java.util.*;

/*
 * 파도반 수열
 */
public class BOJ9461 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		long dp[] = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		
		for(int i = 6; i < 101; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			sb.append(dp[sc.nextInt()] + "\n");
		}
		System.out.println(sb);
	}
}
