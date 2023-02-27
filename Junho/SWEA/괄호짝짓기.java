

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class °ýÈ£Â¦Áþ±â {
	
	static HashMap<Character, Integer> hm_left;
	static HashMap<Character, Integer> hm_right;
	public static void main(String[] args) throws Exception, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1;tc<=10;tc++) {
			
			setting();
			int n= Integer.parseInt(br.readLine());
			String str = br.readLine();
			char[] data = new char[n];
			int[] count=new int[4];
			for(int i=0;i<n;i++) {
				data[i]=str.charAt(i);
			}
			
			for(int i=0;i<n;i++) {
				if(data[i]=='('||data[i]=='['||data[i]=='{'||data[i]=='<') {
					count[hm_left.get(data[i])]++;
				}
				else count[hm_right.get(data[i])]++;
			}
			int result=0;
			
			if(check(count)) result=1;
			
			System.out.println("#"+tc+" "+result);
			
			
		}
	}
	
	private static boolean check(int[] count) {
		for(int i=0;i<count.length;i++) {
			if(count[i]%2!=0) return false;
		}
		return true;
	}

	private static void setting() {
		hm_left = new HashMap<Character, Integer>();
		hm_left.put('(', 0);
		hm_left.put('[', 1);
		hm_left.put('{', 2);
		hm_left.put('<', 3);
		hm_right= new HashMap<Character, Integer>();
		hm_right.put(')', 0);
		hm_right.put(']', 1);
		hm_right.put('}', 2);
		hm_right.put('>', 3);
	}
}
