package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		//�ʱ�ȭ
		st=new StringTokenizer(br.readLine());
		int n= Integer.parseInt(st.nextToken());
		int m= Integer.parseInt(st.nextToken());
		int r= Integer.parseInt(st.nextToken());
		
		//ó�� ��
		int map[][]=new int[n][m];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// ����
		for(int i=0;i<r;i++) {
			map = func(map);
		}
		
		//���� ���
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j] + " ");
			}sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static public int[][] func(int[][] input) {
		// �ݺ�Ƚ��
		int count = 0;
		count = Math.min(input.length, input[0].length) / 2;
		int[][] tmp = new int[input.length][input[0].length]; 
		
		for(int t=0;t<count;t++) {
			int initX = t;
			int initY = t;
			
			int dir = 0;
			
			while(true) {
				if(dir == 4) break;
				int nX = initX + dx[dir];
				int nY = initY + dy[dir];
				if(nX < t || nX >= input.length -t || nY < t || nY >= input[0].length - t) {
					dir++;
					continue;
				}
				tmp[nX][nY] = input[initX][initY];

				initX += dx[dir];
				initY += dy[dir];
			}
		}
		
		return tmp;
	}
}

