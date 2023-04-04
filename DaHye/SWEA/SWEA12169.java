package SWEA;

import java.util.Scanner;

/*
 * 최장증가 부분수열
 */
public class SWEA12169 {
	static StringBuilder sb;
	
	public static void main(String[] args) {
		sb = new StringBuilder();
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int teat_case = 0; teat_case < T; teat_case++) {
			int n = sc.nextInt();
			int arr[] = new int[n];
			int LIS[] = new int[n];
			int max = 0;
			
			for(int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int i = 0; i < n; i++) {
				LIS[i] = 1;
				for (int j = 0; j < i; j++) {
					if(LIS[i] < LIS[j] + 1 && arr[j] < arr[i]) {
						LIS[i] = LIS[j] + 1;
					}
				}
				max = Math.max(max, LIS[i]);
			}
			sb.append("#" + (teat_case + 1) + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
