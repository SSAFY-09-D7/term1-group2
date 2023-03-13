package ssafy.com.알고리즘.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 무기공학 {
	static int n,m;
	static int[][] map;
	static boolean[][] flag;
	static StringTokenizer st;
	static int max_val=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map= new int[n][m];
		flag = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0,0);
		
		System.out.println(max_val==0?0:max_val);
	}
	private static void print(boolean[][] flag2) {
		for (int i = 0; i < flag2.length; i++) {
			for (int j = 0; j < flag2[i].length; j++) {
				System.out.print(flag2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------------------");
	}
	private static void recur(int idx, int val) {
		if(idx==n*m) {
			
			max_val=Math.max(val, max_val);
//			print(flag);
			return;
		}
		int x = idx/m;
		int y= idx%m;
		//1번
		if(x+1<n&&y+1<m&&check(x,y,1)) {
			flag[x][y]=true;
			flag[x][y+1]=true;
			flag[x+1][y]=true;
			recur(idx+1, val+(map[x][y]*2)+map[x][y+1]+map[x+1][y]);
			flag[x][y]=false;
			flag[x][y+1]=false;
			flag[x+1][y]=false;
			
//			System.out.printf("%d %d\n",x,y);
		}
		if(y-1>=0&&x+1<n&&check(x,y,2)) {
			flag[x][y]=true;
			flag[x][y-1]=true;
			flag[x+1][y]=true;
			recur(idx+1, val+(map[x][y]*2)+map[x+1][y]+map[x][y-1]);
			flag[x][y]=false;
			flag[x][y-1]=false;
			flag[x+1][y]=false;
		}
		if(x-1>=0&&y+1<m&&check(x,y,3)) {
			flag[x][y]=true;
			flag[x-1][y]=true;
			flag[x][y+1]=true;
			recur(idx+1, val+(map[x][y]*2)+map[x-1][y]+map[x][y+1]);
			flag[x][y]=false;
			flag[x-1][y]=false;
			flag[x][y+1]=false;
		}
		if(y-1>=0&&x-1>=0&&check(x,y,4)) {
			flag[x][y]=true;
			flag[x-1][y]=true;
			flag[x][y-1]=true;
			recur(idx+1, val+(map[x][y]*2)+map[x-1][y]+map[x][y-1]);
			flag[x][y]=false;
			flag[x-1][y]=false;
			flag[x][y-1]=false;
		}
		recur(idx + 1, val);

	}

	private static boolean check(int x, int y, int n) {
		if (n == 1) {
			if (flag[x][y] || flag[x + 1][y] || flag[x][y + 1])
				return false;
		} else if (n == 2) {
			if (flag[x][y] || flag[x][y - 1] || flag[x + 1][y])
				return false;
		} else if (n == 3) {
			if (flag[x][y] || flag[x-1][y ] || flag[x][y+1])
				return false;
		} else if (n == 4) {
			if (flag[x][y] || flag[x-1][y] || flag[x][y-1])
				return false;
		}
		return true;
	}
}
