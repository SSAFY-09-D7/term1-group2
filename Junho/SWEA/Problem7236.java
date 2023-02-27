package ssafy.com.알고리즘.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//저수지의 물의 총 깊이 구하기
public class Problem7236 {
	static int[] dx= {-1,-1,0,0,1,1,1,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] map = new int[n][n];
			int max = 0;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=st.nextToken().charAt(0);
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					boolean flag = false;
					int cnt = -1;
					if(map[i][j]=='W') {
						for(int k=0;k<8;k++) {
							int nx = i+dx[k];
							int ny = j+dy[k];
							if(0<=nx&&nx<n&&0<=ny&&ny<n) {
								if(map[nx][ny]=='W') {
									flag = true;
									cnt++;
								}
							}
						}
						if(!flag) cnt=1;
					}
					max=Math.max(max, cnt);
				}
			}
			System.out.println("#"+tc+" "+max);
			
			
		}
		
	}
}
