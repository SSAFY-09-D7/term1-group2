package ssafy.com.lecture.day0227.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 창용마을무리의개수_서로소 {

	static int n,m,cnt;
	static int[] parents;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			n= Integer.parseInt(st.nextToken());
			m= Integer.parseInt(st.nextToken());
			parents = new int[n+1];
			cnt=0;
			//make set
			for (int i = 1; i < n+1; i++) {
				parents[i]=i;
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			
			for (int i = 1; i < parents.length; i++) {
				if(parents[i]==i) cnt++;
			}
			System.out.println("#"+tc+" "+cnt);
			
		}
	}
	//Union 만들기
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa!=pb) {
			if(pa>pb) {
				parents[pa]=parents[pb];
			}else {
				parents[pb]=parents[pa];
				
			}
		}
		
	}
	//대표자 찾기
	private static int find(int x) {
		if(parents[x]==x) return x;
		else {
			return find(parents[x]);
		}
		
	}
}
