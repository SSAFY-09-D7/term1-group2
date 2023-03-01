package BAEKJOON;
/*
 * 최소 스패닝 트리
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1197 {
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, A, B, C;
	static int cnt;
	static long sum;
	static ArrayList<Edge> edges = new ArrayList<>();
	static int set[];
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		set = new int[V + 1];
		
		// make Set
		for(int i = 0; i < set.length; i++) {
			set[i] = i;
 		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(A, B, C));
		}
		
		// 1. 가중치 기준으로 정렬하기
		Collections.sort(edges);
		
		// 선택된 간선의 cost의 합을 저장하는 변수
		sum = 0;
		cnt = 0;
		
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			// 순환이 아니라면 가중치 더해주기
			if(find(edge.s) == find(edge.e)) continue;
			union(edge.s, edge.e);
			cnt+= 1;
			sum += edge.w;
			
			if(cnt == V - 1) break;
		}
		System.out.println(sum);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			set[pa] = pb;
		}
	}

	private static int find(int a) {
		// TODO Auto-generated method stub
		return set[a] = set[a] == a ? a : find(set[a]);
	}
}
