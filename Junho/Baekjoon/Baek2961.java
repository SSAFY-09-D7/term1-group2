package ssafy.com.lecture.day0209.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//도영이가 만든 맛있는 음식
public class Baek2961 {

	static int n;
	static Ingred[] ingred;
	static StringTokenizer st;
	static int min_diff=Integer.MAX_VALUE;
	static boolean[] check;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ingred = new Ingred[n];
		check = new boolean[n];
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine());
			ingred[i]=new Ingred(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		recur(0);
		System.out.println(min_diff);
	}
	private static void recur(int idx) {
		if(idx==n) {
			if(allfalse()) {
				return;
			}
			int total_sour=1;
			int total_bitter=0;
			for(int i=0;i<n;i++) {
				if(check[i]) {
					total_sour*=ingred[i].sour;
					total_bitter+=ingred[i].bitter;
				}
				
			}
			min_diff=Math.min(min_diff, Math.abs(total_bitter-total_sour));
		
			return;
		}
		
		check[idx]=true;
		recur(idx+1);
		check[idx]=false;
		recur(idx+1);
		
	}
	private static boolean allfalse() {
		for(int i=0;i<n;i++) {
			if(check[i]) return false;
		}
		return true;
	}
}

class Ingred{
	int sour;
	int bitter;
	public Ingred(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
	}
	
}