import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치킨배달 {
	static int[][] city;
	static ArrayList<index> c_h;
	static ArrayList<index> home;
	static int n,m,ch_dis;
	static StringTokenizer st;
	static index[] sel;
	static int min_dis;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		city = new int[n+1][n+1];
		c_h = new ArrayList<index>();
		home = new ArrayList<index>();
		min_dis=Integer.MAX_VALUE;
		for(int i=1;i<n+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<n+1;j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j]==2) {
					c_h.add(new index(i, j));
				}else if(city[i][j]==1) {
					home.add(new index(i, j));
				}
			}
		}
		for(int k=1;k<=m;k++) {
			sel= new index[k];
			recur(0,0);
//			System.out.println(min_dis);
		}
		System.out.println(min_dis);
	}
	private static void recur(int idx, int s) {
		if(idx==sel.length) {
//			System.out.println(Arrays.toString(sel));
			
			ch_dis=0;
			for (int i = 0; i < home.size(); i++) {
				int min = Integer.MAX_VALUE;
				int dis=0;
				int x = home.get(i).x;
				int y= home.get(i).y;
				for (int j = 0; j < sel.length; j++) {
					dis = Math.abs(x-sel[j].x)+Math.abs(y-sel[j].y);
					if(min>dis) min=dis;
				}
				ch_dis+=min;
//				System.out.println(ch_dis);
			}
			if(min_dis>ch_dis) min_dis=ch_dis;
			return;
		}
		for(int i=s;i<c_h.size();i++) {
			sel[idx]=c_h.get(i);
			recur(idx+1, i+1);
		}
		
	}
}
class index{
	int x,y;

	public index(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "chicken [x=" + x + ", y=" + y + "]";
	}
	
	
}

