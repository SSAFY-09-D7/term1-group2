package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 서강그라운드
 */
public class BOJ14938 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, r, item[], arr[][];
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지역의 개수
		m = Integer.parseInt(st.nextToken()); // 수색범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수
		
		item = new int[n + 1];
		arr = new int[n + 1][n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < item.length; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a][b] = c;
			arr[b][a] = c;
		}
		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				if(r != c && arr[r][c] == 0) arr[r][c] = INF;
			}
		}
		
		for (int k = 1; k < n + 1; k++) { // 경유지
			for (int i = 1; i < n + 1; i++) { // 출발지
				for (int j = 1; j < n + 1; j++) { // 도착지
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		int max = 0;
		for (int r = 1; r < n + 1; r++) {
			int result = 0;
			for(int c = 1; c < n + 1; c++) {
				if(arr[r][c] <= m) {
					result += item[c];
				}
			}
			max = Math.max(result, max);
		}
		
		System.out.println(max);
	}
}
