package ssafy.com.lecture.day0227.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이2 {


	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int n;
	static int cnt=0;
	static int[][] map;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[101][101];
		
		for (int t = 0; t < n; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int i=x;i<x+10;i++) {
				for(int j=y;j<y+10;j++) {
					map[i][j]=1;
				}
			}
			
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				boolean flag = false;
				int count=0;
				if(map[i][j]==1) {
					for(int d=0;d<4;d++) {
						int nx = i+dx[d];
						int ny = j+dy[d];
						if(map[nx][ny]==0) {
							flag =true;
							count++;
						}
							
					}
					if(flag) cnt+=count;
				}
			}
		}
//		for (int i = 0; i < 100; i++) {
//			for(int j=0;j<100;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(cnt);
	}
	
}
