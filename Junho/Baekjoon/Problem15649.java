package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//N과 M(1)
public class Problem15649 {

	static StringBuilder sb= new StringBuilder();
	static int[] data;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		data = new int[m];
		visited = new boolean[n+1];
		dfs(n,m,0);
		System.out.println(sb);
	}

	public static void dfs(int n, int m, int d) {
		
		if(d==m) {
			for(int i=0; i<data.length;i++) {
				sb.append(data[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1;i<=n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				data[d]=i;
				dfs(n,m,d+1);
				visited[i]=false;
			}
		}
	}
}
