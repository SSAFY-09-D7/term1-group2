package BAEKJOON;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 즐거운 단어
 */
public class BOJ2922 {
	static char vo[] = {'a', 'e', 'i', 'o', 'u'};
	static int count, countVo, countCo;
	static String input;
	static char[] str;
	static int[] idx;
	static int total;
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
		
		func(0);
		System.out.println(total);
	}

	private static void func(int k) {
		if(k == count) {
			total++;
			System.out.println(Arrays.toString(str) + countCo + " " + countVo);
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			int numIdx = idx[k];
			
			str[numIdx] = (char) (i + 65);
//			if(!check(str, numIdx)) continue;
			func(k + 1);
		}
	}
	
	// 연속하는 모음의 개수를 찾는 함수
	private static boolean check(char[] str, int idx) {
		int countL = 0;
		if(idx < 3) return true;
		else {
			for(int i = 0; i < idx; i++) {
				if(str[i] == 'L') countL++;
			}
			for(int i = 0; i < idx - 3; i++) {
				countVo = 0;
				countCo = 0;
				for(int j = 0; j < 3; j++) {
					if(str[i + j] == 'a' || str[i + j] == 'e' || str[i + j] == 'i' || str[i + j] == 'o' || str[i + j] =='u') countVo++;
					else countCo++;
				}
				if(countVo == 3) return false;
				if(countCo == 3) return false;
			}
			
			if(countL == 0) return false;
		}

		return true;
	}
}
