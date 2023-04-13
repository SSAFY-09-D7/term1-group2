package BAEKJOON;
/*
 * 미로만들기
 */

import java.io.*;
import java.util.*;

public class BOJ2665 {
	static class Node implements Comparable<Node> {
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
	static int n, map[][];
	static ArrayList<Node> adj[];
	static boolean v[];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];

		adj = new ArrayList[n * n];
		v = new boolean[n * n];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int r = 0; r < n; r++) {
			String input = br.readLine();
			for (int c = 0; c < n; c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc)) continue;
					if(map[nr][nc] == 0) adj[n * r + c].add(new Node(n * nr + nc, 1));
					if(map[nr][nc] == 1) adj[n * r + c].add(new Node(n * nr + nc, 0));
				}
			}
		}
		
		int d[] = new int[n * n];
		
		Arrays.fill(d, Integer.MAX_VALUE);
		
		d[0] = 0;
		v[0] = true;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0));
		v[0] = true;
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for (Node next: adj[current.e]) {
				if(d[next.e] > d[current.e] + next.c) {
					d[next.e] = d[current.e] + next.c;
					queue.add(new Node(next.e, d[next.e]));
				}
			}
		}
		System.out.println(d[d.length - 1]);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < n;
	}
}
