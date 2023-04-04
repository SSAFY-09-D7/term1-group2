package BAEKJOON;

import java.util.Scanner;

/*
 * 경로 찾기
 */
public class BOJ11403 {
	static int N;
	static int adj[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adj[i][j] = sc.nextInt();
			}
		}
		
		for(int k = 0; k < N; k++) {	// 경유지
			for (int i = 0; i < N; i++) {	// 출발지
				for (int j = 0; j < N; j++) {	// 도착지
					if(adj[i][k] == 1 && adj[k][j] == 1) adj[i][j] = 1;
				}
			}
		}
		
		print(adj);
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			} System.out.println();
		}
	}
}
