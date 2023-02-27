package swea;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA7699 {
	
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int t, r, c, max, tNum = 1;
	static char[][] island;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		t = stoi(br.readLine());
		
		while (t-- > 0) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			island = new char[r][c];
			v = new boolean['Z' + 1];
			
			for (int i = 0; i < r; i++) {
				String s = br.readLine();
				for (int j = 0; j < c; j++) {
					island[i][j] = s.charAt(j);
				}
			}
			
			//����
			v[island[0][0]] = true;
			dfs(0, 0, 1);
			
			//���� �Է�
			bw.write("#" + tNum++ + " " + max + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int stoi(String s) {return Integer.parseInt(s);}
	
	static void dfs(int x, int y, int cnt) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
			
			if (!v[island[nx][ny]]) {
				v[island[nx][ny]] = true;
				dfs(nx, ny, cnt + 1);
				v[island[nx][ny]] = false;
			}
		}
		
		max = Math.max(max, cnt);
	}

}
