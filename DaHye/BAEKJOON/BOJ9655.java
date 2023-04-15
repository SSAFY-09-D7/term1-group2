package BAEKJOON;

import java.util.Scanner;

/*
 * 돌 게임
 */
public class BOJ9655 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N % 2 == 0) System.out.println("CY");
		else System.out.println("SK");
	}
}
