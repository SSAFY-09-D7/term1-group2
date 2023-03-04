package SWEA;
/*
 * 프로세서 연결하기
 * 멕시노스의 가장 자리에는 전원이 흐르고 있음
 * 코어: 1, 전선: 2, 가장자리 코어: 3, 빈 공간: 0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1767 {
	static class Core {
		int r;
		int c;
		public Core(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Core [r=" + r + ", c=" + c + "]";
		}
		
	}
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, map[][], copyMap[][], copyTmp[][], line, minLine, count, maxCount;
	static ArrayList<Core> list;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./Input/SWEA1767.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < 2; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			copyMap = new int[N][N];

			list = new ArrayList<>();
			line = 0;
			minLine = Integer.MAX_VALUE;
			
			for (int r = 0; r < map.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					// 가장자리에 있는거면 3으로 바꿔줌
					if((map[r][c] == 1) && (r == 0 || r == N || c == 0 || c == N)) map[r][c] = 3;
					if(map[r][c] == 1) list.add(new Core(r, c));
				}
			}
			
			copyMap = copyArr(map);
			
			func(0);
			System.out.println(a);
			System.out.println(line);
			
		}
		

	}
	static int a;
	private static void func(int k) {
		if(k == list.size()) {
			int lineDis = 0;
			for(int r = 0 ; r < copyMap.length; r++) {
				for (int c = 0; c  < copyMap[r].length; c ++) {
					if(copyMap[r][c] == 2) lineDis++;
				}
			}
			System.out.println(count + " " + line);
			if(maxCount <= count) {
				if(maxCount == count) line = Math.min(lineDis, line);
				else {
					maxCount = count;
					line = lineDis;
				}
//				minLine = Math.min(minLine, line);
			}

			return;
		}
				
		copyTmp = copyArr(copyMap);
		
		// 상, 하, 좌, 우, X: 5가지 경우가 있음
		// 0: 상, 1: 하, 2: 좌, 3: 우, 4: X
		for(int i = 0; i < 5; i++) {
			copyTmp = copyArr(copyMap);
			if(!setLine(list.get(k), i)) {
				// 원상복구
//				System.out.println(copyMap);
				copyMap = copyTmp;
//				print(copyMap);
//				continue;
			} else {
				count++;
			}
//			print(copyMap);
//			System.out.println("======" + count + " " + list.toString());
			func(k + 1);
			if(back(list.get(k), i)) {
				count--;
			}
//			print(copyMap);
//			System.out.println("======" + count + " " + list.toString());
		}
	}


	private static int[][] copyArr(int[][] map) {
		int tmp[][] = new int[N][N];
		for (int r = 0; r < map.length; r++) {
			tmp[r] = Arrays.copyOf(map[r], map[r].length);
		}
		return tmp;
	}

	static int dr[] = {-1, 1, 0, 0, 0};
	static int dc[] = {0, 0, -1, 1, 0};
	// 0: 상, 1: 하, 2: 좌, 3: 우, 4: X
	private static boolean setLine(Core core, int i) {
		int nr = core.r;
		int nc = core.c;
		
		while(true) {
			nr += dr[i];
			nc += dc[i];
			if(nr < 0 || nr == N || nc < 0 || nc == N) break;
			
			if(copyMap[nr][nc] == 0) copyMap[nr][nc] = 2;
			else return false;

		}
		return true;
	}
	
	private static boolean back(Core core, int i) {
		int nr = core.r;
		int nc = core.c;
		
		while(true) {
			nr += dr[i];
			nc += dc[i];
			if(nr < 0 || nr == N || nc < 0 || nc == N) break;
			if(copyMap[nr][nc] != 2) return false;
			if(copyMap[nr][nc] == 2) copyMap[nr][nc] = 0;
		}
		return true;
	}


	private static boolean check(int nr, int nc) {
		if(map[nr][nc] != 0) return false;
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
