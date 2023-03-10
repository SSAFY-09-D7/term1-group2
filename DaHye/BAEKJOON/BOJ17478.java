package BAEKJOON;

import java.util.Scanner;

public class BOJ17478 {
	/*
	 * 재귀함수가 뭔가요?
	 */
	static String[] arr;
	public static void main(String[] args) {
		arr= new String[] {"어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.",
						   "\"재귀함수가 뭔가요?\"",
						   "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
						   "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
						   "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
						   "\"재귀함수는 자기 자신을 호출하는 함수라네\"",
						   "라고 답변하였지."};

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		System.out.println(arr[0]);
		recursive(N, 2);
		System.out.println(arr[6]);

	}

	// int n: 반복횟수, int idx: arr의 인덱스
	static String slash = "";
	public static void recursive(int n, int idx) {
		if (n == 0) {
			System.out.println(slash + arr[1]);
			System.out.println(slash + arr[5]);
			return;
		}
		
		for(int i = 1; i < 5; i++) {
			System.out.println(slash + arr[i]);
		}
		
		slash += "____";
		recursive(n - 1, idx);
		System.out.println(slash + arr[arr.length - 1]);
		slash = slash.substring(0, slash.length() - 4);

	}
}
