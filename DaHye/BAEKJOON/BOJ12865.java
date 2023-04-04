package BAEKJOON;

import java.util.Scanner;

/*
 * 평범한 배낭
 */
public class BOJ12865 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 물품의 수
		int K = sc.nextInt(); // 버틸 수 있는 무게
		
		int V[] = new int[N + 1];
		int W[] = new int[N + 1];
		int knap[][] = new int[N + 1][K + 1];

		for(int i = 1; i < N + 1; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}
		
		for(int i = 1; i < knap.length; i++) {
			for (int w = 1; w < K + 1; w++) {
				if(w >= W[i]) {
					knap[i][w] = Math.max(V[i] + knap[i - 1][w - W[i]], knap[i - 1][w]);					
				} else {
					knap[i][w] = knap[i - 1][w];
				}
			}
		}
		System.out.println(knap[knap.length - 1][K]);
	}
}
