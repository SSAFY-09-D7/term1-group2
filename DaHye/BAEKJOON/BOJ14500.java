package BAEKJOON;
/*
 * 테트로미노
 */

import java.io.*;
import java.util.*;

public class BOJ14500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, max, map[][];
	static int result;
	static boolean v[][];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[r][c]);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				v[r][c] = true;
				dfs(0, r, c, map[r][c]);
				v[r][c] = false;
			}
		}
		System.out.println(result);
	}

	static int dr[] = {1, 0, 0};
	static int dc[] = {0, -1, 1};
	private static void dfs(int k, int r, int c, int sum) {
		if(k == 3) {
			result = Math.max(result, sum);
			return;
		}
		
		if(sum + (3 - k) * max <= result) return;
//		System.out.println(sum + (3 - k) * max + " " + result);
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
	
			if(!check(nr, nc)) continue;
			if(v[nr][nc]) continue;
			
			if(k == 1) {
				v[nr][nc] = true;
				dfs(k + 1, r, c, sum + map[nr][nc]);
			}
			
			v[nr][nc] = true;
			dfs(k + 1, nr, nc, sum + map[nr][nc]);
			v[nr][nc] = false;
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
