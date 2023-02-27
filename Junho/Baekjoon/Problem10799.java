package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//쇠막대기
public class Problem10799 {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String data = br.readLine();
		
		Stack<Character> st = new Stack<>();
		
		int count=0;
		for(int i=0;i<data.length();i++) {
			
			if(data.charAt(i)=='(') {
				st.push('(');
				
			}else {
				st.pop();
				if(data.charAt(i-1)=='(') {
					count+=st.size();
				}
				else {
					count+=1;
				}
			}
		}
		System.out.println(count);
	}

}
