package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 조약돌 꺼내기
 * N개의 조약돌이 있고, 색상은 1부터 M까지 있음
 * K개 뽑았을 때 모두 같은 색일 확률
 */
public class BOJ13251 {
	static int N, M, K, arr[];
	static int mTotal[][], mSub[][];
	static int sum;
	static int total, sub[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
		}
		
		K = sc.nextInt();
		
		mTotal = new int[sum + 1][sum + 1];
		
		for(int i = 0; i < mTotal.length; i++) {
			for(int j = 0 ; j < mTotal.length; j++) {
				mTotal[i][0] = 1;
				mTotal[i][i] = 1;
				mTotal[i][1] = i;
			}
		}
		
		for(int i = 2; i < mTotal.length; i++) {
			for(int j = 1; j < i; j++) {
				mTotal[i][j] = mTotal[i - 1][j - 1] + mTotal[i - 1][j];
			}
		}
		
		total = mTotal[sum][K];
		
		double result = 0;
		
		for(int i = 0; i < arr.length; i++) {
			double tmp = 1;
			for(int j = 0; j < K; j++) {
				tmp *= ((arr[i] - j) / (double)(sum - j));
			}
			result += tmp;
		}
		System.out.println(result);
	}

	private static void print(int[][] mTotal2) {
		for (int i = 0; i < mTotal2.length; i++) {
			System.out.println(Arrays.toString(mTotal2[i]));
		}
	}
}
