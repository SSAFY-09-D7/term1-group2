

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 규영이와인영이의카드게임 {

	static int[] card1;
	static ArrayList<Integer> card2;
	static int[] sel;
	static boolean[] v;
	static StringTokenizer st;
	static int win,lose;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			card1 = new int[9];
			card2 = new ArrayList<Integer>();
			sel = new int[9];
			v = new boolean[9];
			win=0;
			lose=0;
			for (int i = 0; i < 9; i++) {
				card1[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<19;i++) {
				if(input(i)) card2.add(i);
			}
			
			recur(0);
			
			
			System.out.println("#"+tc+" "+win+" "+lose);

		}
	}
	private static void recur(int idx) {
		if(idx==sel.length) {
//			System.out.println(Arrays.toString(sel));
			
			int score1=0;
			int score2=0;
			for(int i=0;i<9;i++) {
				if(card1[i]>sel[i]) { 
				score1+=card1[i]+sel[i];
				}
				else score2+=card1[i]+sel[i];
			}
			if(score1>score2) win++;
			else lose++;
			return;
		}
		
		for(int i=0;i<card2.size();i++) {
			if(!v[i]) {
				v[i]=true;
				sel[idx]=card2.get(i);
				recur(idx+1);
				v[i]=false;
			}
		}
		
		
		
	}
	private static boolean input(int val) {
		
		for(int i=0;i<9;i++) {
			if(card1[i]==val) return false;
		}
		return true;
	}
}
