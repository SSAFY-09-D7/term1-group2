package ssafy.com.lecture.day0227.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서로소집합 {
	static int n,m;
	static StringTokenizer st;
	static int[] parents;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n= Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parents = new int[n+1];
			
			//makeSet
			for (int i = 1; i < n+1; i++) {
				parents[i]=i;
			}
			System.out.print("#"+tc+" ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a= Integer.parseInt(st.nextToken());
				int b= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				
				if(a==0) {
					union(b,c);
				}else {
					if(find(b)==find(c)) {
						System.out.print(1);
					}else {
						System.out.print(0);
					}
				}
			}
			System.out.println();
			
		}
	}
	//Union 만들기
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa!=pb) {
			parents[pa]=parents[pb];
		}
	}
	//대표자 찾기
	private static int find(int x) {
		if(parents[x]==x) return x;
		//path compression
		else {
			return parents[x]=find(parents[x]);
		}
	}
}
