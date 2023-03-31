package BAEKJOON;

import java.util.Scanner;

/*
 * 2×n 타일링 2
 */
public class BOJ11727 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int arr[] = new int[1001];
		
		arr[1] = 1;
		arr[2] = 3;
		arr[3] = 5;
		
		for (int i = 4; i < 1001; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2] * 2) % 10007;
		}
		
		int n = sc.nextInt();
		System.out.println(arr[n]);
	}
}
