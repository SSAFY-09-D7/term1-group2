package BAEKJOON;

import java.io.*;
import java.util.*;

/*
 * 소문난 칠공주
 */
public class BOJ1941 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char map[][];
	static boolean v[][], vCopy[][];
	
	static int sel[];
	static int result;
	static ArrayList<Point> list;
	
	public static void main(String[] args) throws Exception {
		map = new char[5][5];

		
		list = new ArrayList();
		
		for (int r = 0; r < 5; r++) {
			String input = br.readLine();
			for (int c = 0; c < 5; c++) {
				map[r][c] = input.charAt(c);
				list.add(new Point(r, c));
			}
		}
		
		sel = new int[7];
		
		func(0, 0);

		System.out.println(result);
	}
	
	private static void func(int k, int idx) {
		if(k == 7) {
			v = new boolean[5][5];
			
			// 7개가 인접해 있는지 확인 + S가 4명 이상인지 확인
			for(int i = 0; i < sel.length; i++) {
				v[list.get(sel[i]).r][list.get(sel[i]).c] = true;
			}
			
			int count = 0;
			
			// 4명보다 이상인지
			if(checkfourOver()) {

				vCopy = copyArr(v);

				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						if(vCopy[i][j] == true) {
							bfs(i, j);
							count++;
							
							if(count > 1) {
								result += 0;
								return;
							}
						}
					}
				}

				if(count == 1) result += 1;
			} else {
				result += 0;
				return;
			}
			
			return;
		}
		for(int i = idx; i < list.size(); i++) {
			sel[k] = i;
			func(k + 1, i + 1);
		}
	}


	private static boolean[][] copyArr(boolean[][] v) {
		boolean[][] tmp = new boolean[5][5];
		
		for (int r = 0; r < v.length; r++) {
			tmp[r] = Arrays.copyOf(v[r], v[r].length);
		}
		return tmp;
	}

	private static boolean checkfourOver() {
		int count = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(v[i][j] == true && map[i][j] == 'S') {
					count++;
				}
			}
		}
		
		if(count >= 4) return true;
		else return false;
	}


	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	private static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {i, j});
		vCopy[i][j] = false;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if(!check(nr, nc)) continue;
				if(vCopy[nr][nc]) {
					vCopy[nr][nc] = false;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 5 && nc >= 0 && nc < 5;
	}
}
