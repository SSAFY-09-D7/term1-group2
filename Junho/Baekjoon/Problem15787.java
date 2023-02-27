package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

//기차가 어둠을 헤치고 은하수를
//기차는 20개의 좌석
public class Problem15787 {

	static StringTokenizer st;
	static int[][] train;
	static int train_n;
	static Set<String> train_check; 
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //기차 개수
		int m = Integer.parseInt(st.nextToken()); // 명령 개수
		train = new int[n ][20];

		for (int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int control_n = Integer.parseInt(st.nextToken());
			control(control_n, st);
		}
		
		
		train_check = new HashSet<>();
		
		//중복되지 않는 배열 개수 세기
		for(int i=0;i<n;i++) {
			String train_data = Arrays.toString(train[i]);
			train_check.add(train_data);
		}
		train_n=train_check.size();
		
		System.out.println(train_n);

	
	}
	//
	private static void control(int control_n, StringTokenizer st2) {
		if (control_n == 1) {
			int train_idx = Integer.parseInt(st.nextToken());
			int train_seat = Integer.parseInt(st.nextToken());
			train[train_idx-1][train_seat-1] = 1;
		} else if (control_n == 2) {
			int train_idx = Integer.parseInt(st.nextToken());
			int train_seat = Integer.parseInt(st.nextToken());
			train[train_idx-1][train_seat-1] = 0;
			
		} else if (control_n == 3) {
			int train_idx = Integer.parseInt(st.nextToken());
			for (int i = train[train_idx-1].length - 1; i >0; i--) {
				train[train_idx-1][i] = train[train_idx-1][i - 1];
			}
			train[train_idx-1][0] = 0;
		} else {
			int train_idx = Integer.parseInt(st.nextToken());
			for (int i = 0; i < train[train_idx-1].length-1; i++) {
				train[train_idx-1][i] = train[train_idx-1][i + 1];
			}
			train[train_idx-1][19]=0;
		}
	}

}
