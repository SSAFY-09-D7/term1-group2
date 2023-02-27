

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추 {

	static int[][] map;
	static StringTokenizer st;
	static int n,m,k;
	static int bug;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			bug=0;
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=1;
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==1) {
						bfs(i,j);
						bug++;
					}
				}
			}
			System.out.println(bug);
			
		}
		
		
		
	}
	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v= new boolean[n][m];
		v[x][y]=true;
		q.offer(new Point(x,y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			map[p.r][p.c] = 0;
			for(int i=0;i<4;i++) {
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
