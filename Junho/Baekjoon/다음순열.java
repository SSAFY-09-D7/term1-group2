

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 다음순열 {

	static int n;
	static int[] data;
	static StringTokenizer st;
	static int result;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		data = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			data[i]=Integer.parseInt(st.nextToken());
		}
		result=0;
		nextPer();
		
		if(result==-1) System.out.println(-1);
		else {
			for(int i=0;i<data.length;i++) {
				System.out.print(data[i]+" ");
			}
		}
		
	}
	private static void nextPer() {
		int idx = n-1;
		while(idx>0&&data[idx-1]>data[idx]) {
			idx--;
		}
		if(idx==0) {
			result=-1;
			return;
		}
		int b_idx = n-1;
		while(b_idx>idx&&data[b_idx]<data[idx-1]) {
			b_idx--;
		}
		
		int temp = data[idx-1];
		data[idx-1]=data[b_idx];
		data[b_idx]=temp;
		
		Arrays.sort(data,idx,n);;
	}
}
