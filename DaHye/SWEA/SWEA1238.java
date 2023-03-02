package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Contact
 * 비상 연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 가장 나중에 연락을 받게 되는 사람 중 번호가 갖아 큰 사람 구하기
 * 화살표는 연락이 가능한 방향
 * 입력
 * - 데이터의 길이와 시작점
 */
public class SWEA1238 {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int V, E, level;
	static ArrayList<Integer>[] adj;
	static int[] dist;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("DaHye/Input/SWEA1238.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case < 11; test_case++) {
			st = new StringTokenizer(br.readLine());
			// N: 받는 데이터의 길이
			// S: 시작점
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			int arr[] = new int[N];
			adj = new ArrayList[101];
			dist = new int[101];
			v = new boolean[101];
			for (int i = 0; i < adj.length; i++) {
				adj[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N / 2; i++) {
				int a = arr[i * 2];
				int b = arr[i * 2 + 1];
				
				adj[a].add(b);
			}
			
			Queue<int[]> queue = new LinkedList<>();
			
			queue.add(new int[] {S, 0});
			v[S] = true;
			
			while(!queue.isEmpty()) {
				int[] nodeDis = queue.poll();
				dist[nodeDis[0]] = nodeDis[1];
				level = nodeDis[1];
				
				for(int i = 0; i < adj[nodeDis[0]].size(); i++) {
					if(!v[(int) adj[nodeDis[0]].get(i)]) {
						queue.add(new int[] {(Integer) adj[nodeDis[0]].get(i), nodeDis[1] + 1});
						v[(int) adj[nodeDis[0]].get(i)] = true;
					}
				}
			}
			
			int result = 0;
			for (int i = 0; i < dist.length; i++) {
				if(dist[i] == level) result = i;
			}

			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
