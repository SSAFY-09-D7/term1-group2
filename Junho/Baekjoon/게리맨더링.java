package ssafy.com.lecture.day0228.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 게리맨더링 {
	
	static int n,cnt;
	static boolean[] v;
	static boolean[] v2;
	static LinkedList<Integer>[] list;
	static int[] population;
	static StringTokenizer st;
	static int min_diff;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		population= new int[n];
		list = new LinkedList[n+1];
		v = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		min_diff=Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			population[i]=Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < n+1; i++) {
			list[i]= new LinkedList<Integer>();
		}
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int adj_n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adj_n; j++) {
				int x = Integer.parseInt(st.nextToken());
				list[i].add(x);
				list[x].add(i);
				
			}
		}
		//부분집합 구하기
		powerset(0);
		System.out.println(min_diff==Integer.MAX_VALUE?-1:min_diff);
	}
	
	private static void powerset(int idx) {
		//basis part
		if(idx==n) {
			cnt=0;
			//모두 True인 경우와 모두 False인 경우 제외
			if(allFalse()||allTrue()) return;
//			System.out.println(Arrays.toString(v));
			
			v2 = new boolean[n+1];
			for (int i = 0; i < v.length; i++) {
				if(v[i]&&!v2[i+1]) {
					dfs(i+1);
					cnt++;
				}	
			}
			
			v2 = new boolean[n+1];
			for (int i = 0; i < v.length; i++) {
				if(!v[i]&&!v2[i+1]) {
					dfs2(i+1);
					cnt++;
				}
				
			}
			int sum1=0;
			int sum2=0;
			//나눈 영역이 2일 경우
			if(cnt==2) {
				//인구수차이 최소값 구하기
				for (int i = 0; i < v.length; i++) {
					if(v[i]) sum1+=population[i];
					else sum2+=population[i];
				}
				min_diff=Math.min(min_diff, Math.abs(sum1-sum2));
			}

			return;
		}
		//inductive part
		v[idx] = true;
		powerset(idx + 1);
		v[idx] = false;
		powerset(idx + 1);
	}
	//False인 영역 탐색
	private static void dfs2(int idx) {
		v2[idx] = true;

		Iterator<Integer> iter = list[idx].listIterator();
		while (iter.hasNext()) {
			int next = iter.next();
			if (!v2[next] && !v[next - 1]) {
				dfs2(next);
			}
		}

	}
	//True인 영역 탐색
	private static void dfs(int idx) {
		v2[idx] = true;

		Iterator<Integer> iter = list[idx].listIterator();
		while(iter.hasNext()) {
			int next = iter.next();
			if(!v2[next]&&v[next-1]) {
				dfs(next);
			}
		}
		
	}
	private static boolean allTrue() {
		for (int i = 0; i < v.length; i++) {
			if(!v[i]) return false;
		}
		return true;		
	}
	private static boolean allFalse() {
		for (int i = 0; i < v.length; i++) {
			if(v[i]) return false;
		}
		return true;
	}
}
