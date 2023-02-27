import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 다이어트 {

	static int n,mp,mf,ms,mv;
	static Food[] food;
	static boolean[] v;
	static StringTokenizer st;
	static int min_price;
	static boolean flag;
	static ArrayList<String> food_idx;
	static class Food{
		int p,f,s,v,c;
		public Food(int p, int f, int s, int v, int c) {
			super();
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		min_price=Integer.MAX_VALUE;
		flag = false;
		food = new Food[n];
		food_idx= new ArrayList<String>();
		v = new boolean[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			food[i]= new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		recur(0);	
		//조건이 하나도 만족하지 않으면
		if(!flag) {
			System.out.println(-1);
		}else {
			Collections.sort(food_idx); //오름차순 정렬
			System.out.println(min_price);
			System.out.println(food_idx.get(0));
		}
	}
	//부분집합 로직
	private static void recur(int idx) {
		//basis part
		if(idx ==v.length) {
			if(allFalse()) return;
			if(cal()) {
				flag=true;
			}
			return;
		}
		//inductive part
		v[idx]=true;
		recur(idx+1);
		v[idx]=false;
		recur(idx+1);
		
	}
	private static boolean cal() {
		int sum_p=0;
		int sum_f=0;
		int sum_s=0;
		int sum_v=0;
		int sum_c=0;
		for (int i = 0; i < v.length; i++) {
			if(v[i]) {
				sum_p+=food[i].p;
				sum_f+=food[i].f;
				sum_s+=food[i].s;
				sum_v+=food[i].v;
				sum_c+=food[i].c;
			}
		}
		//조건이 만족하면 가격합과 재료 index update 
		if(sum_p>=mp&&sum_f>=mf&&sum_s>=ms&&sum_v>=mv) {
			if(min_price>=sum_c) {
				if(min_price>sum_c) {
					food_idx.clear();
				}
				min_price=sum_c;
				String str ="";
				for(int i=0;i<v.length;i++) {
					if(v[i]) {
						str+=Integer.toString(i+1);
						str+=" ";
					}
				}
				food_idx.add(str);
			}
			
			
			return true;
		}
		
		return false;
	}
	private static boolean allFalse() {
		for (int i = 0; i < v.length; i++) {
			if(v[i]==true) return false;
		}
		return true;
	}
}
