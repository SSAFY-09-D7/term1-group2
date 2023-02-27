package 알고리즘스터디.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//일곱 난쟁이
public class Problem2309 {

	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> height = new ArrayList<>();
		int sum=0;
		for (int i = 1; i < 10; i++) {
			int val = Integer.parseInt(br.readLine());
			height.add(val);
			sum+=val;
		}
		loop:
		for (int i = 0; i < height.size(); i++) {
			for (int j = i+1; j < height.size(); j++) {
				if(sum-(height.get(i)+height.get(j))==100) {
					height.set(i, 0);
					height.set(j, 0);
					break loop;
				}
				
				
			}
			
		}
		Collections.sort(height);
		
		for (int i = 2; i < height.size(); i++) {
			System.out.println(height.get(i));
		}
		
		
	}
}


