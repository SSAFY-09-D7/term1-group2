package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

//연산자 끼워넣기
public class Problem14888 {

	static int max = Integer.MIN_VALUE; //정수 최솟값
	static int min = Integer.MAX_VALUE; // 정수 최댓값
	static StringTokenizer st;
	static int[] operator;
	static int[] num;
	static int n;

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		num = new int[n];
		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());

		for(int i=0;i<4;i++) {
			operator[i]=Integer.parseInt(st.nextToken());
		}

		int c_val=num[0];
		calculation(1,c_val);



	System.out.println(max);
	System.out.println(min);

	}

	private static void calculation(int x,int c_val) {

		//최대 최솟값 구하기
		if(x==n) {
			max = Math.max(max, c_val);
			min = Math.min(min, c_val);
			return;
		}
		
		int result=0;
		//연산자 하나씩 돌면서 1개 이상 있으면 계산
		for(int i=0;i<4;i++) {
			if(operator[i]>=1) {
			//	System.out.println(c_val);
				operator[i]-=1;
				if(i==0) {
					result = c_val+num[x];
				}
				else if(i==1) {
					result = c_val-num[x];
				}
				else if (i==2) {
					result = c_val*num[x];
				}else {
					if(c_val<0 && num[x]>0) {
						c_val*=-1;
						result = c_val/num[x];
						result*=-1;
					}
					else result = c_val/num[x];
				}
				calculation(x+1,result);
				
				operator[i]+=1;
			}
		}


	}


}