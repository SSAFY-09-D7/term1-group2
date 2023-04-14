package BAEKJOON;
/*
 * 노드사이의 거리
 */

import java.io.*;
import java.util.*;

public class BOJ1240 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, arr[][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][N + 1];
		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				if(r != c) arr[r][c] = 987654321;
			}
		}

		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a][b] = Math.min(arr[a][b], c);
			arr[b][a] = Math.min(arr[a][b], c);
		}
		

		for (int k = 0; k < N + 1; k++) { // 경유지
			for (int i = 0; i < N + 1; i++) { // 출발지
				if(i == k) continue;
				for (int j = 0; j < N + 1; j++) { // 도착지
					if(i == j || k == j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(arr[a][b] + "\n");
		}
		
		System.out.println(sb);
	}
}
