package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다리 만들기
 */
public class BOJ2146 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, level, map[][];
	static int dr[] = {0, 0, -1, 1};
	static int dc[] = {1, -1, 0, 0};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		island();
		
		for(int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(checkPoint(r, c) && map[r][c] != 0) {
					level = 0;
					func(r, c, map[r][c]);
					min = Math.min(min, level);
				}
			}
		}
		
		System.out.println(min - 1);
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	private static void island() {
		int count = 1;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) {
					transIsland(i, j, count);
					count++;
				}
			}
		}
		
	}

	private static void transIsland(int i, int j, int count) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] v = new boolean[N][N];
		
		queue.offer(new int[] {i, j});
		map[i][j] = count;
		v[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = point[0] + dr[d];
				int nc = point[1] + dc[d];
				
				if(!check(nr, nc) || v[nr][nc]) continue;
				if(map[nr][nc] == 1) {
					queue.add(new int[] {nr, nc});
					map[nr][nc] = count;
					v[nr][nc] = true;
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void func(int r, int c, int p) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] v = new boolean[N][N];
		
		queue.offer(new int[] {r, c, 0});
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			if(map[point[0]][point[1]] != 0 && map[point[0]][point[1]] != p) {
				level = point[2];
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
				queue.add(new int[] {nr, nc, point[2] + 1});
				v[nr][nc] = true;
			}			
		}

	}

	private static boolean checkPoint(int r, int c) {
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(map[nr][nc] == 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
