package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 벽 부수고 이동하기
 * (1, 1)에서 (N, M)의 위치까지 이동할 떄 최단 경로
 */
public class BOJ2206 {
	static class Point {
		int r;
		int c;
		int w;
		boolean fuk;
		public Point(int r, int c, int w, boolean fuk) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
			this.fuk = fuk;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, time, endR, endC, map[][];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}
		
		bfs(new Point(0, 0, 0, false));
		time = ((endR == N - 1 ) && (endC == M - 1)) ? time + 1 : -1;
		System.out.println(time);
	}
	
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		boolean vFuk[][] = new boolean[N][M];
		queue.offer(p);
		v[p.r][p.c] = true;
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();

			endR = point.r;
			endC = point.c;
			time = point.w;
 			
			if(endR == N - 1 && endC == M - 1) {
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(!point.fuk && !v[nr][nc]) {
					if(map[nr][nc] == 1) {
						vFuk[nr][nc] = true;
						queue.add(new Point(nr, nc, point.w + 1, true));
					}
					
					if(map[nr][nc] == 0) {
						v[nr][nc] = true;
						queue.add(new Point(nr, nc, point.w + 1, false));
					}
				}
				
				if(point.fuk && map[nr][nc] == 0 && !vFuk[nr][nc]) {
					vFuk[nr][nc] = true;
					queue.add(new Point(nr, nc, point.w + 1, point.fuk));
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
