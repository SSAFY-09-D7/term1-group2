

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 애너그램 {
	
	static int n;
	static char[] alp;
	static ArrayList<String> arr;
	static char[] sel;
	static char[] tmp;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int t=0;t<n;t++) {
			String str = br.readLine();
			arr = new ArrayList<String>();
			alp  = str.toCharArray();
			sel = new char[alp.length];
			tmp = new char[alp.length];
			v= new boolean[alp.length];
			//오름차순 정렬
			Arrays.sort(alp);
			recur(0);
			for (int i = 0; i < arr.size(); i++) {
				sb.append(arr.get(i)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	private static void recur(int idx) {
		//basis part
		if(idx==sel.length) {
//			System.out.println(Arrays.toString(sel));
			String str ="";
			for(int i=0;i<sel.length;i++) {
				str += sel[i];
			}
				arr.add(str);
			return;
		}
		
		//inductive part
		
		//중복 방지
		tmp[idx]=' ';
		for(int i=0;i<alp.length;i++) {
			if(!v[i]&&tmp[idx]!=alp[i]) {
				tmp[idx]=alp[i];
//				System.out.println(tmp[idx]);
				v[i]=true;
				sel[idx]=alp[i];
				recur(idx+1);
				v[i]=false;
			}
		}
		
	}
}
