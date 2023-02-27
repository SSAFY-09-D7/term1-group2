package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사나운 개
public class problem2991 {

	static StringTokenizer st ;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] deliver = new int[3];
		for (int i = 0; i < deliver.length; i++) {
			deliver[i]=Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < deliver.length; i++) {
			int count=0;
			int dog1 = deliver[i]%(a+b); 
			int dog2 = deliver[i]%(c+d);
			if(0<dog1&&dog1<=a) count+=1;
			if(0<dog2&&dog2<=c) count+=1;
			System.out.println(count);
			
		}
		
	}
}
