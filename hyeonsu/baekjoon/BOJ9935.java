package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ9935 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static String in, bomb;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		//�ʱ�ȭ
		in = br.readLine();
		bomb = br.readLine();
		
		//����
		for (int i = 0; i < in.length(); i++) {
			sb.append(in.charAt(i));
			
			//sb�� ���̰� ���� ���ڿ����� ��ų� ���� ���
			if (sb.length() >= bomb.length()) {
				//������ ��ġ�κ��� ���� ���ڿ��� ���̸�ŭ �ڷ� ���� ���� ���ڿ��� ������ Ȯ���Ѵ�.
				int startIdx = sb.length() - bomb.length();
				int idx = 0;
				boolean flag = true;
				
				for (int j = startIdx; j < sb.length(); j++) {
					if (sb.charAt(j) != bomb.charAt(idx++)) {
						flag = false;
						break;
					}
				}
				//���� ���ڿ��� ��� ��������.
				if (flag) sb.delete(startIdx, sb.length());
			}
		}
		
		//���� ���
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}

}
