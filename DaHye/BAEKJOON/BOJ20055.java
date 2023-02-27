package BAEKJOON;
/*
 * 컨베이어 벨트 위의 로봇
 * ~10:30
 * 길이가 2N인 벨트가 컨베이어 벨트를 위아래로 감싸며 돌고있음
 * 1번 칸이 있는 위치: 올리는 위치
 * N번 칸이 있는 위치: 내리는 위치
 * 로봇: 올리는 위치에만 올릴 수 있음, 내리는 위치에 도달하면 즉시 내림
 * 로봇이 어떤 칸으로 이동하면 내구도는 즉시 1만큼 감소
 * 입력
 * - N, K: N: 열의 개수, K: 내구도가 0인 칸의 개수가 K개 이상일 때 종료
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20055 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, arr[][];
	static boolean[] v; // 로봇이 있는지 없는지
	static int count;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[2][N];
		v = new boolean[N];

		// 내구도 입력
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
				if (i == 1) {
					arr[i][N - 1 - j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		while(true) {
			count++;
			
			// 배열 돌리기
			rotate();
			// 로봇 돌리기
			rotateV();
			
			// 로봇 옮기기
			moveRobote();
			
			// 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇 올리기
			locateRobote();
			
			if(check() >= K) break;
		}
		System.out.println(count);
	}

	private static void locateRobote() {
		if(arr[0][0] > 0) {
			v[0] = true;
			arr[0][0]--;
		}
	}

	private static void moveRobote() {
		for(int i = v.length - 2; i >= 0; i--) {
			if(v[i] == true) {
				if(v[i + 1] == false && arr[0][i + 1] > 0) {
					v[i + 1] = true;
					v[i] = false;
					arr[0][i + 1]--;
				}
			}
		}
		v[N - 1] = false;
	}

	// 내구도가 0인 칸의 개수가 K개 이상인지 check 하는 함수
	private static int check() {
		int count = 0;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				if(arr[r][c] == 0) count++;
			}
		}
		return count;
	}

	private static void rotate() {
		int tmp1 = arr[1][0];
		int tmp2 = arr[0][N - 1];
		
		for(int i = N - 2; i >= 0; i--) {
			arr[0][i + 1] = arr[0][i];
		}
		
		for(int i = 0; i < N -1; i++) {
			arr[1][i] = arr[1][i + 1];
		}
		arr[0][0] = tmp1;
		arr[1][N-1] = tmp2;
	}
	
	private static void rotateV() {
		for(int i = v.length - 2; i >= 0; i--) {
			v[i + 1] = v[i];
		}
		v[0] = false;
		v[N - 1] = false;
	}
}
