package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 플로이드
 */
public class BOJ11404 {
	static final int INF = 987654321;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int n, m, arr[][];
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1][n + 1];
		
		for (int r = 0; r < arr.length; r++) {
			Arrays.fill(arr[r], INF);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a][b] = Math.min(arr[a][b], c);
		}
		
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				if(i == j) arr[i][j] = 0; 
				if(i != j && arr[i][j] == 0) arr[i][j] = INF;
			}
		}
		
		for (int k = 1; k < n + 1; k++) { // 경유지
			for (int i = 1; i < n + 1; i++) { // 출발지
				if(i == k) continue; // 경유지와 출발지가 같다면 다음 출발지로!
				for (int j = 1; j < n + 1; j++) { // 도착지
					if(i == j || k == j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					if(arr[i][k] + arr[k][j] < arr[i][j]) arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		print(arr);
	}

	private static void print(int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				if(arr[i][j] == INF) System.out.print(0 + " ");
				else System.out.print(arr[i][j] + " ");
			} System.out.println();
		}
		
	}
}