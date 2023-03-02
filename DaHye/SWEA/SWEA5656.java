package SWEA;
/*
 * 벽돌 깨기
 * H X W 배열
 * 
 * 입력
 * - T: 총 테스트 케이스 개수
 * - N: 구슬의 개수 W:너비, H: 높이
 * - map[]: 벽돌의 정보
 * 
 * 출력
 * - 최대한 많은 벽돌을 깨트리는 방법
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5656 {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, W, H, map[][], tmp[][];
	static int sel[];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int minWall;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./Input/SWEA5656.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			minWall = Integer.MAX_VALUE;
			
			map = new int[H][W];
			sel = new int[N];
			
			for (int r = 0; r < map.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			func(0);
			
			sb.append("#" + test_case + " " + minWall + "\n");
		}
		System.out.println(sb);
	}

	private static void func(int k) {
		if(k == N) {
			copyArr();
			
			for (int i = 0; i < sel.length; i++) {
				boolean flag = false;
				for(int r = 0; r < tmp.length; r++) {
					if(tmp[r][sel[i]] != 0) {
						fuk(r, sel[i]);
						replaceWall();
						flag = true;
					}
					if(flag == true) break;
				}
			}
			
			int leftWall = countWall();
			minWall = Math.min(leftWall, minWall);
			
			return;
		}
		
		for(int i = 0; i < W; i++) {
			sel[k] = i;
			func(k + 1);
		}
	}

	private static void replaceWall() {
		for(int c = 0; c < W; c++) {
			for(int r = H - 1; r > 0; r--) {
				if(tmp[r][c] == 0) {
					flag = false;
					wallDown(r, c);
				}
			}
		}
	}

	private static void wallDown(int i, int j) {
		if(i == 0) {
			return;
		}
		for(int r = i - 1; r >= 0; r--) {
			if(tmp[r][j] != 0) {
				tmp[i][j] = tmp[r][j];
				tmp[r][j] = 0;
				break;
			}
		}
		wallDown(i - 1, j);
	}
	private static int countWall() {
		int count = 0;
		for (int r = 0; r < tmp.length; r++) {
			for (int c = 0; c < tmp[r].length; c++) {
				if(tmp[r][c] != 0) count++;
			}
		}
		return count;
	}

	private static void fuk(int r, int sel) {
		int dist = tmp[r][sel];
		
		tmp[r][sel] = 0;
		
		// 상하좌우
		for(int d = 0; d < 4; d++) {
			for(int i = 1; i < dist; i++) {
				int nr = r + dr[d] * i;
				int nc = sel + dc[d] * i;
				if(!check(nr, nc)) continue;
				
				fuk(nr, nc);
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W;
	}

	private static void copyArr() {
		tmp = new int[H][W];
		for (int i = 0; i < map.length; i++) {
			tmp[i] = Arrays.copyOf(map[i], map[i].length);
		}
	}
}
