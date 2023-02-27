

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 요리사 {

	static int n;
	static int[][] ingred_taste;
	static StringTokenizer st;
	static int[] v;
	static int a,b,total;
	static int min,score;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			n = Integer.parseInt(br.readLine());
			ingred_taste = new int[n][n];
			v= new int[n];
			min = Integer.MAX_VALUE;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					ingred_taste[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			recur(0,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void recur(int idx,int s) {
		//basis part
		if(idx==v.length/2) {
//			System.out.println(Arrays.toString(v));
			synergy();
			
			return;
			
		}
		
		//inductive part
		for(int i=s;i<n;i++) {
				v[i]=1;
				recur(idx+1,i+1);
				v[i]=0;
			}
		}
	private static void synergy() {
		a=0;
		b=0;
		total=0;
		
		compare();
		
		total = Math.abs(a-b);
//		System.out.println(total);
		min =Math.min(min, total);
		
	}
	private static void compare() {
		// TODO Auto-generated method stub
		for(int i=0;i<v.length-1;i++) {
			for(int j=i+1;j<v.length;j++) {
				if(v[i]==1&&v[j]==1) {
					a+=ingred_taste[i][j]+ingred_taste[j][i];
				}
				else if(v[i]==0&&v[j]==0){
					b+= ingred_taste[i][j]+ingred_taste[j][i];
				}
			}
		}
		
	}
	
		
}
