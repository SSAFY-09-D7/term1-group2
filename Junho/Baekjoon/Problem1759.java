package ssafy.com.알고리즘.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//��ȣ �����
/*
 * �������� ����� �� �̱�
 * */
public class Problem1759 {

	static int l,c;
	static char[] code;
	static char[] sel;
	static StringTokenizer st;
	static char[] alp = {'a','e','i','o','u'};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		code = new char[c];
		sel = new char[l];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<c;i++) {
			code[i]=st.nextToken().charAt(0);
		}		
		Arrays.sort(code);
		
		
		recur(0,0);
	}
	private static void recur(int s, int idx) {
		
		//basis part
		if(idx ==sel.length ) {
//			System.out.println(Arrays.toString(sel));
			
			String str="";
			for(int i=0;i<sel.length;i++) {
				str+=sel[i];
			}
			if(!check(str)) return;
			for(int i=0;i<sel.length;i++) {
				System.out.print(sel[i]);
			}
			System.out.println();
			return;
		}
		
		//inductive part
		for(int i=s;i<c;i++) {
			sel[idx]=code[i];
			recur(i+1,idx+1);
		}
		
		
	}
	private static boolean check(String str) {
		int con=0;
		//���� ���� ���� �̱�
		for(int i=0;i<str.length();i++) {
			for(int j=0;j<alp.length;j++) {
				if(str.charAt(i)==alp[j]) {
					con++;
					break;
				}	
			} 
		}
		int gather=l-con;
		
		if(con<1||gather<2) return false;
		return true;
	}
}
