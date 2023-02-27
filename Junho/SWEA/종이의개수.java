

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이의개수 {
	static int n;
	static int[][] paper;
	static StringTokenizer st;
	static int one=0;
	static int minus_one=0;
	static int zero=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		paper = new int [n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				paper[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0,0,n);
		System.out.println(minus_one);
		System.out.println(zero);
		System.out.println(one);
	}
	private static void cut(int r, int c, int size) {
		
		int tmp_val = paper[r][c];
		int check=0;
		loop:
		for(int i=r,nr=r+size;i<nr;i++) {
			for(int j=c,nc=c+size;j<nc;j++) {
				if (tmp_val!=paper[i][j]) {
					check=-1;
					break loop;
				}
				
			}
		}
		if(check==0&&tmp_val==1) {
			one++;
		}
		else if (check==0&&tmp_val==0) {
			zero++;
		}
		else if(check==0&&tmp_val==-1) {
			minus_one++;
		}
		else {
			size/=3;
//			System.out.println(size);
			cut(r, c, size);
			cut(r, c+size, size);
			cut(r, c+(size*2), size);
			cut(r+size, c, size);
			cut(r+size, c+size, size);
			cut(r+size, c+(size*2), size);
			cut(r+(size*2), c, size);
			cut(r+(size*2), c+size, size);
			cut(r+(size*2), c+(size*2), size);
			
		}
	}
}
