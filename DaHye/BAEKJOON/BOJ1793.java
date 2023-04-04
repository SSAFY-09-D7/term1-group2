package BAEKJOON;

import java.math.BigInteger;
import java.util.Scanner;


/*
 * 타일링
 */
public class BOJ1793 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		BigInteger dp[] = new BigInteger[251];
		
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");
		for(int i = 3; i < 251; i++) {
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(new BigInteger("2")));
		}
		
		while(sc.hasNext()) {
			int n = sc.nextInt();
			
			System.out.println(dp[n]);
		}
	}
}
