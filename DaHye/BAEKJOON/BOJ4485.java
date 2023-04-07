package BAEKJOON;
/*
 * 녹색 옷 입은 애가 젤다지?
 */

import java.io.*;
import java.util.*;

public class BOJ4485 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, map[][], d[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		int count = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}		
			
			d = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(d[i], 987654321);
			}
			
			d[0][0] = map[0][0];

			bfs(0, 0);
			
			sb.append("Problem " + count + ": " + d[N - 1][N - 1] + "\n");
			count++;
		}
		
		System.out.println(sb);
	}

	private static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		
		boolean v[][] = new boolean[N][N];
		
		queue.add(new int[] {i, j});
		
		while(!queue.isEmpty()) { 
			int current[] = queue.poll();

			for (int k = 0; k < 4; k++) {
				int nr = current[0] + dr[k];
				int nc = current[1] + dc[k];
				
				if(!check(nr, nc)) continue;
				if(d[nr][nc] > map[nr][nc] + d[current[0]][current[1]]) {
					d[nr][nc] = map[nr][nc] + d[current[0]][current[1]];
					queue.add(new int[] {nr, nc});
				}
			}
		}
		
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}