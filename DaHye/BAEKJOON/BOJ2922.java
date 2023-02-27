package BAEKJOON;

import java.util.Scanner;

/*
 * 즐거운 단어
 */
public class BOJ2922 {
	static int count;
	static String input;
	static char[] str;
	static int[] idx;
	static long total;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		str = input.toCharArray();
		
		for(int i = 0; i < input.length(); i++) {
			if(str[i] == '_') count++;
		}
		
		idx = new int[count];
		
		int tmp = 0;
		for (int i = 0; i < input.length(); i++) {
			if(str[i] == '_') {
				idx[tmp] = i;
				tmp++;
			}
		}
		
		func(0, 1);
		System.out.println(total);
	}

	private static void func(int k, long c) {
		if(k == count) {
			if(check(str)) {
				total += c;
			}
			return;
		}
		
		int numIdx = idx[k];
		str[numIdx] = 'A';
		
		func(k + 1, c * 5);
		
		str[numIdx] = 'L';
		func(k + 1, c);
		
		str[numIdx] = 'K';
		func(k + 1, c * 20);
	}
	
	// 모음은 연속해서 3번, 자음은 연속해서 3번 나오면 안됨 + L은 반드시 한 번 포함 해야됨
	private static boolean check(char[] str) {
		int countL = 0;
		int countVo = 0; // countVo: 모음
		int countCo = 0; // countCo: 자음
		
		for (int i = 0; i < str.length; i++) {
			if(str[i] == 'L') countL++;
		}
		
		// str 길이가 3 이하일 경우
		if(countL == 0) return false;
		
		// 연속한 자음이 몇 개?
		if(str.length >= 3) {
			for(int i = 0; i <= str.length - 3; i++) {
				countVo = 0;
				countCo = 0;
				
				for(int j = 0; j < 3; j++) {
					if(str[i + j] == 'A' || str[i + j] == 'E' || str[i + j] == 'I' || str[i + j] == 'O' || str[i + j] == 'U') countVo++;
					else countCo++;
				}
				if(countVo >= 3 || countCo >= 3) return false;
			}
		}
		return true;
	}
}
