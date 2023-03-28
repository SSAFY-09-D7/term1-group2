package etc;
import java.util.Arrays;
/*
 * 아파트를 각 층별로 파란색 또는 노란색 페인츠로 칠하되
 * 노란색은 인접한 두 층에 연속하여 사용할 수 없고,
 * 파란색은 인접한 두 층에 연속해서 사용할 수 없을 때
 * 층의 아파트를 칠할 수 있는 방법의 개수: f(n)일 때 f(8) 구하기
 */
import java.util.Scanner;

public class B_SW_Algo_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int arr[] = new int[n + 1];
		
		arr[1] = 2;
		arr[2] = 3;
		
		for(int i = 3; i < n + 1; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		
		System.out.println(arr[n]);
	}
}
