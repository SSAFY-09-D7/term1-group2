import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 설탕배달 {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n= Integer.parseInt(br.readLine());
		
		int cnt=0;
		
		if(n%5==0) {
			System.out.println(n/5);
		}
		
		else {
			cnt=0;
			while(n>0) {
				n-=3;
				cnt++;
				if(n%5==0) {
					cnt+=n/5;
					System.out.println(cnt);
					break;
				}
				else if (n==1||n==2) {
					System.out.println(-1);
					break;
				}else if (n==0) {
					System.out.println(cnt);
					break;
				}
			}
		}
		
	}

}
