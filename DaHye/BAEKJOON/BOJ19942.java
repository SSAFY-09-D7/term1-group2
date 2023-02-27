package BAEKJOON;

/*
 * 다이어트
 * 6가지의 식재료 중에서 몇 개를 선택해서 영양분의 각각 합이 100, 70, 90, 10이 되게 하는 경우
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ19942 {
	static class food {
		int num;
		int p; // 단백질
		int f; // 지방
		int s; // 탄수화물
		int v; // 비타민
		int c; // 가격
		public food(int p, int f, int s, int v, int c) {
			super();
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int mp, mf, ms, mv;	// 최소 영양성분
	static int N, map[][];
	static boolean sel[];
	static food[] foods;
	static int sumP, sumF, sumS, sumV, sumC;
	static int minCost = Integer.MAX_VALUE;
	static TreeSet<String>set = new TreeSet<>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		foods = new food[N];
		sel = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			foods[i] = new food(p, f, s, v, c);
		}
		
		func(0);
		
		if(minCost == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		} else {
			System.out.println(minCost);
			System.out.println(set.pollFirst());
		}
	}
	private static void func(int k) {
		if(k == N) {
			if(sumP >= mp && sumF >= mf && sumS >= ms && sumV >= mv) {
				if(sumC <= minCost) {
					// sum이 minCost 보다 작아질 때마다 set을 초기화
					if(sumC < minCost) set.clear();
					sb.setLength(0);
					minCost = Math.min(sumC, minCost);
					for (int i = 0; i < sel.length; i++) {
						if(sel[i] == true) {
							sb.append((i + 1) + " ");
						}
					} set.add(sb.toString());
				}
			}
			return;
		}
		
		sel[k] = true;
		sumFood(foods[k]);
		func(k + 1);
		
		sel[k] = false;
		subFood(foods[k]);

		func(k + 1);
	}
	
	private static void subFood(food foods) {
		sumP -= foods.p;
		sumF -= foods.f;
		sumS -= foods.s;
		sumV -= foods.v;
		sumC -= foods.c;
	}
	
	private static void sumFood(food foods) {
		sumP += foods.p;
		sumF += foods.f;
		sumS += foods.s;
		sumV += foods.v;
		sumC += foods.c;
	}
}