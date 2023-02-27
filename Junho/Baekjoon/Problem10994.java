package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//별 찍기 - 19
public class Problem10994 {

	static char[][] board;
	static int n,size;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		size = 4*n-3; // 사격형 크기 
		board = new char[size][size];
		
		draw_star(0);
		
		//사격형 출력
//		for(int i=0; i<size;i++) {
//			for(int j=0;j<size;j++) {
//				if(board[i][j]!='*') System.out.print("  "); // 한칸 띄기
//				else System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(board[i][j]!='*') bw.write(' ');
				else bw.write('*');
			}
			bw.write("\n");
		}
		bw.close();
	}

	private static void draw_star(int idx) {
		
		for(int i=0;i<n;i++) {
			for(int j=idx;j<size-idx;j++) {
				board[idx][j]='*'; // 사각형 위
				board[size-idx-1][j]='*'; //사각형 아래
				board[j][idx]='*'; // 사각형 왼쪽
				board[j][size-idx-1]='*'; // 사각형 오른쪽
			}
			idx+=2;
		}
		
		
	}
}
