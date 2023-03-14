package BAEKJOON;
/*
 * 빙산
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, time, map[][], afterMap[][], ice;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<int[]> iceCount;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			ice = 0;
			time++;
			
			afterMap = new int[N][M];
			
			iceCount = new LinkedList<>();
			
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if(map[r][c] != 0) {
						iceCount.offer(new int[] {r, c});
					}
				}
			}
			
			afterTime();
			for (int r = 0; r < afterMap.length; r++) {
				for (int c = 0; c < afterMap[r].length; c++) {
					if(afterMap[r][c] != 0) {
						map = copyArr();
						bfs(r, c);
					}
				}
			}
			
			if(ice == 2) break;
		}
		
		System.out.println(time);

	}

	private static void afterTime() {
		while(!iceCount.isEmpty()) {
			int[] point = iceCount.poll();
			int count = 0;
			
			for(int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(!check(nr, nc))  continue;
				if(map[nr][nc] != 0) {
					count++;
				}
			}
			
			afterMap[point[0]][point[1]] = map[]
		}
	}

	private static int[][] copyArr() {
		int [][] tmpArr;
		
		tmpArr = new int[N][M];
		
		for (int r = 0; r < tmpArr.length; r++) {
			tmpArr[r] = Arrays.copyOf(afterMap[r], afterMap[r].length);
		}
		return tmpArr;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		
		queue.offer(new int[] {r, c});
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			
			for(int i= 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!check(nr, nc)) continue;
				if(!v[nr][nc] && map[nr][nc] != 0) {
					v[nr][nc] = true;
					map[nr][nc] = 0;
					queue.add(new int[] {nr, nc});
				}
			}
			
		}
	}

	private static int func(int r, int c) {
		int count = 0;
		
		for(int i = 0; i < 4; i++) {
			int nr = map[r][c] + dr[i];
			int nc = map[r][c] + dc[i];
			
			if(!check(nr, nc)) continue;
			if(map[nr][nc] == 0) count++;
		}
		
		return count;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
