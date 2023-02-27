package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 서로소 집합
 * 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
 * 
 * 입력
 * - T: 테스트 케이스
 * - n: 
 * - m: 입력으로 주어지는 연산의 개수
 * - 0 a b: 합집합
 * - 1 a b: 두 원소가 같은 집합에 포함되는지 확인
 * 
 * 출력
 * - 1로 시작하는 입력에 대해서 같은 집합에 속해있으면 1, 아니면 0
 */
public class SWEA3289 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;
	static int n, m, parents[];
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int test_case = 1; test_case < T + 1; test_case++) {
			String result = "";

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			parents = new int[n + 1];
			
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int cal = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cal == 0) {
					union(a, b);
				} else if(cal == 1) {
					if(find(a) == find(b)) result += "1";
					else result += "0";
				}
			}
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) {
			parents[pa] = pb;
		}
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
}
