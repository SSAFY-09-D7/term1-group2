package BAEKJOON;

import java.util.Scanner;

/*
 * RGB 거리
 * 1번 집의 색은 2번 집의 색과 같으면 안됨
 * N번 집의 색은 N-1번 집의 색과 같으면 안됨
 * i 번집의 색은 i-1번, i+1번 집의 색과 같으면 안됨
 * 입력
 * - N: 집의 수
 * - 각 집을 빨, 초, 파로 칠하는 비용
 * 
 * 출력
 * - 모든 집을 칠하는 비용의 최솟값
 */
public class BOJ1149 {
	static int house[][];
	static int dp[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		house = new int[N][3];
		dp = new int[N][3];
		
		for (int i = 0; i < house.length; i++) {
			for (int j = 0; j < house[i].length; j++) {
				house[i][j] = sc.nextInt();
			}
		}
		
		dp[0][0] = house[0][0];
		dp[0][1] = house[0][1];
		dp[0][2] = house[0][2];
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = house[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = house[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = house[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}
		
		int min = Math.min(dp[N - 1][0], dp[N - 1][1]);
		min = Math.min(min, dp[N - 1][2]);
		
		System.out.println(min);
	}
}
