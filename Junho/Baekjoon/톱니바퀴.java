package ssafy.com.알고리즘.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 
 * 0: n극  1: S극
 * 
 * */
public class 톱니바퀴 {
	static int[][] gear;
	static int n,score;
	static StringTokenizer st;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j]=input.charAt(j)-'0';
			}
		}
		
		n = Integer.parseInt(br.readLine());
		score=0;
		for (int t = 0; t < n; t++) {
			st = new StringTokenizer(br.readLine());
			v = new boolean[4];
			int gear_n = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			check(gear_n);
//			System.out.println(Arrays.toString(v));
			rotate(gear_n,dir);
			
			
		}
		//1~4번 톱니바퀴 점수의 합 구하기
		if(gear[0][0]==1) score+=1;
		if(gear[1][0]==1) score+=2;
		if(gear[2][0]==1) score+=4;
		if(gear[3][0]==1) score+=8;
		System.out.println(score);
		
	}
	private static void print() {
		for (int i = 0; i < gear.length; i++) {
			for (int j = 0; j < gear[i].length; j++) {
				System.out.print(gear[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	//톱니바퀴 회전
	private static void rotate(int gear_n, int dir) {
		int idx_left=gear_n-1;
		int idx_right=gear_n-1;
		int tmp_dir=dir;
		rotate_gear(gear_n-1,dir);
		
		while(idx_left>0) {
			idx_left--;
			if(v[idx_left]) {
				dir*=-1;
				
				rotate_gear(idx_left, dir);
			}
			
		}
		
		while(idx_right<3) {
			idx_right++;
			if(v[idx_right]) {
				tmp_dir*=-1;
				rotate_gear(idx_right, tmp_dir);
//				print();
			}
			
		}
		
	}
	
	private static void rotate_gear(int gear_n, int dir) {
		//시계방향
		if(dir==1) {
			int last_val = gear[gear_n][7];
			for (int i =  7; i >0; i--) {
				gear[gear_n][i]=gear[gear_n][i-1];
			}
			gear[gear_n][0]=last_val;
		}
		//반시계방향
		else {
			int first_val = gear[gear_n][0];
			for (int i = 0; i < 7; i++) {
				gear[gear_n][i]=gear[gear_n][i+1];
			}
			gear[gear_n][7]=first_val;
		}
	}

	// 1~4번 톱니바퀴 중 돌릴 수 있는 톱니바퀴 확인
	private static void check(int gear_n) {
		int idx_left = gear_n - 1;
		int idx_right = gear_n - 1;
		while (idx_left > 0) {
			
			if(gear[idx_left][6]!=gear[idx_left-1][2]) {
				v[idx_left-1]=true;
				idx_left--;
			}
			else break;
		}
		while (idx_right < 3) {
			
			if(gear[idx_right][2]!=gear[idx_right+1][6]) {
				v[idx_right+1]=true;
				idx_right++;
			}
			else break;
		}

	}
	
}
