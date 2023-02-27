package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//오리
public class Problem12933 {

	static char[] duck_sound= {'q','u','a','c','k'};
	static String duck;
	static int[] check;
	static int duck_n;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		duck = br.readLine();

		check = new int[duck.length()];
		
		//duck문자열이 5의 배수가 아니면 -1
		if(duck.length()%5!=0) {
			System.out.println("-1");
			System.exit(0);
		}
		duck_n=0;
		
		for (int i = 0; i < duck.length(); i++) {
			if(duck.charAt(i)=='q'&&check[i]==0) {
				search_duck(i);
			}
		}
		//duck 개수가 0
		if(duck_n==0 || !check_duck()) {
			System.out.println("-1");
		}
		else System.out.println(duck_n);

	}
	
	//check배열 모든 값이 0이 아니면 false
	private static boolean check_duck() {
		for(int i=0;i<check.length;i++) {
			if(check[i]==0) {
				return false;
			}
		}
		return true;
	}
	//일일히 문자 하나씩  'quack' 순서에 맞게 비교
	private static void search_duck(int s) {
		int j=0;
		boolean flag =true;
		
		for(int i=s;i<duck.length();i++) {
			if(duck.charAt(i)==duck_sound[j]&&check[i]==0) {
				check[i]=1;
				if(duck.charAt(i)=='k') {
					if(flag) {
						duck_n+=1;
						flag=false;
					}
					j=0;
				}
				else j+=1;
			}
		}
	}
}
