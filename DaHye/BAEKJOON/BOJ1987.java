package BAEKJOON;
/*
 * 알파벳
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, max = 1;
	static char[][] map;
	static boolean[] v; 
	static int count = 1; // 지나간 칸 수 이므로 초기값: 1
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		v = new boolean[26];
		
		for(int i = 0; i < map.length; i++) {
			String input = br.readLine();
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		dfs(0, 0);
		
		System.out.println(max);
	}

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	private static void dfs(int r, int c) {
		
		v[map[0][0] - 'A'] = true; // 해당 알파벳 나왔다고 표시
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr, nc)) continue;
			if(!v[map[nr][nc] - 'A']) {
				v[map[nr][nc] - 'A'] = true;
				count++;
				max = Math.max(count, max);
				dfs(nr, nc);
				count--;
				v[map[nr][nc] - 'A'] = false;
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
