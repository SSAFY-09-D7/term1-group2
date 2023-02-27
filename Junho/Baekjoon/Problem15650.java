package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//N과 M(2)
public class Problem15650 {

	static StringBuilder sb= new StringBuilder();
	static int[] data;
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		data = new int[m];
		
		dfs(1,0);
		System.out.println(sb);
	}

	public static void dfs(int s, int d) {
		
		if(d==m) {
			for(int i=0; i<data.length;i++) {
				sb.append(data[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=s;i<=n;i++) {
			data[d]=i;
			dfs(i+1,d+1);
		}
	}

	
}
