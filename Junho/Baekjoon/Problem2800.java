

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

//괄호 제거
/*
 * 괄호의 시작 끝 지점을 arraylist에 저장
 * 모든 부분집합 경우의 수를 뽑는다
 * treeset으로 중복을 없앤다
 * 
 * 
 * */
public class Problem2800 {

	static boolean[] sel;
	static Stack<Integer> stack;
	static ArrayList<괄호> arr;
	static ArrayList<String> arr2;
	static TreeSet<String> set;
	static String data;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		data = br.readLine();

		arr = new ArrayList<괄호>();
		arr2 = new ArrayList<String>();
		stack = new Stack<Integer>();
		set = new TreeSet<String>();
		for (int i = 0; i < data.length(); i++) {
			if(data.charAt(i)=='(') stack.push(i);
			if(data.charAt(i)==')') {
				arr.add(new 괄호(stack.pop(), i));
			}
		}
		
		sel = new boolean[arr.size()];
		recur(0);
		
		Iterator it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	private static void recur(int idx) {
		//basis part
		if (idx == sel.length) {
			if (allFalse())
				return;
//			System.out.println(Arrays.toString(sel));
			
			v= new boolean[data.length()];
			for(int i=0;i<sel.length;i++) {
				if(sel[i]) {
					v[arr.get(i).idx_s]=true;
					v[arr.get(i).idx_e]=true;
				}
			}
			String str="";
			for (int i = 0; i < v.length; i++) {
				if(!v[i]) str+=String.valueOf(data.charAt(i));
			}
			set.add(str);
			return;
		}
		//inductive part
		sel[idx] = true;
		recur(idx + 1);
		sel[idx] = false;
		recur(idx + 1);

	}


	private static boolean allFalse() {
		for(int i=0;i<sel.length;i++) {
			if(sel[i]) return false;
		}
		return true;
	}
}

class 괄호{
	int idx_s;
	int idx_e;
	public 괄호(int idx_s, int idx_e) {
		super();
		this.idx_s = idx_s;
		this.idx_e = idx_e;
	}
	
	
	
}

