package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 해킹
 */
public class BOJ10282 {
	static class Node implements Comparable<Node>{
		int e, c, count;

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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, n, d, c, count, result;
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[n + 1];
			
			result = Integer.MIN_VALUE;
			count = 0;
			
			for (int i = 0; i < adj.length; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adj[b].add(new Node(a, s));
			}
			
			int d[] = new int[n + 1];
			boolean v[] = new boolean[n + 1];
			
			Arrays.fill(d, Integer.MAX_VALUE);
			d[c] = 0;
			
			PriorityQueue<Node> queue = new PriorityQueue<>();
			queue.add(new Node(c, 0));
			
			while(!queue.isEmpty()) {
				Node current = queue.poll();
				if(!v[current.e]) {
					count++;
					v[current.e] = true;
				}
				
				for (Node next : adj[current.e]) {
					if(d[next.e] > d[current.e] + next.c) {
						d[next.e] = d[current.e] + next.c;
						queue.add(new Node(next.e, d[next.e]));
					}
				}
			}
			
			for(int i = 0; i < d.length; i++) {
				if(d[i] != Integer.MAX_VALUE) result = Math.max(result, d[i]);
			}
			sb.append(count + " "+ result + "\n");
		}
		System.out.println(sb);
	}
}
