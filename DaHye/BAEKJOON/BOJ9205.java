package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 맥주 마시면서 걸어가기
 * 50미터를 가기 전에 맥주 한 병을 마셔야 됨
 * 한 박스에 맥주: 20 → 1000m를 이동할 수 있음
 */
public class BOJ9205 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb;
	static StringTokenizer st;
	static int t, n, point[][], distance[][];
	static boolean flag[][];
	
	public static void main(String[] args) throws Exception {
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int test_case = 0; test_case < t; test_case++) {
			n = Integer.parseInt(br.readLine());
			distance = new int[n + 2][n + 2];
			point = new int[n + 2][2];
			flag = new boolean[n + 2][n + 2];
			
			// 처음 집, 편의점, 페스티벌의 위치를 저장함
			for (int r = 0; r < distance.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 2; c++) {
					point[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			pointToDistance();

			for (int k = 0; k < n + 2; k++) { // 경유지
				for (int i = 0; i < n + 2; i++) { // 출발지
					for (int j = 0; j < n + 2; j++) {
						if(flag[i][k] && flag[k][j]) flag[i][j] = true;
					}
				}
			}
			
			// [0][n + 1]: 0번(집) ~ n + 1(페스티벌)
			String result = flag[0][n + 1] ? "happy" : "sad";
			
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}

	private static void print(int[][] distance) {
		for (int r = 0; r < distance.length; r++) {
			System.out.println(Arrays.toString(distance[r]));
		}
	}
	
	// 입력받은 point를 거리로 변환
	private static void pointToDistance() {
		for (int r = 0; r < distance.length; r++) {
			for (int c = 0; c < distance[r].length; c++) {
				int distance = Math.abs(point[r][0] - point[c][0]) + Math.abs(point[r][1] - point[c][1]);
				if(distance <= 1000) flag[r][c] = true;
			}
		}
	}
	
}
