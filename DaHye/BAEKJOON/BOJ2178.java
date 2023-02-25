package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 미로 탐색
 */
public class BOJ2178 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][];
	static int dist;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			String Input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Input.charAt(c) - '0';
			}
		}
		
		bfs(0, 0, 0);
		
		System.out.println(dist);
	}

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static void bfs(int r, int c, int d) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		
		queue.add(new int[] {r, c, 0});
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			
			if(point[0] == map.length - 1 && point[1] == map[0].length - 1) {
				dist = point[2] + 1;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == 1 && !v[nr][nc]) {
					
					queue.add(new int[] {nr, nc, point[2] + 1});
					v[nr][nc] = true;
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
