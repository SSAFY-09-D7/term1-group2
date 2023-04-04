package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 보호 필름
 * 단면의 모든 세로 방향에 대해 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우 성능 검사에서 통과하게 됨
 * 약물 최소 투입횟수: 2
 * D: 두께, W: 가로크기, K: 합격기준, 보호필름 단면의 정보
 */
public class SWEA2112 {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, D, W, K;
	static int mak[][], copy[][];	// 1: A, 0: B
	static int sel[], sel2[];
	static boolean result;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./Input/SWEA2112.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case < T + 1; test_case++) {
			System.out.println(test_case + "------------");
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			mak = new int[D][W];
			
			for (int r = 0; r < mak.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < mak[r].length; c++) {
					mak[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			print(mak);
			
			if(check(mak)) sb.append("#" + test_case + " " + 0 + "\n");
			else {
//				position();
				sb.append("#" + test_case + " " + position() + "\n");
			}
		}
		
		System.out.println(sb);
	}
	private static int position() {
		int i = 2;
		result = false;
		
		while(true) {
			sel = new int[i];
			func(0, 0);
			if(result = true) break;
			i++;
		}
		return i;
	}
	
	private static void func(int k, int idx) {
		if(k == sel.length) {
			copy= new int[mak.length][mak[0].length];
			copy = copyArray(mak);
			
//			System.out.println(Arrays.toString(sel));
			
			sel2 = new int[sel.length];
			System.out.println("--------");
			System.out.println(Arrays.toString(sel));
			result = putPosition(0);
//			if(putPosition(0))
//			System.out.println(putPosition(0));
			
			return;
		}
		
		for(int i = idx; i < D; i++) {
			sel[k] = i;
			func(k + 1, i + 1);
		}
		
	}

	private static boolean putPosition(int k) {
		if(k == sel2.length) {
			System.out.println(Arrays.toString(sel2));
			for(int i = 0; i < sel.length; i++) {
				Arrays.fill(copy[sel[i]], sel2[i]);
			}
			
			print(copy);
			System.out.println(check(copy));
			return check(copy);
		}

		sel2[k] = 0;
		putPosition(k + 1);
		sel2[k] = 1;
		putPosition(k + 1);
		
		return true;
	}
	
	private static int[][] copyArray(int[][] mak) {
		int tmp[][] = new int[mak.length][mak[0].length];
		
		for (int i = 0; i < mak.length; i++) {
			tmp[i] = Arrays.copyOf(mak[i], mak[i].length);
		}
		return tmp;
	}
	
	private static boolean check(int[][] mak) {
		for (int c = 0; c < mak[0].length; c++) {
			boolean flag = false;
			for (int r = 0; r < mak.length - (K - 1); r++) {
				if(checkMak(r, c)) flag = true;

				if(r == mak.length - K && !flag) return false;
			}
		}
		return true;
	}
	
	private static boolean checkMak(int r, int c) {
		int tmp = mak[r][c];
		
		for(int i = 1; i < K; i++) {
			if(tmp != mak[r + i][c]) return false;
		}
		return true;
	}
	
	private static void print(int[][] mak) {
		for (int i = 0; i < mak.length; i++) {
			System.out.println(Arrays.toString(mak[i]));
		}System.out.println("------------");
	}
}
