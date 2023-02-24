package BAEKJOON;
/*
 * DFS와 BFS
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, V;
	static ArrayList<Integer>[] A;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 정점의 번호
		A = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			A[v1].add(v2);
			A[v2].add(v1);
			
		}
		
		// 방문순서를 위해 오름차순 정렬!
		for(int i = 1; i <= N; i++) {
			Collections.sort(A[i]);
		}
		visited = new boolean[N + 1];
		
		for(int i = 0; i <= N; i++) {
			visited[i] = false;
		}
		
		dfs(V);
		sb.append("\n");

		visited = new boolean[N + 1];
		bfs(V);
		
		System.out.println(sb);
	}

	
	private static void dfs(int node) {
		sb.append(node + " ");
		visited[node] = true;
		
		for(int i: A[node]) {
			if(!visited[i]) {
				dfs(i);
			}
		}
	}


	private static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		
		visited[node] = true;
		while(!queue.isEmpty()) {
			int nowNode = queue.poll();
			sb.append(nowNode + " ");
			for(int i : A[nowNode]) {
				if(!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	} 
}
