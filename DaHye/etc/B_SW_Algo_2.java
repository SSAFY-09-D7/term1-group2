package etc;

import java.util.Scanner;

/*
 * 1cm - 파란 막대, 노란 막대
 * 2cm - 빨간 막대
 * 막대들을 연결해서 n cm 막대를 만드는 방법의 수: f(n)
 */
public class B_SW_Algo_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int arr[] = new int[n + 1];
		
		arr[1] = 2;
		arr[2] = 5;
		
		for(int i = 3; i < arr.length; i++) {
			arr[i] = arr[i - 1] * 2 + arr[i - 2];
		}
		System.out.println(arr[n]);
	}
}
