package BAEKJOON;

import java.util.Scanner;

/*
 * 다리 놓기
 */
public class BOJ1010 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			int K = sc.nextInt();
			int N = sc.nextInt();
			
			int arr[][] = new int[N + 1][N + 1];
			
			for (int i = 0; i < N + 1; i++) {
				for(int j = 0; j < i + 1; j++) {
					if(i == j) arr[i][j] = 1;
					if(j == 1) arr[i][j] = i;
					if(j == 0) arr[i][j] = 1;
				}
			}

			for(int i = 1; i < N + 1; i++) {
				for (int j = 1; j < K + 1; j++) {
					arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
				}
			}
			System.out.println(arr[N][K]);
 		}
	}
}
