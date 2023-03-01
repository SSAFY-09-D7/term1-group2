package SWEA;
/*
 * 최소 스패닝 트리 by PRIM
 * 1부터 시작할 때 주의하기!
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3124_PRIM {
	static class Vertex{
		int e, w;

		public Vertex(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, V, E, A, B, C;
	static int dist[]; // 정점배열
	static boolean v[];
	static ArrayList<Vertex>[] adj;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V + 1];

			for (int i = 0; i < adj.length; i++) {
				adj[i] = new ArrayList<>();
			}
			for (int i = 0; i < E; i++) {
				st= new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				adj[A].add(new Vertex(B, C));
				adj[B].add(new Vertex(A, C));
			}
			
			// 정점배열
			dist = new int[V + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			// 방문배열
			v = new boolean[V + 1];
			
			// 임의의 정점
			dist[1] = 0;
			
			// 선택된 정점과 인접한 정점들의 값을 찾아서 정점 배열에 업데이트
			for (int cnt = 0; cnt < V; cnt++) {
				int minIdx = -1;
				int minDist = Integer.MAX_VALUE;
				
				for(int i = 1; i <= V; i++) {
					if(!v[i] && minDist > dist[i]) {
						minIdx = i;
						minDist = dist[i];
					}
				}

				v[minIdx] = true;
				
				for (Vertex next : adj[minIdx]) {
					if(!v[next.e] && next.w < dist[next.e]) {
						dist[next.e] = next.w;
					}
				}
			}
//			System.out.println(Arrays.toString(dist));
			int sum = 0;
			for (int i = 1; i < dist.length; i++) {
				sum += dist[i];
			}
			sb.append("#" + test_case + " " + sum + "\n");
		}
		System.out.println(sb);
	}
}
