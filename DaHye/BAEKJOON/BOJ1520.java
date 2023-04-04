package BAEKJOON;
/*
 * 내리막 길
 * 재귀 + 메모이제이션
 * 제일 왼쪽 위 -> 제일 오른쪽 아래
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1520 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, count, map[][];	// 세로의 크기: M, 가로의 크기: N
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	static int memo[][];
	static int tmp = 1;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		memo = new int[M][N];

		for (int r = 0; r < memo.length; r++) {
			Arrays.fill(memo[r], -1);
		}
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 0));
	}

	
	private static int dfs(int r, int c) {
		if(r == M - 1 && c == N - 1) {
			return 1;
		}
		
		// 메모이제이션 할 때 값이 있으면 바로 return 하기!!
		if(memo[r][c] != -1) {
			return memo[r][c];
		}

		memo[r][c] = 0;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d];
			
			if(!check(nr, nc)) continue;

			if(map[r][c] > map[nr][nc]) {
				memo[r][c] += dfs(nr, nc);
			}
		}
		
		return memo[r][c];
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < M && nc >= 0 && nc < N;
	}
}
