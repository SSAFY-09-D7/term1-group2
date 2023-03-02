package BAEKJOON;
/*
 * 최단경로
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1753 {
	static class Node { 
		int e;
		int w;
		public Node(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int V, E, K, u, v, w;
	static ArrayList<Node>[] adj; 
	static int dist[];
	static boolean visit[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V + 1];		
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		visit = new boolean[V + 1];
		
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList();
		
		// 시작 정점의 번호
		K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Node(v, w));
		}
		
		dist[K] = 0;
		
		for (int i = 1; i < V; i++) {
			int minIdx = -1;
			int minDist = Integer.MAX_VALUE;
			
			for (int j = 1; j < V + 1; j++) {
				if(minDist > dist[j] && !visit[j]) {
					minDist = dist[j];
					minIdx = j;
				}
			}
			
			if(minIdx == -1) break;
			
			visit[minIdx] = true;
			
			for (Node next : adj[minIdx]) {
				if(!visit[next.e] && dist[next.e] > dist[minIdx] + next.w) {
					dist[next.e] = dist[minIdx] + next.w;
				}
			}
		}
		
		for (int i = 1; i < dist.length; i++) {
			if(dist[i] != Integer.MAX_VALUE) sb.append(dist[i] + " \n");
			else sb.append("INF" + "\n");
		}
		
		System.out.println(sb);
	} 
}
