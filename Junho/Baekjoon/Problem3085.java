package 알고리즘스터디.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//사탕 게임
public class Problem3085 {

	//인접한 투 칸 고르기
	//배열에 가장 긴 연속 부분(행,열) 최대 개수 check
	//C:빨 P:파 Z:초, Y:노
	
	//우,하
	static int[] dx= {0,1};
	static int[] dy= {1,0};
	static int n;
	static char[][] candy;
	static int max;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		candy = new char[n][n];
		
		for (int i = 0; i < candy.length; i++) {
			String data = br.readLine();
			for (int j = 0; j < candy.length; j++) {
				candy[i][j]=data.charAt(j);
			}
		}
		max=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				check_next(i,j);
			}
		}
		System.out.println(max);
		

	}
	private static void check_next(int x, int y) {
		
		for(int i=0;i<2;i++) {
			int nx = x;
			int ny = y;
			nx +=dx[i];
			ny+=dy[i];
			if(nx<n&&ny<n&&candy[x][y]!=candy[nx][ny]) {
				swap(x,y,nx,ny);
				
				//행 긴 연속 부분
				for (int j = 0; j < candy.length; j++) {
					char data = candy[j][0];
					int count1=1;
					for (int k = 1; k < candy.length; k++) {
						if(candy[j][k]==data) {
							count1+=1;
						}else { 
							data=candy[j][k];
							max = Math.max(count1, max);
							count1=1;
						}
						
					}
					max= Math.max(max, count1);
					
				}
				//열 긴 연속 부분
				for (int j = 0; j < candy.length; j++) {
					char data2 = candy[0][j];
					int count2=1;
					for (int k = 1; k < candy.length; k++) {
						if(candy[k][j]==data2) {
							count2+=1;
						}else { 
							data2=candy[k][j];
							max = Math.max(count2, max);
							count2=1;
						}
						
					}
					max= Math.max(max, count2);
//					System.out.println(count2);
					
				}
//				for (int j = 0; j < candy.length; j++) {
//					for (int j2 = 0; j2 < candy.length; j2++) {
//						System.out.print(candy[j][j2]);
//					}
//					System.out.println();
//					
//					
//				}
//				System.out.println();
				swap(x,y,nx,ny);
			
			}
			
			
		}
	}
	//문자 스압
	private static void swap(int x, int y, int nx, int ny) {
		
		char temp = candy[x][y];
		candy[x][y]=candy[nx][ny];
		candy[nx][ny]=temp;
		
	}

}
