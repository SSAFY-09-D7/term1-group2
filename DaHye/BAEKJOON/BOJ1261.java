package BAEKJOON;
/*
 * 알고스팟
 */

import java.io.*;
import java.util.*;

public class BOJ1261 {
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
	static int N, M, map[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		adj = new ArrayList[N * M + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int r = 0; r < map.length; r++) {
			String input = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(!check(nr, nc)) continue;
					if(map[nr][nc] == 1) adj[r * M + c + 1].add(new Node(nr * M + nc + 1, 1));
					if(map[nr][nc] == 0) adj[r * M + c + 1].add(new Node(nr * M + nc + 1, 0));
				}
			}
		}

		PriorityQueue<Node> queue = new PriorityQueue<>();
		int d[] = new int[N * M + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[1] = 0;
		
		queue.offer(new Node(1, 0));
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.e == N * M) break;
			for (Node next : adj[current.e]) {
				if(d[next.e] > d[current.e] + next.c) {
					d[next.e] = d[current.e] + next.c;
					queue.add(new Node(next.e, d[next.e]));
				}
			}
		}
		
		System.out.println(d[d.length -1]);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
