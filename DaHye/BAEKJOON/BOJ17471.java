package BAEKJOON;
/*
 * 게리맨더링
 * N: 구역의 개수
 * 구역의 인구
 * 구역과 인접한 구역의 정보
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, people[];
	static ArrayList<Integer>[] adj;
	static boolean isArea[];
	static int minSubPeople = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		people = new int[N + 1];
		isArea = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < people.length; i++)
			people[i] = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i < adj.length; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for (int j = 0; j < a; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				adj[i].add(tmp);
			}
		}
		
		func(0);
		
		System.out.println(minSubPeople = minSubPeople == Integer.MAX_VALUE ? -1: minSubPeople);
	}

	private static void func(int k) {
		if (k == isArea.length) {

			if (check()) {
				int peopleA = 0;
				int peopleB = 0;

				for (int i = 0; i < isArea.length; i++) {
					if (isArea[i])
						peopleA += people[i + 1];
					else
						peopleB += people[i + 1];
				}
				int tmp = Math.abs(peopleA - peopleB);
				minSubPeople = Math.min(minSubPeople, tmp);
			}
			return;
		}

		isArea[k] = true;
		func(k + 1);

		isArea[k] = false;
		func(k + 1);
	}
	
	private static boolean check() {
		int areaA = -1; // A 구역(true)인 구역
		int areaB = -1; // B 구역(false)인 구역
		boolean[] v = new boolean[N + 1];

		for (int i = 0; i < isArea.length; i++) {
			if (isArea[i]) { // true일 때 시작점 idx 얻기
				areaA = i + 1;
				break;
			}
		}

		for (int i = 0; i < isArea.length; i++) {
			if (!isArea[i]) { // false일 때 시작점 idx 얻기
				areaB = i + 1;
				break;
			}
		}
		
		// 둘 중 하나라도 -1일 경우 모든 구역이 연결되어 있는 것이므로 false 반환
		if (areaA == -1 || areaB == -1)
			return false;

		// A 구역 확인
		Queue<Integer> queue = new LinkedList<>();

		queue.add(areaA);
		v[areaA] = true;

		while (!queue.isEmpty()) {
			int area = queue.poll();
			
			for (int i = 0; i < adj[area].size(); i++) {
				if (!isArea[adj[area].get(i) - 1] || v[adj[area].get(i)])continue; // 자기구역 아니면 넘어가기
				queue.add(adj[area].get(i));
				v[adj[area].get(i)] = true;
			}
		}

		// B 구역 확인
		queue.add(areaB);
		v[areaB] = true;
		while (!queue.isEmpty()) {
			int area = queue.poll();
			for (int i = 0; i < adj[area].size(); i++) {
				if (isArea[adj[area].get(i) - 1] || v[adj[area].get(i)]) continue;
				queue.add(adj[area].get(i));
				v[adj[area].get(i)] = true;
			}
		}

		// 한 곳이라도 연결되어 있지 않을 때
		for (int i = 1; i < v.length; i++) {
			if (!v[i])
				return false;
		}
		return true;
	}
}

