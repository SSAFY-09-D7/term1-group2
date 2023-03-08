package BAEKJOON;
/*
 * 아기 상어
 * N X N 공간, M마리의 물고기, 1마리 아기상어
 * 한 칸에는 물고기가 최대 한 마리 존재
 * 아기 상어 - 크기: 2, 1초에 상하좌우로 인접한 한 칸씩 이동
 * 			 자기보다 큰 물고기: 먹을 수 X, 지나갈 수 X
 * 			 크기가 같은 물고기: 먹을 수 X, 지나갈 수 O
 * 			 크기가 작은 물고기: 먹을 수 O, 지나갈 수 O
 * 		   - 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가
 * 출력: 물고기를 다 먹을 때까지의 시간
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
	static class shark {
		int r;
		int c;
		int w;
		public shark(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}
		@Override
		public String toString() {
			return "shark [r=" + r + ", c=" + c + ", w=" + w + "]";
		}
		
		
	}
	static class fish extends shark implements Comparable<fish> {
		int d;
		
		public fish(int r, int c, int w) {
			super(r, c, w);
		}
		
		public fish(int r, int c, int w, int d) {
			super(r, c, w);
			this.d = d;
		}

		@Override
		public int compareTo(fish o) {
			if(this.d == o.d && this.r == o.r) return Integer.compare(this.c, o.c);
			
			if(this.d == o.d) return Integer.compare(this.r, o.r);

			return Integer.compare(this.d, o.d);
		}

		@Override
		public String toString() {
			return "fish [d=" + d + ", r=" + r + ", c=" + c + ", w=" + w + "]";
		}
		
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, time, eatFish, map[][];
	static ArrayList<fish> list;
	static shark Shark;
	static boolean flag, flag2;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] != 0 && map[r][c] != 9) list.add(new fish(r, c, map[r][c]));
				if(map[r][c] == 9) Shark = new shark(r, c, 2);
			}
		}

		while(true) {
//			System.out.println(Shark.w);
			if(checkFish()) break;
			
			eatFish();
			
//			print(map);
			if(!flag || !flag2) break;
		}
		
		time = time < 0 ? 0 : time;
		
		System.out.println(time);
	}

	private static void print(int[][] map2) {
		for (int r = 0; r < map2.length; r++) {
			System.out.println(Arrays.toString(map2[r]));
		}
	}

	private static void eatFish() {
		
		flag = false;
		flag2 = false;
		
		// 거리가 가장 가까운 물고기부터 먹으러 감
		for(int i = 0; i < list.size(); i++) {
			list.get(i).d = dist(Shark, list.get(i));
		}
		
		Collections.sort(list);
		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).w < Shark.w) {
				flag = true;
				break;
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).d != Integer.MAX_VALUE) {
				flag2 = true;
				break;
			}
		}
		
		if(!flag || !flag2) {
			time = 0;
			return; 
		}
		
		// 자신의 크기보다 작은 물고기만 먹을 수 있음
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).w < Shark.w && list.get(i).d != Integer.MAX_VALUE) {
				// 경로 확인 하다가 자기 보다 큰 물고기 있으면 못감 다음 
				time += list.get(i).d;
				moveShark(list.get(i));
				list.remove(i);
				eatFish++;
				
				// 먹은 물고기의 수가 상어의 크기가 되면
				if(eatFish == Shark.w) {
					Shark.w++;
					eatFish = 0;
				}
				
				return;
				
			} else if(list.get(i).w >= Shark.w) {
				continue;
			}
		}
	}

	private static int checkRoute(shark shark, fish fish) {
		int dist = bfs(shark, fish);
		return dist;
	}

	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, -1, 0, 1};
	private static int bfs(shark shark, fish fish) {
		int dist = Integer.MAX_VALUE;
		Queue<int []> queue = new LinkedList<>();
		boolean[][] v= new boolean[N][N];
		
		queue.offer(new int[] {shark.r, shark.c, 0});
		v[shark.r][shark.c] = true;

		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			if(point[0] == fish.r && point[1] == fish.c) {
				dist = point[2];
				return dist;
			}
			for(int i = 0; i < 4;i ++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] <= shark.w && !v[nr][nc]) {
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc, point[2] + 1});
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void moveShark(fish fish) {
		map[Shark.r][Shark.c] = 0;
				
		Shark.r = fish.r;
		Shark.c = fish.c;
		
		map[Shark.r][Shark.c] = 9;
		
	}

	private static int dist(shark shark2, fish fish) {
		int dist = 0;
		dist = bfs(shark2, fish);
		return dist;
	}

	// 물고기가 있으면 false 반환
	private static boolean checkFish() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				if(map[r][c] > 0 && map[r][c] < 9) return false;
			}
		}
		return true;
	}
}
