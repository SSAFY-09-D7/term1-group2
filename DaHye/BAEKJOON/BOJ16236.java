package BAEKJOON;
/*
 * 아기 상어
 */

import java.io.*;
import java.util.*;

public class BOJ16236 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], count;
	static fish shark;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static class fish implements Comparable<fish>{
		int r, c, size, d, eat;
		public fish(int r, int c, int size, int d) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
		}

		public void setD(int d) {
			this.d = d;
		}
		
		@Override
		public String toString() {
			return "fish [r=" + r + ", c=" + c + ", size=" + size + ", d=" + d + "]";
		}

		@Override
		public int compareTo(fish o) {
			if(this.r == o.r) {
				return Integer.compare(this.c, o.c);
			}
			return Integer.compare(this.r, o.r);
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					shark = new fish(r, c, 2, 0);
					map[r][c] = 0;
				}
			}
		}
		
		bfs();
		
		System.out.println(count);
	}

	private static void bfs() {
		Queue<fish> queue = new LinkedList<>();
		ArrayList<fish> list = new ArrayList<>();
		
		boolean v[][] = new boolean[N][N];
		
		// 상어의 위치를 queue에 담음
		queue.add(new fish(shark.r, shark.c, shark.size, 0));
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				fish current = queue.poll();
				
				// 상어가 먹을 수 있는 물고기를 만났을 때
				if(current.size < shark.size && current.size > 0) {
					list.add(current);
				}
				
				v[current.r][current.c] = true;
				
				for (int d = 0; d < 4; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					
					if(!check(nr, nc)) continue;
					if(!v[nr][nc] && map[nr][nc] <= shark.size) {
						v[nr][nc] = true;
						queue.add(new fish(nr, nc, map[nr][nc], current.d + 1));
					}
				}
			}
			
			if(list.size() > 0) {
				Collections.sort(list);
				
				shark.eat++;
				
				shark.r = list.get(0).r;
				shark.c = list.get(0).c;
				count += list.get(0).d;
				
				if(shark.eat == shark.size) {
					shark.eat = 0;
					shark.size++;
				}
				
				list = new ArrayList<>();
				v = new boolean[N][N];

				queue = new LinkedList<>();
				queue.add(new fish(shark.r, shark.c, shark.size, 0));
				map[shark.r][shark.c] = 0;
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
