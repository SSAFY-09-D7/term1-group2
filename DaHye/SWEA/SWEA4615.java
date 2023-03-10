package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4615 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./DaHye/Input/SWEA4615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 한 변의 길이
			int N = Integer.parseInt(st.nextToken());
			// 플레이어가 돌을 놓는 횟수
			int M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];

			// 돌을 놓을 수 있는 공간 수
			int leftSpace = N * N - 4;

			// 상 우 하 좌
			int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
			int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

			// 돌의 초기 위치 설정
			for (int i = arr.length / 2 - 1; i <= arr.length / 2; i++) {
				for (int j = arr[i].length / 2 - 1; j <= arr.length / 2; j++) {
					if (i == j)
						arr[i][j] = 2;
					else
						arr[i][j] = 1;
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				// 흑돌 놓기
				if (col == 1)
					arr[r - 1][c - 1] = 1;
				// 백돌 놓기
				else
					arr[r - 1][c - 1] = 2;
				// 돌을 놓고, 남은 공간 수 1 줄이기
				leftSpace--;
//				for (int j = 0; j < arr.length; j++) {
//					for (int j2 = 0; j2 < arr.length; j2++) {
//						System.out.print(arr[j][j2] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("-----------");

				for (int j = 0; j < 8; j++) {
					List<Integer> nX = new ArrayList<>();
					List<Integer> nY = new ArrayList<>();
					for (int k = 1; k < arr.length; k++) {
						int tmpR = r - 1;
						int tmpC = c - 1;
						tmpR = tmpR + k * dr[j];
						tmpC = tmpC + k * dc[j];

						if (tmpR >= 0 && tmpR < arr.length && tmpC >= 0 && tmpC < arr.length) {
							if(arr[tmpR][tmpC] == 0) break;
							else if (arr[tmpR][tmpC] == col) {
								for(int l = 0; l < nX.size(); l++) {
									arr[nX.get(l)][nY.get(l)] = col;
								} break;
							} else {
								nX.add(tmpR);
								nY.add(tmpC);
							}
						} else break;
					}

				}

			}

			int countB = 0;
			int countW = 0;

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
//					System.out.print(arr[i][j]);
					if (arr[i][j] == 1)
						countB++;
					else if (arr[i][j] == 2)
						countW++;
				}
//				System.out.println();
			}

			System.out.println("#" + test_case + " " + countB + " " + countW);
		}

	}

}
