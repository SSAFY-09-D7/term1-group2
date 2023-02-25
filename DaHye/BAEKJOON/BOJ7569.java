package BAEKJOON;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 토마토
 * 토미토가 다 익게 되는 최소 일 수
 * 입력
 * - M, N: 상자의 크기를 나타내는 정수, H: 상자의 수
 * 
 * 0: 익지 않은 토마토, -1 토마토가 있지 않은 상태, 1: 익은 토마토
 */
public class BOJ7569 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, H, map[][][];
	static int days = -1;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		for(int h = 0; h < map.length; h++) {
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					String tmp = st.nextToken();
					if(tmp.equals("-1")) map[h][r][c] = -1;
					else map[h][r][c] = Integer.parseInt(tmp);
				}
			}
		}
		
		bfs();
		if(!fullTomato()) System.out.println(-1);
		else {
			System.out.println(days + 1);			
		}
	}

	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dr = {0, 0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 0, 1, -1};
	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] v = new boolean[H][N][M];
		
		for (int h = 0; h < map.length; h++) {
			for (int r = 0; r < map[h].length; r++) {
				for (int c = 0; c < map[h][r].length; c++) {
					if(map[h][r][c] == 1) {
						queue.add(new int[] {h, r, c, 0});
						v[h][r][c] = true;
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();

			for(int sH = 0; sH < dh.length; sH++) {

				for(int s = 0; s < 6; s++) {
					int nh = point[0] + dh[s];
					int nr = point[1] + dr[s];
					int nc = point[2] + dc[s];
					
					if(!check(nh, nr, nc)) continue;
					if(map[nh][nr][nc] == 0 && !v[nh][nr][nc]) {
						map[nh][nr][nc] = 1;
						if(fullTomato()) {
							days = point[3];
							return;
						}
						v[nh][nr][nc] = true;
						queue.add(new int[] {nh, nr, nc, point[3] + 1});
					}
				}
			}
		}
	}
	
	private static boolean fullTomato() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				for (int h = 0; h < map[i][j].length; h++) {
					if(map[i][j][h] == 0) return false;
				}
			}
		}
		return true;
	}

	private static boolean check(int h, int r, int c) {
		return h >= 0 && h < H && r >= 0 && r < N && c >= 0 && c < M;
	}
}