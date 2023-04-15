package BAEKJOON;
/*
 * 파티
 */

import java.io.*;
import java.util.*;

public class BOJ1238 {
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
	static StringTokenizer st;
	static int N, M, X, d1[][], d2[], result;
	static ArrayList<Node>[] adj;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		d1 = new int[N + 1][N + 1];
		d2 = new int[N + 1];
		
		Arrays.fill(d2, 987654321);
		result = Integer.MIN_VALUE;
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, c));

			d1[a][b] = c;
		}

		for (int r = 1; r < N + 1; r++) {
			for (int c = 0; c < N + 1; c++) {
				if (r != c && d1[r][c] == 0)
					d1[r][c] = 987654321;
			}
		}

		floyd();
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		d2[X] = 0;
		
		queue.add(new Node(X, 0));
		
		while(!queue.isEmpty()) {
			Node p = queue.poll();
			int minIdx = p.e;
			
			for (Node next : adj[minIdx]) {
				if(d2[next.e] > d2[minIdx] + next.c) {
					d2[next.e] = d2[minIdx] + next.c;
					queue.add(new Node(next.e, d2[next.e]));
				}
			}
		}
	
		sumDistance();
		
		System.out.println(result);
	}

	private static void sumDistance() {
		for(int i = 1; i < N + 1; i++) {
			d2[i] += d1[i][X];
			result = Math.max(result, d2[i]);
		}
	}

	private static void print(int[][] d) {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < d.length; j++) {
				System.out.print(d1[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void floyd() {
		for(int k=1; k<N + 1; k++) {
			for(int i=1; i<N + 1; i++) {
				if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
				for(int j=1; j<N + 1; j++) {
					if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					if(d1[i][j] > d1[i][k]+d1[k][j]) {
						d1[i][j] = d1[i][k]+d1[k][j];
					}
				}
			}
		}
	}
}
