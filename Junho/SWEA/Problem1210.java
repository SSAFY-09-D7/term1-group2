package 알고리즘스터디.SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Problem1210 {

	static int[][] board;
	static StringTokenizer st;
	static int x,y;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1;tc<=10;tc++) {
			int T = Integer.parseInt(br.readLine());
			board = new int[100][100];
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					board[i][j]=Integer.parseInt(st.nextToken());
					if(board[i][j]==2) x=i; y=j;
				}
			}
			visited = new boolean[100][100];
			
			
			while(x!=0) {
				
				visited[x][y]=true;
				//왼쪽
				if(y-1>=0&&board[x][y-1]==1&&visited[x][y-1]==false) {
					y-=1;
					continue;
				}
				//오른쪽
				else if(y+1<100&&board[x][y+1]==1&&visited[x][y+1]==false) {
					y+=1;
					continue;
				}
				//위쪽
				else x-=1;
				
				
			}
			System.out.println("#"+tc+" "+y);
		
		
			
		}
		

	}

}

