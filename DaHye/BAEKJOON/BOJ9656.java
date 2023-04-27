package BAEKJOON;

import java.util.Scanner;

/*
 * 돌 게임 2
 */
public class BOJ9656 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N % 2 == 1) {
			System.out.println("CY");
		} else {
			System.out.println("SK");
		}
	}
}
