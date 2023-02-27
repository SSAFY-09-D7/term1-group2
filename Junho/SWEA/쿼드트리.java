

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쿼드트리 {

	static int n;
	static int[][] player;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		player= new int[n][n];
		for(int i=0;i<n;i++) {
			String data = br.readLine();
			for(int j=0;j<n;j++) {
				player[i][j]=data.charAt(j)-'0';
			}
		}
		
		
		cut(0,0,n);
		System.out.println(sb.toString());
		
	}

	private static void cut(int r, int c, int size) {
		int sum=0;
		for (int i = r,rEnd = r+size; i < rEnd; i++) {
			for(int j=c,cEnd=c+size;j<cEnd;j++) {
				sum+= player[i][j];
			}
		}
		
		
		if(sum==size*size) {
			
			sb.append(1);
		}
		else if(sum==0) {
			sb.append(0);
		}
		else {
			sb.append('(');
			size/=2;
			cut(r, c, size);
			cut(r, c+size, size);
			cut(r+size, c, size);
			cut(r+size, c+size, size);
			sb.append(')');
			
		}
	}
}
