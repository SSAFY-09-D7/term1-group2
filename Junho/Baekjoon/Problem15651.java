package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 //N과 M(3)
public class Problem15651 {
	
	public static int[] data;
	public static int n, m;
	public static StringBuilder sb = new StringBuilder();
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
 
		data = new int[m];
		dfs(0);
		System.out.println(sb);
	}
 
	public static void dfs(int depth) {
 
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				sb.append(data[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
 
		for (int i = 1; i <= n; i++) {
			data[depth] = i;
			dfs(depth + 1);
		}
	}
 
}