
import java.util.*;
import java.io.*;
public class 배열돌리기1 {
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R= Integer.parseInt(st.nextToken());
		//배열 A
		int[][] arr = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//R번 회전시킴
	
		
		int square = Math.min(N, M)/2;
		
		for (int i = 0; i < R; i++) {
			for (int cnt = 0; cnt < square; cnt++) {

				
				int startx = cnt;
				int starty = cnt;
				int dirr = 0;
				int temp = arr[cnt][cnt];
				while (true) {
					if (dirr == 4)
						break;
					int nx=startx+dir[dirr][0];
					int ny=starty+dir[dirr][1];
					
					
					if(nx>=cnt&&nx<N-cnt&&ny>=cnt&&ny<M-cnt) {
						arr[startx][starty]=arr[nx][ny];
						startx=nx;
						starty=ny;
					}
					else dirr++;
//					System.out.println(dirr);
					
//					System.out.println(Arra/ys.deepToString(arr));


				}
				arr[cnt+1][cnt]=temp;
			}
			
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
