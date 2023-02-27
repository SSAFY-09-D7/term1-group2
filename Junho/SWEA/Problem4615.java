package 알고리즘스터디.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//재미있는 오셀로 게임
public class Problem4615 {
	static int[]	dx= {-1,-1,0,1,1,1,0,-1};
	static int[]	 dy= {0,1,1,1,0,-1,-1,-1};
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][]board = new int[n][n];
			int mid = n/2;
			
			board[mid][mid]=2;
			board[mid-1][mid-1]=2;
			board[mid-1][mid]=1;
			board[mid][mid-1]=1;
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				x-=1;
				y-=1;
				//처음 돌 놓기
				board[x][y]=c;
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				for(int j=0;j<8;j++) {
					
					int nx = x+dx[j];
					int ny = y+dy[j];
					
					while(true) {
						//범위를 벗어나면 
						if(0>nx||nx>=n||0>ny||ny>=n) {
							list.clear();
							break;
						}
						//공백이면
						if(board[nx][ny]==0) {
							list.clear();
							break;
						}
						//같은 색이면 멈춤
						if (board[nx][ny] == c) {
							break;
						}
						//다른 색이면 list에 nx ny 추가
						
						list.add(nx);
						list.add(ny);
						
						nx += dx[j];
						ny += dy[j];
					}
					//색 바꾸기
					for(int k=0;k<list.size()-1;k+=2) {
						int tx = list.get(k);
						int ty = list.get(k+1);
						
						if(c==1) board[tx][ty]=1;
						else board[tx][ty]=2;
					}
					
				}
				
				
			}
			int b=0,w=0;
			//흰돌 검은돌 갯수 찾기
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if(board[i][j]==1) b+=1;
					else if(board[i][j]==2) w+=1;
				}
			}
			System.out.println("#"+tc+" "+b+" "+w);

		}
		
	}
}
