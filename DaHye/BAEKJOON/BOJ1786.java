package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 찾기
 */
public class BOJ1786 {
	static String P, T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		P = br.readLine();
		T = br.readLine();
		
		int count = 0;
		
		// 부분일치 테이블 배열 만들기
		int table[] = new int[T.length()];

		int j = 0;
		for (int i = 1; i < T.length(); i++) {
			while(j > 0 && T.charAt(i) != T.charAt(j)) {
				j = table[j - 1];
			}
			
			if(T.charAt(i) == T.charAt(j)) {
				table[i] = ++j;
			}
		}
		
		j = 0;
		
		for(int i = 0; i < P.length(); i++) {
			while(j > 0 && P.charAt(i) != T.charAt(j)) {
				j = table[j - 1];
			}
			if(P.charAt(i) == T.charAt(j)) {
				if(j == T.length() - 1) {
					count++;
					
					sb.append((i - T.length() + 2) + " ");
					j = table[j];
				} else {
					j++;
				}
			}
		}
		
		System.out.println(count + "\n" + sb);
	}
}
