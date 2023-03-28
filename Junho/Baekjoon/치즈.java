package ssafy.com.알고리즘.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {
	static class Point{
		int x,y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static ArrayList<Point> cheese;
	static int n,m;
	static int[][] map;
	static StringTokenizer st;
	static boolean[][] v1;
	static boolean[][] v2;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		st=  new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		cheese = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) cheese.add(new Point(i, j));
			}
		}
		
		
		int hour=0;
		while(true) {
			if(allZero()) {
				break;
			}
			
			
			v1 = new boolean[n][m];
			v2 = new boolean[n][m];
			bfs(0,0);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[i][j]==1) {
						if(checkOuter(i,j)) {
							v2[i][j]=true;
						}
						
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(v2[i][j]) {
						map[i][j]=0;
					}
				}
			}
			
			
//			print(v1);
			hour++;
			
		}
		System.out.println(hour);
		
	}
	private static void bfs(int x, int y) {
		// TODO Auto-generated method stub
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		v1[x][y]=true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx =p.x+dx[d];
				int ny =p.y+dy[d];
				if(0<=nx&&0<=ny&&nx<n&&ny<m&&!v1[nx][ny]&&map[nx][ny]==0) {
					v1[nx][ny]=true;
					q.add(new Point(nx, ny));
				}
			}
			
		}
		
		
	}
	private static boolean allZero() {
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]!=0) {
					return false;
				}
			}
		}
		return true;
	}
	private static boolean checkOuter(int x, int y) {
		int cnt=0;
		for (int d = 0; d < 4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(map[nx][ny]==0&&v1[nx][ny]) {
				cnt++;
			}
		}
		if(cnt>=2) {
			return true;
		}
		
		
		return false;
	}
	private static void print(boolean[][] v) {
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------");
		
	}
	
}
