import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 한빈이와SpotMart {

	static int n,m;
	static int[] snack_weight;
	static int[] sel;
	static StringTokenizer st;
	static int max_weight;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			snack_weight= new int[n];
			max_weight = -1;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				snack_weight[i]=Integer.parseInt(st.nextToken());
			}
			sel = new int[2];
			
			recur(0,0);
			System.out.println("#"+tc+" "+max_weight); 
		}
	}
	private static void recur(int idx, int s) {
		if(idx == sel.length) {
			int total=0;
			for(int i=0;i<sel.length;i++) {
				total+=sel[i];
			}
			if(total<=m) {
				max_weight=Math.max(max_weight, total);
			}
			
			
//			System.out.println(Arrays.toString(sel));
			return;
		}
		for(int i=s;i<snack_weight.length;i++) {
			sel[idx]=snack_weight[i];
			recur(idx+1, i+1);
		}
		
	}
}
