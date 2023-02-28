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
		for (int count = 0; count < K + 1; count++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 톱니바퀴 변호
			D = Integer.parseInt(st.nextToken()); // 방향 (1: 시계, -1: 반시계)

			func(N, D);
			
			for(int i = 0; i < wheel.length ;i++) {
				System.out.println(wheel[i]);
			}
		}
	}

	private static void func(int n, int d) {
		rotate(n, d);
		int wheelR = (int) wheel[n - 1].toArray()[2];
		int wheelL = (int) wheel[n - 1].toArray()[6];
		
		rotate(n, d);
		
		// 현재 바퀴의 오른쪽과 오른쪽 바퀴의 왼쪽과 비교하면서 다르면 자기가 도는 방향과 반대로 돌려주기
		for(int i = n; i < wheel.length; i++) {
			int nextWheelL = (int) wheel[n].toArray()[6];
			
			// 다음 바퀴 돌려주기
			if(wheelR != nextWheelL) {
				func(n + 1, d * -1);
			}
			else break;
		}
		
		// 현재 바퀴의 왼쪽과 왼쪽 바퀴의 오른쪽과 비교하면서 다르면 자기가 도는 방향과 반대로 돌려주기
		if(n - 2 >= 0) {
			for(int i = n - 2; i >= 0; i--) {
				int nextWheelR = (int) wheel[n - 2].toArray()[2];
				
				if(wheelL != nextWheelR) {
					func(n - 2, d * -1);
				} else break;
			}
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
