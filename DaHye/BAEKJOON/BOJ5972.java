package BAEKJOON;
/*
 * 택배 배송
 */

import java.io.*;
import java.util.*;

public class BOJ5972 {
	static class Node implements Comparable<Node> {
		int e, c;

		public Node(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M; // N: N개의 헛간(목적지), M: 간선
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
		
		int d[] = new int[N + 1];
		
		Arrays.fill(d, Integer.MAX_VALUE);
		
		d[1] = 0;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(1, 0));
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for (Node next : adj[current.e]) {
				if(d[next.e] > d[current.e] + next.c) {
					d[next.e] = d[current.e] + next.c;
					queue.add(new Node(next.e, d[next.e]));
				}
			}
		}
		
		System.out.println(d[N]);
	}
}
