package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//차이를 최대로
public class Problem10819 {
	static int[] arr;
	static int[] result;
	static StringTokenizer st;
	static int n, max;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		arr = new int[n];
		result = new int[n];
		visited= new boolean[n];
		st = new StringTokenizer(br.readLine());
		
		//배열 값 넣기
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}

		dfs(0);
		System.out.println(max);
		
	}
	private static void dfs(int depth) {
		
		//깊이가 n이면 최댓값 계산하여 return
		if(depth==n) {
			int sum=0;
			for(int i=0;i<n-1;i++) {
				sum+=Math.abs(result[i]-result[i+1]);
			}
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				result[depth]=arr[i];
				dfs(depth+1);
				visited[i]=false;
			}
		}
		
	}

}
