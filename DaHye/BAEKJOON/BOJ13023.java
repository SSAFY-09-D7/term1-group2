package BAEKJOON;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * ABCDE
 */
public class BOJ13023 {
	static ArrayList<Integer>[] adjList;
	static boolean v[];
	static int count;
	static boolean flag;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		adjList = new ArrayList[N + 1];
		v = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();

			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
		
		for(int i = 0; i < N; i++) {
			count = 0;
			dfs(i, new boolean[N]);
			if(flag == true) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}

	private static void dfs(int node, boolean[] visited) {
		v[node] = true;

		for (int i : adjList[node]) {
			if (v[i]) continue;
			count++;
			if(count == 4) {
				flag = true;
				return;
			}
			v[i] = true;
			dfs(i, visited);
			v[i] = false;
			count--;
		}
		v[node] = false;
	}
}
