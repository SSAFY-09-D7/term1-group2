import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18352 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K, X;
	static ArrayList<Integer>[] adjList;
	static int v[];
	static ArrayList<Integer> answer;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N + 1];

		for(int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
		}

		v = new int[N + 1];
		for (int i = 0; i < v.length; i++) {
			v[i] = -1;
		}
		
		bfs(X);

		answer = new ArrayList<>();
		
		for(int i = 0; i < v.length; i++) {
			if(v[i] == K) {
				answer.add(i);
				System.out.println(i);
			}
		}
		
		if(answer.isEmpty()) {
			System.out.println(-1);
		} else {
			Collections.sort(answer);
			for (String string : args) {
				System.out.println(string);
			}
		}
	}

	private static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(node);
		v[node]++;
		
		while(!queue.isEmpty()) {
			int now_node = queue.poll();
			for (Integer i: adjList[now_node]) {
				if(v[i] == -1) {
					v[i] = v[now_node]+ 1;
					queue.add(i);
				}
			}
		}
	}
}
