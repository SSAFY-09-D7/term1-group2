package SWEA;
/*
 * 창용 마을 무리의 개수
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, M;
	static int[] set;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			set = new int[N + 1];
			cnt = 0;
			makeSet();
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			for (int i = 1; i < set.length; i++) {
				if(set[i] == i) cnt++;
			}
			sb.append("#" + test_case + " " + cnt + "\n");
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

	private static void makeSet() {
		for (int i = 1; i <= N; i++) {
			set[i] = i;
		}
	}
}
