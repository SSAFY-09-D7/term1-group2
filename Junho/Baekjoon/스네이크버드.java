import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 스네이크버드 {

	static int fruit_n,sb_length;
	static int[] h;
	static boolean[] v;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		fruit_n = Integer.parseInt(st.nextToken());
		sb_length = Integer.parseInt(st.nextToken());
		h = new int[fruit_n];
		v = new boolean[fruit_n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<fruit_n;i++) {
			h[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(h);
		loop:
		while(true) {
			for (int i = 0; i < h.length; i++) {
				if(sb_length>=h[i]&&!v[i]) {
					v[i]=true;
					sb_length++;
				}
				else break loop;
			}
		}
		System.out.println(sb_length);
	}
}
