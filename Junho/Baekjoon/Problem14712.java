package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//넴모넴모(Easy)
/*
 * n*m 크기에 따라 경우의 수 2의 n*m제곱
 * 가지의 수 2개
 * */
public class Problem14712 {

	static int[][] board;
	static int n, m;
	static int count;
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		recur(0, 0);

		System.out.println(count);
	}

	private static void recur(int x, int y) {

		if (x == n) {
			for(int i=0;i<board.length;i++) {
				for(int j=0;j<board[i].length;j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			// 2*2 사각형이 있다면 return
			if (check())
				return;

			count += 1;
			return;
		}
		int next_x,next_y;
		if (y + 1 == m)
			next_y = 0;
		else
			next_y = y + 1;
		if (next_y == 0)
			next_x = x + 1;
		else
			next_x = x;

		board[x][y] = 1; // 배치하기

		recur(next_x,next_y); //가지수 1
		board[x][y] = 0; // 없애기
		recur(next_x,next_y); //가지수 2
		
	}

	
	
//2*2 사각형이 있는지 검사
	private static boolean check() {
		for (int i = 0; i <= n - 2; i++) {
			for (int j = 0; j <= m - 2; j++) {
				if (board[i][j] == 1 && board[i + 1][j] == 1 && board[i][j + 1] == 1 && board[i + 1][j + 1] == 1)
					return true;
			}
		}
		return false;
	}

}
