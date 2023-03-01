package SWEA;
/*
 * 최소 스패닝 트리
 * 입력
 * - T: 테스트 케이스 개수
 * - V: 정점의 개수, E: 간선의 개수
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA3124 {
	static class Edge implements Comparable<Edge>{
		int s, e, w;

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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, V, E;
	static int[] set;
	static ArrayList<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new ArrayList<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				edges.add(new Edge(A, B, C));
			}
		
			// 정점: 1부터 시작
			set = new int[V + 1];
			for (int i = 1; i < set.length; i++) {
				set[i] = i;
			}
		
			Collections.sort(edges);
			
			long sum = 0, cnt = 0;
			
			for(int i = 0; i < edges.size(); i++) {
				Edge edge = edges.get(i);
				
				if(find(edge.s) != find(edge.e)) {
					union(edge.s, edge.e);
					cnt++;
					sum += edge.w;
					
					if(cnt == V - 1) break;
				}
			}
			sb.append("#" + test_case + " " + sum + "\n");
		}
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) {
			set[pa] = pb;
		}
	}

	private static int find(int a) {
		return set[a] = set[a] == a ? a : find(set[a]);
	}
}
