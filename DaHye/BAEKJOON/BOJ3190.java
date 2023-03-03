package BAEKJOON;

/*
 * 뱀
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ3190 {
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
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };
	static int d = 0;
	static int time = 0;
	static Deque<Point> snake = new LinkedList<>();
	static boolean flag;

	static class Order {
		int time;
		char dir;

		public Order(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}

	static ArrayList<Order> orders;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		map = new int[N][N];
		orders = new ArrayList<>();

		snake.add(new Point(0, 0));
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			map[r][c] = 1; // 사과가 있으면 1로 표시
		}

		L = Integer.parseInt(br.readLine()); // 뱀의 변환 횟수

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);

			orders.add(new Order(t, c));
		}

		while (true) {
			Point head = snake.peekFirst();
			map[head.r][head.c] = -1;
			time++;

			int nextHeadR = head.r + dr[d];
			int nextHeadC = head.c + dc[d];

			if (!check1(nextHeadR, nextHeadC)) break;
			if (!check2(nextHeadR, nextHeadC)) break;
			
			if (map[nextHeadR][nextHeadC] == 1) {
				for (int k = 0; k < snake.size(); k++) {
					Point tmp = (Point) snake.toArray()[k];
					map[tmp.r][tmp.c] = -1;
				}

				map[nextHeadR][nextHeadC] = -1;
				snake.addFirst(new Point(nextHeadR, nextHeadC));
			}

			if (map[nextHeadR][nextHeadC] == 0) {
				map[nextHeadR][nextHeadC] = -1;
				Point tail = snake.pollLast();
				map[tail.r][tail.c] = 0;
				snake.addFirst(new Point(nextHeadR, nextHeadC));
			}

			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).time == time) {
					if (orders.get(i).dir == 'D') d = (d + 1) % 4;
					if (orders.get(i).dir == 'L') d = (d - 1 + 4) % 4;
				}
			}
		}

		System.out.println(time);
	}

	private static boolean check2(int r, int c) {
		if(map[r][c] == -1) return false;
		return true;
	}

	private static boolean check1(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}