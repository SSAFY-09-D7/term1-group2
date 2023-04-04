package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 말이 되고픈 원숭이
 * K번만 말과 같이 움직일 수 있고, 그 외에는 인접한 칸(상, 하, 좌, 우)만 움직일 수 있음
 * K
 * W: 가로, H: 세로
 */
public class BOJ1600 {
	static class Point {
		int r;
		int c;
		int l;
		int m;
		
		public Point(int r, int c, int l, int m) {
			super();
			this.r = r;
			this.c = c;
			this.l = l;
			this.m = m;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", l=" + l + "]";
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, W, H, map[][];
	static boolean v[][][];
	static int dr[] = {1, -1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
	static int dc[] = {0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2};
	static int result = -1;
	
	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		    
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		v = new boolean[K + 1][H][W];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(0, 0);
		
		System.out.println(result);
	}
	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(r, c, 0, 0));
		v[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.r == H - 1 && cur.c == W - 1) {
				result = cur.l;
				return;
			}
			// 현재 움직인 횟수가 K번 보다 적다면
			// 원숭이 점프를 할 수도 있고, 그냥 갈 수도 있음
			// cur.m 늘리고 원숭이 점프로 queue에 넣어주기
			if(cur.m < K) {
				for(int i = 4; i < 12; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(!check(nr, nc)) continue;
					if(map[nr][nc] != 1 && !v[cur.m + 1][nr][nc]) {
						v[cur.m + 1][nr][nc] = true;
						queue.add(new Point(nr, nc, cur.l + 1, cur.m + 1));
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] != 1 && !v[cur.m][nr][nc]) {
					v[cur.m][nr][nc] = true;
					queue.add(new Point(nr, nc, cur.l + 1, cur.m));
				}
			}
		}
	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W;
	}
}
