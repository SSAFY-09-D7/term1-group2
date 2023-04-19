package BAEKJOON;

import java.io.*;
import java.util.*;

/*
 * 도시 건설
 */
public class BOJ21924 {
	static class Node implements Comparable<Node>{
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

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static long total, d;
	static ArrayList<Node> adj[];
	static boolean v[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
			total += c;
		}
		
		d = 0;
		v = new boolean[N + 1];
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		v[1] = true;
		queue.addAll(adj[1]);
		
		int count = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(v[current.e]) continue;
			v[current.e] = true;
			d += current.c;
			queue.addAll(adj[current.e]);
			count++;
			if(count == N - 1) break;
		}
		
		if(checkV()) System.out.println(total - d);
		else System.out.println(-1);
	}

	private static boolean checkV() {
		for (int i = 1; i < v.length; i++) {
			if(!v[i]) return false; 
		}
		return true;
	}
}
