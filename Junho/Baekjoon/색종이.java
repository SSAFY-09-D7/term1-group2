import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 색종이 {

	static Point1[] paper;
	static int[][] map;
	static  int n;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[101][101];
		paper = new Point1[n];
		for(int i=0;i<n;i++) {
			st  = new StringTokenizer(br.readLine());
			paper[i]= new Point1(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int t=0;t<n;t++) {
			
			for(int i=paper[t].x; i<paper[t].x+paper[t].w;i++ ) {
				for(int j=paper[t].y;j<paper[t].y+paper[t].h;j++) {
					map[i][j]=t+1;
				}
			}
		}

		for (int t = 1; t < n + 1; t++) {
			int count=0;
			for (int i = 0; i < 101; i++) {
				for (int j = 0; j < 101; j++) {
					if(map[i][j]==t) count++;
				}
			}
			System.out.println(count);
		}
	}

}

class Point1{
	int x;
	int y;
	int w;
	int h;
	public Point1(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
}