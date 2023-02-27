package 알고리즘스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//크로아티아 알파벳
public class Problem2941 {

	static String[] code= {"c=","c-","dz=","d-","lj","nj","s=","z="};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String data = br.readLine();
		
		//크로아티아 알바벳이 단어에 포함되면 1로 replace
		for(int i=0;i<code.length;i++) {
			if(data.contains(code[i])) {
				data=data.replace(code[i], "1");
			}
		}
		
		
		System.out.println(data.length());
	}
	
}
