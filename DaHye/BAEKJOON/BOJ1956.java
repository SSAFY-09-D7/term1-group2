package BAEKJOON;

import java.util.Arrays;
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
		
		arr = new int[V][V];
		sel = new int[2];
		result = INF;
		
		// 배열 초기화
		for (int[] node : arr) {
			Arrays.fill(node, INF);
		}
		
		for (int i = 0; i < E; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			
			arr[r][c] = sc.nextInt();
		}
		
		// 사이클이 있어야 되므로 조건문 설정 잘 하기!
		for (int k = 0; k < V; k++) { // 경유지
			for (int j = 0; j < V; j++) { // 출발지
				if(k == j) continue;
				for (int i = 0; i < arr.length; i++) {
					if(i == k) continue;
					arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
				}
			}
		}
//		print(arr);
		
		for(int i = 0; i < V; i++) {
			result = Math.min(result, arr[i][i]);
		}
		
		result = result == INF ? -1 : result;
		

		System.out.println(result);
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][j] == INF) System.out.print(0 + " ");
				else System.out.print(arr[i][j] + " ");
			} System.out.println();
 		}
	}
}
