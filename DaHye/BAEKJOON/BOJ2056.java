package BAEKJOON;
/*
 * 작업
 */

import java.io.*;
import java.util.*;

public class BOJ2056 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, d[], time[], result[];
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		time = new int[N + 1];
		d = new int[N + 1];
		result = new int[N + 1];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int tmp = Integer.parseInt(st.nextToken());
			time[i] = tmp;
			
			int n = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				adj[a].add(i);
				d[i]++;
			}
		}
		
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i < d.length; i++) {
			result[i] = time[i];
			if(d[i] == 0) queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for (Integer next : adj[current]) {
				result[next] = Math.max(result[next], result[current] + time[next]);
				
				d[next]--;
				if(d[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		System.out.println(result[result.length - 1]);
	}
}
