package BAEKJOON;
/*
 * 최소비용 구하기
 */

import java.io.*;
import java.util.*;

public class BOJ1916 {
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
	static int N, M, start, end;
	static ArrayList<Node>[] adj;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
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
		
		int distance[] = new int[N + 1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		int min, current;
		
		// 처음 시작할 때 출발지: 0
		distance[start] = 0;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node p = queue.poll();
			current = p.e;
			
			if(current == -1) break;
			if(current == end) break;
			
			for (Node next : adj[current]) {				
				if(distance[next.e] > distance[current] + next.c) {
					distance[next.e] = distance[current] + next.c;
					queue.add(new Node(next.e, distance[next.e]));
				}
			}
		}
		
		System.out.println(distance[end]);
	}
}
