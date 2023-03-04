package BAEKJOON;
/*
 * 미세먼지 안녕!
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144 {
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static class Dust {
		Point p;
		int w;
		public Dust(Point p, int w) {
			super();
			this.p = p;
			this.w = w;
		}
	}
	static class machine {
		Point s;
		Point e;
		public machine(Point s, Point e) {
			super();
			this.s = s;
			this.e = e;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C, T, totalDust, map[][];
	static StringTokenizer st;
	static machine m;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		findMachine();

		while(T > 0) {
			// 미세먼지 확산 모든 칸에서 동시에 일어남
			Queue<Dust> queue = new LinkedList<>();
			
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if(map[r][c] > 0) {
						queue.add(new Dust(new Point(r, c), map[r][c]));
					}
				}
			}
			
			// Queue에 담은 먼저 확산
			sprayDust(queue);
				
			// 공기청정기 작동
			map = runUpMachine(m.s.r, 1);
			map = runDownMachine(m.e.r, 1);
			
			T--;
		}
		sumDust();
		
		System.out.println(totalDust);
	}

	private static void sumDust() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] != -1) totalDust += map[r][c];
			}
		} 
	}

	private static void findMachine() {
		for (int r = 0; r < map.length; r++) {
			if(map[r][0] == -1) {
				m = new machine(new Point(r, 0), new Point(r + 1, 0));
				return;
			}
		}
	}
	
	// 우, 하, 좌, 상
	static int dr[] = {0, -1, 0, 1};
	static int dc[] = {1, 0, -1, 0};
	private static int[][] runUpMachine(int r, int c) {
		int tmp[][] = new int[map.length][map[0].length];
		
		copyArr(map, tmp);
		int nr = r;
		int nc = c;
		int d = 0;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if(!check2(nr,nc)) {
				nr -= dr[d];
				nc -= dc[d];
				d++;
				continue;
			}
			tmp[nr][nc] = map[r][c];
			r = nr;
			c = nc;
			
			if(nr == m.s.r && nc == 0) break;
		}
		tmp[m.s.r][1] = 0;
		tmp[r][c] = -1;
		return tmp;
	}
	
	private static void copyArr(int[][] map, int[][] tmp) {
		for (int i = 0; i < map.length; i++) {
			tmp[i] = Arrays.copyOf(map[i], map[i].length);
		}
	}

	// 우, 하, 좌, 상
	static int dr2[] = {0, 1, 0, -1};
	static int dc2[] = {1, 0, -1, 0};
	private static int[][] runDownMachine(int r, int c) {
		int tmp[][] = new int[map.length][map[0].length];
		
		copyArr(map, tmp);
		int nr = r;
		int nc = c;
		int d = 0;
		
		while(true) {
			nr += dr2[d];
			nc += dc2[d];
			
			if(!check2(nr,nc)) {
				nr -= dr2[d];
				nc -= dc2[d];
				d++;
				continue;
			}
			tmp[nr][nc] = map[r][c];
			r = nr;
			c = nc;
			
			if(nr == m.e.r && nc == 0) break;
		}
		tmp[m.e.r][1] = 0;
		tmp[r][c] = -1;
		return tmp;
	}
	private static boolean check2(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	private static void sprayDust(Queue<Dust> queue) {
		
		while(!queue.isEmpty()) {
			Dust dust = queue.poll();
			
			ArrayList<Dust> list = new ArrayList<>();
			for(int i = 0; i < 4; i++) {
				int nr = dust.p.r + dr[i];
				int nc = dust.p.c + dc[i];
				
				if(!check(nr, nc)) continue;
				list.add(new Dust(new Point(nr, nc), 0));
			}
//			System.out.println(dust.p.r + " " + dust.p.c + " " + list.size());
			// 확산되는 양: Ar,c/5
			for(int i = 0; i < list.size(); i++) {
				map[list.get(i).p.r][list.get(i).p.c] += (dust.w / 5);
			}
			map[dust.p.r][dust.p.c] -= ( list.size() * (dust.w / 5));
		}
		
	}
	private static boolean check(int nr, int nc) {
		if(nr < 0 || nr >= R || nc < 0 || nc >= C) return false;
		if(map[nr][nc] == -1) return false;
		return true;
	}
}
