

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 단지번호붙이기 {

	static int[][] map;
	static int n;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int cnt=0;
	static ArrayList<Integer> arr= new ArrayList<Integer>();
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		int val=1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==1) {
					bfs(i,j,val+1);
					cnt++;
				}
			}
		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(cnt);
		Collections.sort(arr);
		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}
	}
	private static void bfs(int r, int c,int val) {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v= new boolean[n][n];
		v[r][c]=true;
		q.offer(new Point(r, c));
		
		int count=1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			map[p.r][p.c] = 2;
			for(int i=0;i<4;i++) {
				int nr = p.r+dr[i];
				int nc = p.c+dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n &&!v[nr][nc]&& map[nr][nc] == 1) {
					v[nr][nc]=true;
					map[nr][nc]= 2;
					count++;
					q.add(new Point(nr, nc));
					
				}
			}
		}
		arr.add(count);
	}			
		
	}


class Point{
	int r;
	int c;
	
	public Point(int r, int c) {
		super();
		this.r = r;
		this.c = c;
		
	}
	
}

