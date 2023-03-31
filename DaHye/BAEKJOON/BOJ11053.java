package BAEKJOON;

import java.util.Scanner;

/*
 * 가장 긴 증가하는 부분 수열
 */
public class BOJ11053 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int A[] = new int[N];
		int LIS[] = new int[N];
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		for (int i = 0; i < N; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(LIS[i] < LIS[j] + 1 && A[j] < A[i]) {
					LIS[i] = LIS[j] + 1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		
		System.out.println(max);
	}
}
