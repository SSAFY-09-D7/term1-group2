package BAEKJOON;
/*
 * 최소비용 구하기
 */

import java.io.*;
import java.util.*;

public class BOJ1916 {
	static class Node {
		int e, c;

		public Node(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [e=" + e + ", c=" + c + "]";
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, start, end;
	static ArrayList<Node>[] adj;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < adj.length; i++) {
			System.out.println(adj[i]);
		}
		
		int distance[] = new int[N + 1];
		boolean v[] = new boolean[N + 1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		int min, current;
		
		// 처음 시작할 때 출발지: 0
		distance[start] = 0;
		
		for (int c = 1; c < N + 1; c++) {
			current = -1;
			min = Integer.MAX_VALUE;
			
			for(int i = 1; i < v.length; i++) {
				if(!v[i] && min > distance[i]) {
					min = distance[i];
					current = i;
				}
			}
			
			if(current == -1) break;
			if(current == end) break;
			
			v[current] = true; // 경유지로 설정!
			
			for (Node next : adj[current]) {				
				if(!v[next.e] && distance[next.e] > distance[current] + next.c) {
					distance[next.e] = distance[current] + next.c;
				}
			}
		}
		
		System.out.println(Arrays.toString(distance));
		System.out.println(distance[end]);
	}
}
