package 알고리즘스터디.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//직사각형을 만드는 방법
public class Problem8320 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int count=0;
		
		for(int i=1;i<=n;i++) {
			//만약 i*j(넓이)가 n이하이면 +1
			for(int j=1;j<=i;j++) {
				if(i*j<=n) {
					count+=1;
				}
		}
		
		}
		System.out.println(count);
	}
}
