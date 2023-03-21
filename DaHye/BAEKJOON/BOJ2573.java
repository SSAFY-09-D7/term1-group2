package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 빙산
 */

public class BOJ2573 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, time, map[][], copyMap[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 빙산의 개수를 세는 check함수
		while(!check()) {
			time++;
			
			if(checkMap()) {
				time = 0;
				break;
			}
			
			// 빙산 녹이기
			melt();

		}
		System.out.println(time);
	}

	private static void melt() {
		
		Queue<int[]> ice = new LinkedList<>();
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] != 0) {
					// 주변 빙산의 개수
					int countIce = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						if(map[nr][nc] == 0) countIce++;
					}
					
					ice.add(new int[] {r, c, countIce});
				}
			}
		}
		
		while(!ice.isEmpty()) {
			// 빙산를 녹이기 위해 빙산의 좌표, 바다로 둘러쌓인 면의 개수
			int[] calIce = ice.poll();
			
			// 빙산의 r 좌표
			int tr = calIce[0];
			// 빙산의 c 좌표
			int tc = calIce[1];
			
			int tIce = map[tr][tc] - calIce[2];
			map[tr][tc] = tIce >= 0 ? tIce : 0; 
		}
	}

	private static boolean checkMap() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c] != 0) return false;				
			}
		}
		return true;
	}

	private static boolean check() {
		int count = 0;
		// map의 복사본
		copyMap = copyArr();
		
		for (int r = 0; r < copyMap.length; r++) {
			for (int c = 0; c < copyMap[r].length; c++) {
				if(copyMap[r][c] != 0) {
					count++;
					bfs(r, c);
				}
			}
		}
		
		// 빙산의 개수가 2개 이상이라면 true를 반환
		return count >= 2 ? true : false;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		boolean v[][] = new boolean[N][M];
		
		queue.offer(new int[] {r, c});
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			int point[] = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(copyMap[nr][nc] != 0 && !v[nr][nc]) {
					copyMap[nr][nc] = 0;
					v[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	private static int[][] copyArr() {
		int tmp[][] = new int[N][M];
		
		for (int r = 0; r < map.length; r++) {
			tmp[r] = Arrays.copyOf(map[r], map[r].length);
		}
		return tmp;
	}
	
	private static void print(int[][] map2) {
		for (int r = 0; r < map2.length; r++) {
			System.out.println(Arrays.toString(map2[r]));
		}
		System.out.println("==============");
	}
}