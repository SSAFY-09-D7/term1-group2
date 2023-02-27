import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백만장자프로젝트 {

	static int[] num;
	static int n;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			num = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				num[i]=Integer.parseInt(st.nextToken());
			}
		
			int max_val = num[n-1];
			long anw=0;
			for(int i=n-2;i>=0;i--) {
				if(max_val<num[i]) {
					max_val=num[i];
				}
				anw+=max_val-num[i];
			}
			System.out.println("#"+tc+" "+anw);
		}
		
	}

}
