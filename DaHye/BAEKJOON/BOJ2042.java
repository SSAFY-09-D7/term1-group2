package BAEKJOON;
/*
 * N: 수의 개수, M: 수의 변경이 일어나는 횟수, K: 구간합을 구하는 횟수
 * 펜윅트리 사용
 */
import java.io.*;
import java.util.*;

public class BOJ2042 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, K;
	// arr: 숫자가 담겨있는 배열, idx: 각 범위에 있는 구간합을 저장
	static long arr[];
	static long idx[]; 
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int count = 1;
		
		arr = new long[N + 1];
		idx = new long[N + 1];
		
		// 초기 데이터 삽입해주기
		// 처음 초기값은 변화량 자체가 초기갑임!!
		for (int i = 1; i < arr.length; i++) {
			update(i, Long.parseLong(br.readLine()));
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());

			// update
			if(a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				update(b, c);
			}
				
			// 구간합 구하기
			if(a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				sb.append((getSum(c)- (getSum(b - 1))) + "\n");
				count++;
			}
			if(count > K) break;
		}
		System.out.println(sb);
	}
	
	// 값 변경 및 초기 데이터 삽입
	// bit[idx]에 각 어떠한 범위의 구간합이 들어가는지 지정했음
	private static void update(int b, long val) {
		long diff = val - arr[b];
		arr[b] = val;
		
		while(b < N + 1) {
			idx[b] += diff;
			b += b & -b;
		}
	}

	private static long getSum(int c) {
		long answer = 0l;
		while(c > 0) {
			answer += idx[c];
			c = c & (c - 1);
		} return answer;
	}
}