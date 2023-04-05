package SWEA;

import java.util.*;

/*
 * 장훈이의 높은 선반
 */
public class SWEA1486 {
	static int N, B, result, H[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			result = Integer.MAX_VALUE;
			
			N = sc.nextInt();
			B = sc.nextInt();
			H = new int[N];

			for (int i = 0; i < N; i++) H[i] = sc.nextInt();
			
			func(0, 0);
			
			sb.append("#" + test_case + " "+ result + "\n");
		}
		
		System.out.println(sb);
	}
	private static void func(int k, int sum) {
		if(k == N) {
			if(sum >= B) result = Math.min(result, sum - B);
			return;
		}
		
		func(k + 1, sum + H[k]);
		func(k + 1, sum);
	}
}
