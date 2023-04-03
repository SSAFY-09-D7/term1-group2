package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 가장 긴 감소하는 수열
 */
public class BOJ11722 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int arr[] = new int[N];
		int LIS[] = new int[N];
		
		for(int i = 0; i < arr.length; i++) arr[i] = sc.nextInt();
		
		for(int i = 0; i < LIS.length; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(arr[i] < arr[j] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1;
			}
		}
		
		int max = 0;
		for(int i = 0; i < LIS.length; i++) {
			max = Math.max(LIS[i], max);
		}
		
		System.out.println(max);
	}
}
