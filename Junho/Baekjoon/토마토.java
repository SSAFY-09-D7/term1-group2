

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토 {
	static int[][] map;
	static int n,m;
	static StringTokenizer st;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map= new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		int max=0;
		
		boolean flag = true;
		loop:
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) {
					flag = false;
					break loop;
				}
				max = Math.max(max, map[i][j]);
			}
		}
		if(flag) {
			
			System.out.println(max-1);
		}
		else {
			System.out.println(-1);
		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		
	}
	private static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v= new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==1) {
					v[i][j]=true;
					q.offer(new Point(i,j));
					
				}
			}
		}
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0;i<4;i++) {
				int nr = p.r+dr[i];
				int nc = p.c+dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m&&!v[nr][nc]&& map[nr][nc] == 0) {
					v[nr][nc]=true;
					map[nr][nc]= map[p.r][p.c]+1;
					
					q.add(new Point(nr, nc));
					
				}
			}
		}
		
	}
}
//class Point{
//	int r;
//	int c;
//	
//	public Point(int r, int c) {
//		super();
//		this.r = r;
//		this.c = c;
//		
//	}
//	
//}