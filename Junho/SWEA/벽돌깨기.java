package ssafy.com.알고리즘.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 벽돌깨기 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int n,w,h,brick_cnt,min;
	static int[][] map;
	static int[][] copyMap;
	static int[] sel;
	static Stack<Integer>stack;
	static  StringTokenizer st;

	static class Point {
		int x, y, val;

		public Point(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", val=" + val + "]";
		}

		
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h= Integer.parseInt(st.nextToken());
			map= new int[h][w];
			
			sel = new int[n];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//중복순열 
			recur(0);
			System.out.println("#"+tc+" "+min);
		}
	}

	private static void print(int[][] copyMap2) {
		for (int i = 0; i < copyMap2.length; i++) {
			for (int j = 0; j < copyMap2[i].length; j++) {
				System.out.print(copyMap2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	private static void recur(int idx) {
		//basis part
		if(idx==n) {
//			System.out.println(Arrays.toString(sel));
			brick_cnt=0;
//			copyMap= new int[h][w];
			for (int i = 0; i < h; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, w);
			}
			for (int i = 0; i < sel.length; i++) {
				int index = sel[i];
				for (int j = 0; j < h; j++) {
					if(copyMap[j][index]!=0) {
						bfs(j,index);
						brick_down();
						break;
					}
				}
				
			}
			//벽돌 세기
			for (int i = 0; i < copyMap.length; i++) {
				for (int j = 0; j < copyMap[i].length; j++) {
					if(copyMap[i][j]!=0) brick_cnt++;
				}
			}
			min = Math.min(min, brick_cnt);
			
//			print(copyMap);
			
			return;
		}
		
		//inductive part
		for (int i = 0; i < w; i++) {
			sel[idx]=i;
			recur(idx+1);
		}
		
	}
//벽돌 내리기
	private static void brick_down() {
		for (int i = 0; i < w; i++) {
			stack = new Stack<Integer>();
			int n = 0;
			for (int j = 0; j < h; j++) {
				if(copyMap[j][i]!=0) {
					stack.push(copyMap[j][i]);
					n++;
				}
			}
			for (int j = h-1; j > h-1-n; j--) {
				copyMap[j][i]=stack.pop();
			}
			for(int j=h-1-n;j>=0;j--) {
				copyMap[j][i]=0;
			}
		}
		
	}
//벽돌 깨기
	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y, copyMap[x][y]));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			copyMap[p.x][p.y]=0;
			for (int i = 1; i < p.val; i++) {
				for (int d = 0; d < 4; d++) {
					int nx = p.x+(dx[d]*i);
					int ny = p.y+(dy[d]*i);
					if(0<=nx&&nx<h&&0<=ny&&ny<w&&copyMap[nx][ny]!=0) {
						q.offer(new Point(nx, ny, copyMap[nx][ny]));
						copyMap[nx][ny]=0;
					}
				}
			}
			
		}
	}
}
