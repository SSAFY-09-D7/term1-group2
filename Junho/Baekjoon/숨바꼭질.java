

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {

	static int n,k,cnt;
	static StringTokenizer st;
	static boolean[] v;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		k= Integer.parseInt(st.nextToken());
		v = new boolean[100001];
		bfs(n);
		System.out.println(cnt);
	}
	private static void bfs(int x) {
		Queue<Integer> q = new LinkedList<Integer>();
		v[x]=true;
		q.offer(x);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				int now = q.poll();
				if(now==k) {
					
					return;
				}
				for(int i=0;i<3;i++) {
					if(i==0) {
						//2*X 순간이동
						if(2*now<=100000&&!v[2*now]) {
							v[2*now]=true;
							q.offer(now*2);
						}
						//X-1 순간이동
					}else if(i==1) {
						if(now-1>=0&&!v[now-1]) {
							v[now-1]=true;
							q.offer(now-1);
						}
						//X+1 순간이동
					}else {
						if(now+1<=100000&&!v[now+1]) {
							v[now+1]=true;
							q.offer(now+1);
						}
					}
				}
			}
			cnt++;
		}
		
	}
}
