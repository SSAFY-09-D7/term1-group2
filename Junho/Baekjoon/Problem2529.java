

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//부등호
/*
 * int로 할시 numberformatexception터짐 -> long으로 대체
 * 
 * 순열로 경우의 수를 뽑은 다음 부등호 처리를 한다
 * 
 * */
public class Problem2529 {

	static int k;
	static int[] sel;
	static int[] arr;
	static char[] sign;
	static boolean[] v;
	static long min_val = Long.MAX_VALUE; 
	static long max_val = Long.MIN_VALUE;
	static String min;
	static String max;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		sign = new char[k];
		arr = new int[k+1];
		sel = new int[k+1];
		v = new boolean[10];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<k;i++) {
			sign[i]=st.nextToken().charAt(0);
		}
		
		recur(0);
		System.out.println(max);
		System.out.println(min);
	}
	private static void recur(int idx) {
		
		//basis part
		if(idx==sel.length) {
			for(int i=0;i<sel.length-1;i++) {
				if(!compare(sel[i],sel[i+1],sign[i])) {
					return;
				}
				
			}
			String str ="";
			for(int i=0;i<sel.length;i++) {
				str+=sel[i];
			}
			if(max_val<Long.parseLong(str)) {
				max_val=Long.parseLong(str);
				max=str;
			}
			if(min_val>Long.parseLong(str)) {
				min_val=Long.parseLong(str);
				min=str;
			}
			
			
			return;
		}
		
		//inductive part
		for(int i=0;i<10;i++) {
			if(!v[i]) {
				v[i]=true;
				sel[idx]=i;
				recur(idx+1);
				v[i]=false;
			}
		}
		
	}
	
	private static boolean compare(int a,int b,char c) {
		
		switch(c) {
			case '<':
				if(a<b) return true;
				else return false;
				
			case '>':
				if(a>b) return true;
				else return false;
				
		}
		
		
		
		return false;
	}
}
