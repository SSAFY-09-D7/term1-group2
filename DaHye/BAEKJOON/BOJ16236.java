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
	static ArrayList<fish> fish;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static class fish implements Comparable<fish>{
		int r, c, size, d, eat;
		
		public fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
		
		public fish(int r, int c, int size, int eat) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.eat = eat;
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
			if(this.d == o.d) {
				if(this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.d, o.d);
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		fish = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] != 0 && map[r][c] != 9) fish.add(new fish(r, c, map[r][c]));
				if(map[r][c] == 9) shark = new fish(r, c, 2, 0);
			}
		}
		
		
		while(checkMap()) {

			getDistance();
			
			boolean flag = false;
			
			for(int i = 0; i < fish.size(); i++) {
				if(fish.get(i).size < shark.size) {
					if(fish.get(i).d != 987654321) flag = true;
				}
			}
			
			if(!flag) break;
			
			Collections.sort(fish);
//			for (int i = 0; i < fish.size(); i++) {
//				System.out.println(fish.get(i));
//			}

			eat();
			
//			print(map);
			
//			System.out.println(count + "===========");
		}


		System.out.println(count);
	}

	private static void print(int[][] map) {
		for (int r = 0; r < map.length; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}

	private static void eat() {
		for(int i = 0; i < fish.size(); i++) {
			if(fish.get(i).size < shark.size && fish.get(i).d != 987654321) {
				shark.eat++;
				if(shark.eat == shark.size) {
					shark.size++;
					shark.eat = 0;
				}
				
				map[shark.r][shark.c] = 0;
				
				shark.r = fish.get(i).r;
				shark.c = fish.get(i).c;
				
				map[fish.get(i).r][fish.get(i).c] = 9;
				
				count += fish.get(i).d;
				
				fish.remove(i);
				
				break;
			}
		}
	}

	private static boolean checkMap() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				if(map[r][c] != 9 && map[r][c] < shark.size && map[r][c] > 0) return true;
			}
		}
		return false;
	}

	// 자신보다 작은 물고기를 먹으러 갈 때 거리를 구하는 함수
	private static void getDistance() {
		Queue<int []> queue= new LinkedList<>();
		boolean v[][] = new boolean[N][N];
		
		queue.add(new int [] {shark.r, shark.c, 0});
		
		for (int i = 0; i < fish.size(); i++) {
			fish.get(i).setD(987654321);
		}

		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			int r = current[0];
			int c = current[1];
			
			for(int i = 0; i < fish.size(); i++) {
				if(fish.get(i).r == r && fish.get(i).c == c) fish.get(i).setD(current[2]);
			}
			v[r][c] = true;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(!check(nr, nc)) continue;
				if(!v[nr][nc] && map[nr][nc] <= shark.size) {
					 queue.add(new int[] {nr, nc, current[2] + 1});
					 v[nr][nc] = true;
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
