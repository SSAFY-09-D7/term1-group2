package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 1로 만들기
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눔
 * 2. X가 2로 나누어 떨어지면, 2로 나눔
 * 3. 1을 뺌
 */
public class BOJ1463 {
	static int cnt, X, i;
	static int memo[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		memo = new int[1000000 + 1];
		
		Arrays.fill(memo, 0);
		
		for(int i = 2; i <= X; i++) {
			int tmp1 = Integer.MAX_VALUE;
			int tmp2 = Integer.MAX_VALUE;
			int tmp3 = Integer.MAX_VALUE;
			
			if(i % 2 == 0) {
				tmp1 = memo[i /2];
			} 
			if(i % 3 == 0) {
				tmp2 = memo[i / 3];
			} 
			tmp3 = memo[i - 1];
			
			int min = Math.min(tmp1, tmp2);
			min = Math.min(min, tmp3);
			
			memo[i] = min + 1;
		}

		System.out.println(memo[X]);
	}
}
