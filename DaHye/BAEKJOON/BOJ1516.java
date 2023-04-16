package BAEKJOON;

/*
 * 게임 개발
 */

import java.io.*;
import java.util.*;

public class BOJ1516 {
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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, buildTime[], d[], resultTime[];	// buildTime: 건물을 짓는데 걸리는 시간, d: 진입차수 배열
	static ArrayList<Node> adj[];
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		buildTime = new int[N + 1];
		resultTime = new int[N + 1];
		d = new int[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cost = Integer.parseInt(st.nextToken());
			buildTime[i] = cost;
			
			while(st.hasMoreTokens()) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == -1) break;
				adj[tmp].add(new Node(i, buildTime[tmp])); // 인접리스트
				d[i]++;	// 진입차수 배열 초기화
			}
		}
		
		// 1. 진입차수가 0이면 Queue에 넣는다
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i < N + 1; i++) {
			if(d[i] == 0) queue.add(i);
		}
		
		// 2. 진입차수가 0인 것과 인접한 노드들의 진입차수를 1씩 빼줌
		// 3. 인접한 노드의 진입차수가 0이 되면 queue에 담아줌
		while(!queue.isEmpty()) {
			int current = queue.poll();

			for (Node next : adj[current]) {
				d[next.e]--;
				resultTime[next.e] = Math.max(resultTime[next.e], resultTime[current] + buildTime[current]);
				if(d[next.e] == 0) queue.add(next.e);
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			sb.append(buildTime[i] + resultTime[i] + "\n");
		}
		System.out.println(sb);
	}
}