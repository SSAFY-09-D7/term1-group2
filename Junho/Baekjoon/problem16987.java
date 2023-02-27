package 알고리즘스터디.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//계란으로 계란치기
/*
 * 계란 : 내구도와 무게
 * 계란 의 내구도 = 상대 계란의 무게 
 * 내구도가<=0 계란 깨짐
 * 
 * 
 * */
public class problem16987 {

	static int n;
	static int[]egg_stregth;
	static int[]egg_weight;
	static boolean[] egg_broken;
	static StringTokenizer st;
	static int cnt;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		egg_stregth = new int[n];
		egg_weight = new int[n];
		egg_broken = new boolean[n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			egg_stregth[i]=Integer.parseInt(st.nextToken());
			egg_weight[i]=Integer.parseInt(st.nextToken());
		}
		
		recur(0);
		System.out.println(cnt);
		
	}
	private static void recur(int idx) {
		
		int count=0;
		for(int i=0;i<n;i++) {
			if(egg_stregth[i]<=0) {
				count+=1;
			}
		}
		cnt = Math.max(count, cnt);
		//basis part
		if(idx == n) {
			return;
			}
		//inductive part
		if(egg_stregth[idx]<=0) {
			recur(idx+1);
		}else {
			for(int i=0;i<n;i++) {
				if(i==idx) continue;
				if(egg_stregth[i]>0) {
					egg_stregth[idx]-=egg_weight[i];
					egg_stregth[i]-=egg_weight[idx];
					recur(idx+1);
					egg_stregth[idx]+=egg_weight[i];
					egg_stregth[i]+=egg_weight[idx];
					
				}
			}
		}
		

				

			}
		}
