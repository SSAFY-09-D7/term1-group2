package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//롤 케이크
public class Problem3985 {

	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] check = new int[l+1];
		
		int e_val=0; //기대하고 있는 방청객 케이크 조각 최대 수
		int e_num=0;// 기대하고 있는 방청객 번호
		
		int f_num=0; //실제로 방청객 케이크 조각 최대 수
		int f_val = 0;//실제로 방청객 번호
		person[] nums= new person[n];
		
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());	
			
			if(e_val<(y-x)) {
				e_val=y-x;
				e_num=i+1;
				
			}
			nums[i]=new person(x, y);
		}
		
		for (int i = 0; i < nums.length; i++) {
			int cnt=0;
			for(int j=nums[i].x;j<=nums[i].y;j++) {
				
				if(check[j]==0) {
					check[j]=i+1;
					cnt+=1;
				}
				else continue;
			}
			if(f_val<cnt) {
				f_val=cnt;
				f_num=i+1;
			}
		}
		
		
		
	System.out.println(e_num);
	System.out.println(f_num);

	}

}

class person{
	int x;
	int y;
	public person(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}