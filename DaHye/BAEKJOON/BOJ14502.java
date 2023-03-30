package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 연구소
 * 0: 빈 칸, 1: 벽, 2: 바이러스
 * 2차원 배열에서 조합을 구해야 된다면 배열의 위치를 Class로 나타낸 뒤 list 활용하기
 */

public class BOJ14502 {
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, max, map[][], copyMap[][];
	static ArrayList<Point> list = new ArrayList<>();
	static Point sel[];
	
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sel = new Point[3];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) list.add(new Point(r, c));
			}
		}
		
		// list 중에서 3개 뽑기
		makeWall(0, 0);

		System.out.println(max);
	}

	private static void makeWall(int k, int idx) {
		// 0인 부분을 조합으로 3개 고른 뒤, 벽을 세워보고 이 때 바이러스를 퍼지게 한 뒤, 최댓값 구하기
		if(k == 3) {
			copyMap = new int[N][M];
			copy();
			
			for(int i = 0; i < 3; i++) {
				int r = sel[i].r;
				int c = sel[i].c;
				
				copyMap[r][c] = 1;
			}

			bfs();
			
			max = Math.max(max, countSafe());
			return;
		}
		
		for (int i = idx; i < list.size(); i++) {
			sel[k] = list.get(i);
			makeWall(k + 1, i + 1);
		}
	}

	private static int countSafe() {
		int safeZone = 0;
		for (int r = 0; r < copyMap.length; r++) {
			for (int c = 0; c < copyMap[r].length; c++) {
				if(copyMap[r][c] == 0) safeZone++;
			}
		}
		return safeZone;
	}

	private static void copy() {
		for (int r = 0; r < map.length; r++) {
			copyMap[r] = Arrays.copyOf(map[r], map[r].length);
		}
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		
		for (int r = 0; r < copyMap.length; r++) {
			for (int c = 0; c < copyMap[r].length; c++) {
				if(copyMap[r][c] == 2) queue.add(new int[] {r, c});
			}
		}
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if(!check(nr, nc)) continue;
				if(copyMap[nr][nc] == 0 && !v[nr][nc]) {
					copyMap[nr][nc] = 2;
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
