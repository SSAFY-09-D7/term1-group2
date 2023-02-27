import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 스위치켜고끄기 {

	static int switch_n;
	static int[] arr;
	static int st_n,gender,num;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		switch_n= Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[switch_n+1];
		for(int i=1;i<switch_n+1;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		st_n = Integer.parseInt(br.readLine());
		
		for(int t=0;t<st_n;t++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			
			if(gender==1) {
				for(int i=num;i<switch_n+1;i+=num) {
					change(i);
				}
			}else {
				change(num);
				int idx=1;
				while(true) {
					int left = num-idx;
					int right = num+idx;
//					System.out.println(left);
//					System.out.println(right);
					if(left<1||right>switch_n) {
						break;
					}
					if(arr[left]==arr[right]) {
						change(left);
						change(right);
					}
					else break;
					idx++;
				}
			}
		}
		

		for(int i=1;i<switch_n+1;i++) {
			System.out.print(arr[i]+" ");
			if(i%20==0) {
				System.out.println();
			}
		}
		
	}
	private static void change(int i) {
		if(arr[i]==1) arr[i]=0;
		else arr[i]=1;
	}
}
