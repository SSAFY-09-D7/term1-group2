package BAEKJOON;

/*
 * 탈출
 * .: 빈 곳, *: 물이 차있는 곳, 'X': 돌, 'D': 비버의 굴, 'S': 고슴도치 위치
 * 물: 매 초 확장
 * 물, 고슴도치: 돌 통과 X
 * 고슴도치: 물 X
 * 물: 비버의 소굴 X
 * 고슴도치가 비버의 굴로 이동하기 위한 최소 시간
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
	static class Point {
		int r, c, t;
		char ch;
		public Point(int r, int c, int t, char ch) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.ch = ch;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R ,C, time;
	static char map[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		time = Integer.MAX_VALUE;
		
		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);
			}
		}
		
		bfs();
		
		String reuslt = time == Integer.MAX_VALUE ? "KAKTUS" : String.valueOf(time);
		
		System.out.println(reuslt);
		
//		print(map);
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean v[][] = new boolean[R][C];
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == '*') queue.add(new Point(r, c, 0, '*'));
			}
		}
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] == 'S') queue.add(new Point(r, c, 0, 'S'));
			}
		}
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			v[current.r][current.c] = true;
			
			for(int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if(!check(nr, nc)) continue;
				// current가 물일 때
				if(current.ch == '*') {
					if(map[nr][nc] != 'D' && map[nr][nc] != 'X' && !v[nr][nc]) {
						v[nr][nc] = true;
						map[nr][nc] = '*';
						queue.add(new Point(nr, nc, current.t + 1, current.ch));
					}
				}
				
				// current가 고슴도치일 때
				if(current.ch == 'S') {
					if(map[nr][nc] == 'D') {
						time = current.t + 1;
						return;
					}
					
					if(map[nr][nc] != '*' && map[nr][nc] != 'X' && !v[nr][nc]) {
						v[nr][nc] = true;
						queue.add(new Point(nr, nc, current.t + 1, current.ch));
					}
				}
			}
			
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static void print(char[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
}