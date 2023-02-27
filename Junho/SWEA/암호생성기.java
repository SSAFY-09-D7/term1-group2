

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기 {

	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t =0;t<10;t++) {
			
		
		
		int tc = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<8;i++) {
			q.offer(Integer.parseInt(st.nextToken()));
		}
		int idx=1;
		L:
		while (true) {
			
			for(int i=0;i<q.size();i++) {
					if(idx==6) {
						idx=1;
					}
					
					int start = q.poll();
					start -= idx;
					if(start<=0) {
						q.offer(0);
						break L;
					
					}
					q.offer(start);
					
					idx++;
							
			}
		}
		
		System.out.print("#"+tc+" ");
		while(!q.isEmpty()) {
			System.out.print(q.poll()+" ");

		}
		System.out.println();
	}
	}
}
