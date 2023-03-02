package BAEKJOON;
/*
 * 뱀
 * 사과를 먹으면 뱀 길이가 늘어남
 * 벽 또는 자기자신의 몸과 부딪히면 게임 끝
 * - 몸길이를 늘려 머리를 다음칸에 이동
 * - 이동한 칸에 사과 O: 사과가 없어지고 꼬리가 움직이지 않음
 * - 이동한 칸에 사과 X: 몸 길이 줄이고 꼬리가 위치한 칸 비워주기
 * 
 * map: 1행 1열 기준
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190 {
	static class Snake {
		Point head;
		Point tail;
		public Snake(Point head, Point tail) {
			super();
			this.head = head;
			this.tail = tail;
		}
	}
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static BufferedReader br;
	static StringTokenizer st;
	static int N, K, L, X, map[][];
	static char C;
	// 우, 하, 좌, 상
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
	static int d = 0;
	static int time;
	static Snake snake;
	static Queue<Point> snakeBody = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./DaHye/Input/BOJ3190.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 보드의 크기
		K = Integer.parseInt(br.readLine());	// 사과의 개수
		map = new int[N][N];
		
		snake = new Snake(new Point(0, 0), new Point(0, 0));
		snakeBody.add(new Point(0, 0));
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			map[r][c] = 1; // 사과가 있으면 1로 표시
		}
		
		L = Integer.parseInt(br.readLine());	// 뱀의 변환 횟수
		
		L: for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			
			X = Integer.parseInt(st.nextToken());
			
			Queue<Point> queue = new LinkedList<>();
			
			for(int t = 0; t < X; t++) {
				Point head = snakeBody.peek();

				time++;
				head.r += dr[i];
				head.c += dc[i];
				
				int tmp = map[head.r][head.c];
				if(!check(head.r, head.c)) break L;
				
				map[head.r][head.c] = -1;	// 뱀의 머리가 있는 곳은 -1로 
				snakeBody.add(new Point(head.r, head.c));
				
				queue.add(new Point(head.r, head.c));
				
				// 뱀이 가다가 사과를 먹으면 몸통의 길이가 늘어나야 됨
				if(tmp == 1) {
					while(!queue.isEmpty()) {
						Point p = queue.poll();
						map[p.r][p.c] = -1;
					}
				}
				if(tmp == 0) {
					Point p = snakeBody.poll();
					map[p.r][p.c] = 0;
				}
			}

			print(map);

			C = st.nextToken().charAt(0);	// L: 왼쪽으로 90도 회전, R: 오른쪽 90도 회전
			
			if(C == 'D') d = (d + 1) % 4;
			if(C == 'L') {
				if(d == 0) d = 3;
				else d--;
			}
		}
		System.out.println(time);

	}

	private static boolean check(int r, int c) {
		boolean flag1 = false;
		boolean flag2 = false;
		if(r >= 0 && r < N && c >= 0 && c < N) flag1 = true;
		if(map[r][c] != -1) flag2 = true;
		return flag1 && flag2;
	}

	private static void print(int[][] map) {
		for(int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
