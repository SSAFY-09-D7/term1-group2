

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS와BFS {

	static int n,m,v;
	static LinkedList<Integer>[] graph;
	static boolean[] visit;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		v= Integer.parseInt(st.nextToken());
		graph = new LinkedList[n+1];
		visit = new boolean[n+1];
		
		for (int i = 1; i < n+1; i++) {
			graph[i]= new LinkedList<Integer>();
		}
		for (int i = 1; i < m+1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 1; i < n+1; i++) {
			Collections.sort(graph[i]);
		}

		
		dfs(v);
		System.out.println();
		visit = new boolean[n+1];
		bfs(v);
		
		
		
	}
	private static void bfs(int x) {
		Queue<Integer> q = new LinkedList<Integer>();
		visit[x]=true;
		q.offer(x);
		
		while(!q.isEmpty()) {
			int p = q.poll();
			System.out.print(p+" ");
			Iterator<Integer> list = graph[p].listIterator();
			while(list.hasNext()) {
				int next = list.next();
				if(!visit[next]) {
					visit[next]=true;
					q.add(next);
				}
			}
			
		}
		
		
		
		
		
		
	}
	private static void dfs(int x) {
		
		visit[x]= true;
		
		System.out.print(x+" ");
		
		Iterator<Integer> list = graph[x].listIterator();
		
		while(list.hasNext()) {
			int next = list.next();
			if(!visit[next]) {
				dfs(next);
			}
		}
			
		
		
	}

}
