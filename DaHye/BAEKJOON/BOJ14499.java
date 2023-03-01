package BAEKJOON;
/*
 * 주사위 굴리기
 * 주사위를 굴렸을 때 이동한 칸의 숫자가 0이면 주사위 바닥에 쓰인 수가 복사
 * 0이 아닌 경우 칸에 쓰인 숫자가 주사위 면에 복사 & 칸에 쓰인 숫자: 0
 * 주사위가 이동했을 때 마다 상단에 쓰여 있는 값 구하기
 * 
 * 입력
 * - N: 세로, M: 가로, x, y: 주사위를 놓은 곳의 좌표, K: 명령의 개수
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14499 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, r, c, K;
	static int map[][];
	static int dr[] = {0, 0, -1, 1}; // 동, 서, 북, 남
	static int dc[] = {1, -1, 0, 0};
	static int dice[] = {0, 0, 0, 0, 0, 0, 0};

	// 주사위 바닥: [3][1]
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dice[6] = map[r][c];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			
			int tmpR = r;
			int tmpC = c;
			
			tmpR += dr[d - 1];
			tmpC += dc[d - 1];
			
			if(!check(tmpR, tmpC)) continue;
			
			r = tmpR;
			c = tmpC;
			
			// 주사위 굴리기
			dice = func(d);
			
			if(map[r][c] == 0) {
				map[r][c] = dice[6];
			} else {
				dice[6] = map[r][c];
				map[r][c] = 0;
			}
			System.out.println(dice[1]);
		}
	}

	private static boolean check(int tmpR, int tmpC) {
		return tmpR >= 0 && tmpR < N && tmpC >= 0 && tmpC < M;
	}
	
	// 좌우로 굴리기
	private static int[] func(int d) {
		int[] tmp = new int[] {0, 0, 0, 0, 0, 0, 0};
		// 동쪽으로 굴리기
		if(d == 1) {
			tmp[2] = dice[2];
			tmp[5] = dice[5];
			tmp[1] = dice[4];
			tmp[3] = dice[1];
			tmp[4] = dice[6];
			tmp[6] = dice[3];
		}
		// 서쪽으로 굴리기
		if(d == 2) {
			tmp[2] = dice[2];
			tmp[5] = dice[5];
			tmp[1] = dice[3];
			tmp[3] = dice[6];
			tmp[4] = dice[1];
			tmp[6] = dice[4];
		}
		// 북쪽으로 굴리기
		if(d == 3) {
			tmp[3] = dice[3];
			tmp[4] = dice[4];
			tmp[2] = dice[1];
			tmp[1] = dice[5];
			tmp[5] = dice[6];
			tmp[6] = dice[2];
		}
		// 남쪽으로 굴리기
		if(d == 4) {
			tmp[3] = dice[3];
			tmp[4] = dice[4];
			tmp[6] = dice[5];
			tmp[5] = dice[1];
			tmp[1] = dice[2];
			tmp[2] = dice[6];
		}
		return tmp;
	}
}
