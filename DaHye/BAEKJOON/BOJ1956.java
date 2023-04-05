package BAEKJOON;

import java.util.Scanner;

/*
 * 운동
 */
public class BOJ1956 {
	static final int INF = 987654321;
	static int sel[];
	static int V, E, arr[][];
	static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		V = sc.nextInt();
		E = sc.nextInt();
		
		arr = new int[V + 1][V + 1];
		sel = new int[2];
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < E; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			arr[r][c] = sc.nextInt();
		}
		
		for (int r = 1; r < arr.length; r++) {
			for (int c = 1; c < arr[r].length; c++) {
				if(r != c && arr[r][c] == 0) arr[r][c] = INF;
			}
		}
		
		for (int k = 1; k < V + 1; k++) { // 경유지
			for (int j = 1; j < V + 1; j++) { // 출발지
				if(k == j) continue;
				for (int i = 0; i < arr.length; i++) {
					if(i == k || i == j) continue;
					if(arr[i][k] + arr[k][j] < arr[i][j]) arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		comb(0, 0);
		
		result = result == Integer.MAX_VALUE ? -1 : result;
		
		System.out.println(result);
	}

	private static void comb(int k, int idx) {
		if(k == 2) {
			if(arr[sel[0]][sel[1]] != INF && arr[sel[1]][sel[0]] != INF) {
				int tmp = arr[sel[0]][sel[1]] + arr[sel[1]][sel[0]];
				
				result = Math.min(tmp, result);
			}
			return;
		}
		 
		for(int i = idx; i < V; i++) {
			sel[k] = i + 1; 
			comb(k + 1, i + 1);
		}
	}

	private static void print(int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				if(arr[i][j] == INF) System.out.print(0 + " ");
				else System.out.print(arr[i][j] + "  ");
			} System.out.println();
 		}
	}
}
