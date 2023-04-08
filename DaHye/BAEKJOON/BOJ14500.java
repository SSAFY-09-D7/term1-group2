package BAEKJOON;
/*
 * 테트로미노
 */

import java.io.*;
import java.util.*;

public class BOJ14500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][];
	static int result;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.println("-----" + "r: "+ r + " c: " + c + "-----");
				dfs(0, r, c, map[r][c]);
				dfs2(0, r, c, map[r][c]);
			}
		}

	}
	
	// ㅗ, ㅜ, ㅓ, ㅏ 모양 처리하기
	private static void dfs2(int i, int r, int c, int j) {
		// TODO Auto-generated method stub
		
	}

	static int dr[] = {1, 0, 0};
	static int dc[] = {0, -1, 1};
	private static void dfs(int k, int r, int c, int sum) {
		if(k == 3) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc)) continue;
			dfs(k + 1, nr, nc, sum + map[nr][nc]);
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
