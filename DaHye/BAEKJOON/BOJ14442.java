package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 벽 부수고 이동하기 2
 * (1, 1)에서 (N, M)의 위치까지 이동할 떄 최단 경로
 */
public class BOJ14442 {
	static class Point {
		int r;
		int c;
		int w;
		int fuk;	// 벽을 부순 횟수
		public Point(int r, int c, int w, int fuk) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
			this.fuk = fuk;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K, time, endR, endC, map[][];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}
		
		bfs(new Point(0, 0, 0, 0));
		time = ((endR == N - 1 ) && (endC == M - 1)) ? time + 1 : -1;
		System.out.println(time);
	}
	
	static int dr[] = {1, 0, -1, 0};
	static int dc[] = {0, 1, 0, -1};
	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][][] = new boolean[K + 1][N][M];
		queue.offer(p);
		v[0][p.r][p.c] = true;
		
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
				// 경우의 수 잘 확인하고 처리하기!!
				if(point.fuk < K) {	// 벽을 부순 회수가 K회보다 작을 때
					if(map[nr][nc] == 1 && !v[point.fuk + 1][nr][nc]) {
						v[point.fuk + 1][nr][nc] = true;
						queue.add(new Point(nr, nc, point.w + 1, point.fuk + 1));
					}
					
					if(map[nr][nc] == 0 && !v[point.fuk][nr][nc]) {
						v[point.fuk][nr][nc] = true;
						queue.add(new Point(nr, nc, point.w + 1, point.fuk));
					}
				}
				
				if(point.fuk == K && map[nr][nc] == 0 && !v[K][nr][nc]) {
					v[K][nr][nc] = true;
					queue.add(new Point(nr, nc, point.w + 1, point.fuk));
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
