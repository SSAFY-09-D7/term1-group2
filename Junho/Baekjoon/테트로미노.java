package ssafy.com.알고리즘.Backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int n,m;
	static int[][] map;
	static StringTokenizer st;
	static int max_val=0;
	static boolean[][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		map = new int[n][m];
		v = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				v[i][j]=true;
				recur(i,j,0,map[i][j]);
				v[i][j]=false;
			}
		}
		System.out.println(max_val);
		
	}
	
	private static void recur(int x, int y, int idx,int sum) {
		if(idx==3) {
			max_val=Math.max(max_val, sum);
//			print(v);
//			System.out.println(sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(0<=nx&&nx<n&&0<=ny&&ny<m&&!v[nx][ny]) {
				
				if(idx==1) {
//					System.out.println("gkgkgk");
					v[nx][ny]=true;
					recur(x, y, idx+1, sum+map[nx][ny]);
					v[nx][ny]=false;
				}
				
				v[nx][ny]=true;
				recur(nx, ny, idx+1, sum+map[nx][ny]);
				v[nx][ny]=false;
				
			}
		}
	}

	private static void print(boolean[][] v2) {
		for (int i = 0; i < v2.length; i++) {
			for (int j = 0; j < v2[i].length; j++) {
				System.out.print(v2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
		
	}
}
