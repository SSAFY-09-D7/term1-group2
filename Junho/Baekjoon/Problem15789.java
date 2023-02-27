package 알고리즘스터디.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//CTP 왕국은 한솔 왕국을 이길 수 있을까

/* 조건
 * A->B B->C A->C
 * CTP 왕국은 한솔 왕국과 동맹인 왕국과 동맹x
 * k번의 동맹 맺을 기회를 모두 사용X
 * 출력 : CTP 왕국의 힘의 최댓값
 * */

public class Problem15789 {

	static StringTokenizer st;
	static LinkedList<Integer>[] graph;
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean[] visited;
	static Iterator<Integer> it;
	static ArrayList<Integer> max_arr;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		graph = new LinkedList[n+1];
		max_arr = new ArrayList<Integer>();
		int result=1;
		for(int i=0;i<=n;i++) {
			graph[i]= new LinkedList<Integer>();
		}
		visited = new boolean[n+1];
		for(int t=0;t<m;t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
			}
		
		st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		//CTP와 모든 동맹국들 true
		//한솔와 모든 동맹국들 true
		
		visited[h]=true;
		visited[c]=true;
		it = graph[h].listIterator();
		
		while(it.hasNext()) {
			visited[it.next()]=true;
		}
		it = graph[c].listIterator();
		while(it.hasNext()) {
			result+=1;
			visited[it.next()]=true;
		}
		//System.out.println(result);
		for(int i=1;i<visited.length;i++) {
			int max=1;
			if(visited[i]==false) {
				//System.out.println(i);
				bfs(i,max);
			}
		}
		//max_arr 오름차순 정렬
		Collections.sort(max_arr,Collections.reverseOrder());
		
		//k 조건 고려
		if(k>max_arr.size()) {
			for(int i=0;i<max_arr.size();i++) {
				result+=max_arr.get(i);
			}
		}else {
			for(int i=0;i<k;i++) {
				result+=max_arr.get(i);
			}
		}
		System.out.println(result);
	}
	
	private static void bfs(int s,int max) {
		
		visited[s]=true;
		
		q.add(s);
		//큐가 빌때까지
		while(q.size()!=0) {
			s = q.poll();
			
			it = graph[s].listIterator();
			while(it.hasNext()) {
				int next = it.next();
				if(!visited[next]) {
					max+=1;
					visited[next]=true;
					q.add(next);
				}
			}
		}
		max_arr.add(max);
		
		
		
	}

}
