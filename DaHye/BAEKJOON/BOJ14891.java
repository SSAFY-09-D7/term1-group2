package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 톱니바퀴
 * 4개의 톱니바퀴, 총 K번 회전: 방향을 결정해야 됨
 * 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하는 프로그램
 * 12시 방향으로 시계방향 순서대로 주어짐, N: 0, S: 1
 */
public class BOJ14891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, N, D; // K: 회전 횟수, N: 회전 톱니바퀴 번호, D: 방향
	static Deque<Integer>[] wheel = new Deque[4];
	static int result = 0;
	static boolean flagR, flagL;
	static int R, L;
	
	public static void main(String[] args) throws Exception {
		// 톱니바퀴 정보 입력 받기
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			wheel[i] = new LinkedList<>();

			for (int c = 0; c < input.length(); c++) {
				wheel[i].add(input.charAt(c) - '0');
			}
		}

		K = Integer.parseInt(br.readLine()); // 회전 횟수 입력
		for (int count = 0; count < K; count++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 톱니바퀴 변호
			D = Integer.parseInt(st.nextToken()); // 방향 (1: 시계, -1: 반시계)
			flagR = false;
			flagL = false;
			R = (int) wheel[N - 1].toArray()[2];
			L = (int) wheel[N - 1].toArray()[6];
			
			funcR(N, D);
			if(2 <= N) funcL(N, D, L);
			
		}
		
		int tmp = 1;
		for(int i = 0; i < 4; i++) {
			result += (tmp  * wheel[i].pollFirst());
			tmp *= 2;
		}
		

		System.out.println(result);
	}

	private static void funcR(int n, int d) {
		
		int wheelR = (int) wheel[n - 1].toArray()[2];
		
		rotate(n, d);
		
		// 현재 바퀴의 오른쪽과 오른쪽 바퀴의 왼쪽과 비교하면서 다르면 자기가 도는 방향과 반대로 돌려주기
		for(int i = n; i < wheel.length; i++) {
			int nextWheelL = (int) wheel[n].toArray()[6];
			
			// 다음 바퀴 돌려주기
			if(wheelR != nextWheelL && !flagR) {
				if(i == wheel.length - 1) flagR = true;
				funcR(n + 1, d * -1);
			}
			else {
				flagR = true;
				return;
			}
		}
	}
	
	private static void funcL(int n, int d, int l) {
		if(n == 1) {
			flagL = true;
			return;
		}
		
		int wheelL = l;
		
		int prevWheelR = (int) wheel[n - 2].toArray()[2];
		if(wheelL != prevWheelR && !flagL) {
			wheelL = (int) wheel[n - 2].toArray()[6];
			rotate(n - 1, d*-1);
			
			funcL(n - 1, d * -1, wheelL);
		}
	}

	// d - 1: 시계, -1: 반시계
	private static void rotate(int n, int d) {
		int tmp = 0;
		if(d == 1) {
			tmp = wheel[n - 1].pollLast();
			wheel[n - 1].addFirst(tmp);
		} else if(d == -1) {
			tmp = wheel[n - 1].pollFirst();
			wheel[n - 1].addLast(tmp);
		}
	}
}
