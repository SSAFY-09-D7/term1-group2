package SWEA;
/*
 * 벌꿀채취
 * 칸의 숫자: 꿀의 양
 * 각각의 일꾼: 가로로 연속되도록 M개의 벌꿀 채취, 벌통 겹치면 안됨
 * 꿀 채취: 일부분만 채취 X, 벌통에 있는 모든 꿀을 한번에 채취
 * 용기에 있는 꿀의 양이 많을수록 상품가치가 높음
 * 출력: 두 일꾼이 꿀을 채취하여 얻을 수 있는 수익의 합이 최대가 되는 경우, 그 떄의 최대 수익
 * 
 * N: 벌통들의 크기
 * M: 선택할 수 있는 벌통의 개수
 * C: 벌꿀을 채취할 수 있는 최대 양
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2115 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, C, map[][], max, tmp;
	static boolean v[][];
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static Point[] worker;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./Input/SWEA2115.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			worker = new Point[2];
			max = 0;
			map = new int[N][N];
			v = new boolean[N][N];
			
			for (int r = 0; r < map.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			func(0, 0);
			sb.append("#" + test_case + " " + max + "\n");
		}
		System.out.println(sb);
	}

	private static void func(int k, int idx1) {
		if(k == 2) {

			// 꿀의 최대양을 넘지 않도록 꿀 채취하기
			// 일꾼별로 N만큼 벌꿀 채취
			int tmpH1 = 0;
			int tmpH2 = 0;
			
			ArrayList<Integer> honey1 = new ArrayList<>();
			ArrayList<Integer> honey2 = new ArrayList<>();
			
			for(int i = 0; i < M; i++) {
				tmpH1 += map[worker[0].r][worker[0].c + i];
				honey1.add(map[worker[0].r][worker[0].c + i]);
				
				tmpH2 += map[worker[1].r][worker[1].c + i];
				honey2.add(map[worker[1].r][worker[1].c + i]);
			}
			
			tmp = 0;
			tmpH1 = findMax(honey1, 0, new boolean[honey1.size()]);
			
			tmp = 0;
			tmpH2 = findMax(honey2, 0, new boolean[honey2.size()]);

			max = Math.max(max, tmpH1 + tmpH2);
			return;
		}
		
		for(int r = idx1; r < N; r++) {
			for(int c = 0; c < N - M + 1; c++) {
				boolean flag = false;
				for(int h = 0; h < M; h++) {
					if(v[r][c + h]) {
						flag = true;
						break;
					}
				}
				
				if(flag == true) continue;
				
				worker[k] = new Point(r, c);
				
				for(int h = 0; h < M; h++) {
					v[r][c + h] = true;
				}
				
				func(k + 1, idx1 + 1);
				
				for(int h = 0; h < M; h++) {
					v[r][c + h] = false;
				}
			}
		}
	}
	
	// 합계가 C를 넘지 않을 때의 모든 원소를 제곱하고 합
	private static int findMax(ArrayList<Integer> honey1, int idx, boolean[] sel) {
		if(idx == honey1.size()) {
			int sum = 0;
			int max = 0;
			for(int i = 0; i < sel.length; i++) {
				if(sel[i] == true) {
					sum += honey1.get(i);
					max += Math.pow(honey1.get(i), 2);
				}
				if(sum <= C) {
					tmp = Math.max(max, tmp);
				}
			}
			return tmp;
		}
		
		sel[idx] = true;
		findMax(honey1, idx + 1, sel);
			
		sel[idx] = false;
		findMax(honey1, idx + 1, sel);
		
		return tmp;
	}
}
