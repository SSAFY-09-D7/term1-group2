

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class 숫자만들기 {

	static int n,operator_sum,min,max;
	static int[] operator_n;
	static ArrayList<Character> operator;
	static int[] num;
	static int[] sel;
	static StringTokenizer st;
	static Set<String> set;
	
	public static void main(String[] args) throws Exception, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			set = new HashSet<String>();
			operator_n = new int[4];
			num = new int[n];
			st = new StringTokenizer(br.readLine());
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for(int i=0;i<4;i++) {
				operator_n[i]=Integer.parseInt(st.nextToken());
				operator_sum+=operator_n[i];
				
			}
			sel = new int[n-1];
			st = new StringTokenizer(br.readLine());			
			for(int i=0;i<n;i++) {
				num[i]=Integer.parseInt(st.nextToken());
			}	
			recur(0);
			Iterator<String> it = set.iterator();
			
			
			
			int anw = max-min;
			System.out.println("#"+tc+" "+anw);
		}
	}


	private static void recur(int idx) {
		if(idx==sel.length) {

			String str ="";
			for(int i=0;i<sel.length;i++) {
				str+=Integer.toString(sel[i]);
			}
			cal(str);
			
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(operator_n[i]>0) {
				operator_n[i]--;
				sel[idx]=i;
				recur(idx+1);
				operator_n[i]++;
			}
		}
	}


	private static void cal(String str) {
		int result=num[0];
		String next = str;
		for(int i=1;i<n;i++) {
			if(next.charAt(i-1)=='0') {
				result+=num[i];
			}
			else if(next.charAt(i-1)=='2') {
				result*=num[i];
			}
			else if(next.charAt(i-1)=='3') {
				result/=num[i];
			}
			else {
				result-=num[i];
			}
			
		}
		max = Math.max(result, max);
		min = Math.min(result, min);
	}

	
}
