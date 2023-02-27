package 알고리즘스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 월말평가5번문제 {

	static String[] code = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010" };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//문자의 개수
		int n = Integer.parseInt(br.readLine());

		String data = br.readLine();
		//문자 같은지 비교하는 변수
		int count = 0;
		//몇번쨰
		int idx=0;
		for (int i = 0; i < data.length(); i += 6) {
			int cnt=0;
			idx+=1;
			for (int k = 0; k < code.length; k++) {
				for (int j = 0; j < 6; j++) {
					if (data.charAt(j+i) == code[k].charAt(j)) {
						count += 1;
						
					}
			}
				if(count>=5) {
				sb.append((char)(k+65));
				}
				else {
					cnt+=1;
				}
//				System.out.println("count: "+count);
				count=0;
		
		}
//			System.out.println("cnt :"+cnt);
			if(cnt==8) {
				//초기화
				sb.setLength(0);
				sb.append(idx);
				break;
			}
			
	}
		System.out.println(sb);
	}
	}


