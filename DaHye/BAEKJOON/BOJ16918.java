package BAEKJOON;

import java.io.*;
import java.util.*;

/*
 * 봄버맨
 * O: 79, .: 46
 */

public class BOJ16918 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, N;
	static int map[][];
	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		
		for (int r = 0; r < map.length; r++) {
			Arrays.fill(map[r], Integer.MAX_VALUE);
		}
		
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c);
			}
		}

		int count = 0;
		
		// 1초
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				// 폭탄이라면 감소
				if(map[r][c] != 46) map[r][c]--;
			}
		}
		
		while(count++ < N - 1) {
			func();
		}
	
		print(map);
	}
	
	private static void bumb() {
		Queue<int[]> queue = new LinkedList<>();
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 76) {
					queue.add(new int[] {r, c});
					map[r][c] = 46;
				} 
			}
		}
		
		while(!queue.isEmpty()) {
			int current[] = queue.poll();
			
			int r = current[0];
			int c = current[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(!check(nr, nc)) continue;
				map[nr][nc] = 46;
			}
		}
	}

	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	private static void func() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				// 폭탄이라면 감소
				if(map[r][c] != 46) map[r][c]--;
				if(map[r][c] == 46) map[r][c] = 79;
			}
		}
		bumb();
	}
	
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 46) System.out.print((char) 46);
				else System.out.print((char) 79);
			}System.out.println();
		}
	}
}