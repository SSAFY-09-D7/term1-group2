package 알고리즘스터디.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//카드 2
public class Problem2164 {

	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		//큐에 1~n까지 넣기
		for(int i=1;i<=n;i++) {
			q.add(i);
		}
//		System.out.println(q.toString());
		while(q.size()!=1) {
			q.poll();
			int next_n = q.poll();
			q.add(next_n);
		}
		
		System.out.println(q.peek());
		
	}

}
