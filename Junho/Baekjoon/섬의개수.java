

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 섬의개수 {
	static int[][] map;
	static int n,m;
	static StringTokenizer st;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if(n==0 && m==0) break;
			map= new int[n][m];
			cnt =0;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<m;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==1) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v= new boolean[n][m];
		v[r][c]=true;
		q.offer(new Point(r,c));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			map[p.r][p.c] = 0;
			for(int i=0;i<8;i++) {
				int nr = p.r+dr[i];
				int nc = p.c+dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m&&!v[nr][nc]&& map[nr][nc] == 1) {
					v[nr][nc]=true;
					map[nr][nc]= 0;
					
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