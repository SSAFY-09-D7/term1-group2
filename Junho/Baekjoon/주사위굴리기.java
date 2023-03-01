package ssafy.com.알고리즘.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위굴리기 {

	
	//동서북남
	static int[] dx= {0,0,-1,1};
	static int[] dy= {1,-1,0,0};
	
	static StringTokenizer st;
	static int n,m,x,y,k;
	static int[][] map;
	static int[] order;
	static int[] dice;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dice = new int[7];
		order = new int[k];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<k;i++) {
			order[i]=Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < order.length; i++) {
			int nx = x+dx[order[i]-1];
			int ny = y+dy[order[i]-1];
			//범위에 벗어나는 경우 
			if(nx <0 || ny < 0 || nx >=n|| ny >= m) continue;
			play(nx,ny,order[i]);
			System.out.println(dice[3]);
			x = nx;
			y = ny;
		}

		
		
	}
	/*
	 * 	1
	 * 2	3	4
	 * 	5
	 * 	6
	 * 
	 * */
	private static void play(int x ,int y, int idx) {
		int top_num=dice[3];
		//동
		if(idx==1) {
			dice[3]=dice[4];
			dice[4]=dice[6];
			dice[6]=dice[2];
			dice[2]=top_num;
		}
		//서
		else if(idx==2) {
			dice[3]=dice[2];
			dice[2]=dice[6];
			dice[6]=dice[4];
			dice[4]=top_num;
		}
		//북
		else if (idx==3) {
			dice[3]=dice[5];
			dice[5]=dice[6];
			dice[6]=dice[1];
			dice[1]=top_num;
		}
		//남
		else if(idx==4){
			dice[3]=dice[1];
			dice[1]=dice[6];
			dice[6]=dice[5];
			dice[5]=top_num;
		
		}
		//바닥면이 0인경우
		if(map[x][y]==0) {
			map[x][y]=dice[6];
		}
		//아닌경우
		else {
			dice[6]=map[x][y];
			map[x][y]=0;
		}
		
	}
	
}
