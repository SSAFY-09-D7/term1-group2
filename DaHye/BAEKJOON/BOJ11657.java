package BAEKJOON;
/*
 * 타임머신
 * 한 도시에서 출발하여 다른 도시에 도착하는 버스 M개
 * C = 0: 순간 이동, C < 0: 타임머신
 */

import java.io.*;
import java.util.*;

public class BOJ11657 {
	// 벨만-포드: 에지 리스트 사용할 것
	static class Edge {
		int s, e, c;

		public Edge(int s, int e, int c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Edge [s=" + s + ", e=" + e + ", c=" + c + "]";
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static Edge edges[]; 
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
		
		edges = new Edge[M + 1];
		
		for (int i = 1; i < edges.length; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(A, B, C);
		}
		
		long d[] = new long[N + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		
		// 1번 도시에서 출발해 나머지도시로 가는 가장 빠른 시간
		d[1] = 0;
		
		// 에지 사용 개수
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < edges.length; j++) {
				Edge edge = edges[j];
				// 출발 노드가 방문한 적이 없는 노드(INF)라면 값을 없데이트하면 안됨!!
				if(d[edge.s] != Integer.MAX_VALUE && d[edge.e] > d[edge.s] + edge.c) {
					d[edge.e] = d[edge.s] + edge.c;
				}
			}
		}
		
		// 음수 사이클 확인
		boolean cycle = false;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < edges.length; j++) {
				Edge edge = edges[j];
				if(d[edge.s] != Integer.MAX_VALUE && d[edge.e] > d[edge.s] + edge.c) {
					cycle = true;
				}
			}
		}
		
		if(cycle) System.out.println(-1);
		else {
			for (int i = 2; i < d.length; i++) {
				if(d[i] == Integer.MAX_VALUE) sb.append("-1" + "\n");
				else sb.append(d[i] + "\n");
			}
		}
		System.out.println(sb);
	}
}
