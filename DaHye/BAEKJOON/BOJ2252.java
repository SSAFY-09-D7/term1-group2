package BAEKJOON;

import java.io.*;
import java.util.*;

/*
 * 줄 세우기
 * 위상정렬
 * 1. 진입 차수가 0인 노드를 큐에 저장함
 * 2. 큐에서 데이터를 poll해 해당 노드를 탐샏ㄱ 결과에 추가하고, 해당 노드가 가리키는 노드의 진입차수를 1씩 감소
 * 3. 감소했을 때 진입차수가 0이 되는 노드를 큐에 offer
 * 4. 큐가 빌 때까지 반복!
 */
public class BOJ2252 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, M; // N: 학생 수(노드), M: 키 비교 회수(간선)
	static ArrayList<Integer>[] adj; // ArrayList
	static int D[]; // 진입차수 배열
	static boolean v[];
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		D = new int[N + 1];
		v = new boolean[N + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
		}
			
		for (ArrayList<Integer> nodes : adj) {
			for (Integer node : nodes) {
				D[node]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < D.length; i++) {
			if(D[i] == 0) {
				queue.add(i);
				v[i] = true;
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current + " ");
			
			for (Integer node : adj[current]) {
				D[node]--;
			}
			
			for(int i = 1; i < D.length; i++) {
				if(!v[i] && D[i] == 0) {
					queue.add(i);
					v[i] = true;
				}
			}
		}
		System.out.println(sb);
	}
}
