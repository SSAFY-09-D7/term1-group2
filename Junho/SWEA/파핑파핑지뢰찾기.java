import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 파핑파핑지뢰찾기 {
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	static char[][] map;
	static boolean[][] v;
	static int n,cnt;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(br.readLine());
			map = new char[n][n];
			cnt=0;
			v= new boolean[n][n];
			for(int i=0;i<n;i++) {
				String str = br.readLine();
				for(int j=0;j<n;j++) {
					map[i][j]=str.charAt(j);
				}
			}
			near_mine();
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j]=='0'&&!v[i][j]) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j]!='*'&&!v[i][j]) {
						cnt++;
					}
				}
			}
//			System.out.println(Arrays.deepToString(map));
			System.out.println("#"+tc+" "+cnt);
		}
	}
	private static void bfs(int x, int y) {
		Queue<Mine> q = new LinkedList<Mine>();
		v[x][y]=true;
		q.offer(new Mine(x, y));
		
		while(!q.isEmpty()) {
			Mine p = q.poll();
			for(int i=0;i<8;i++) {
				int nx = p.x+dx[i];
				int ny = p.y+dy[i];
				if(0<=nx&&nx<n&&0<=ny&&ny<n&&!v[nx][ny]&&map[nx][ny]!='*') {
					v[nx][ny]=true;
					if(map[nx][ny]=='0') {
						q.offer(new Mine(nx, ny));
					}
				}
			}
		}
	}
	private static void near_mine() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]=='.') {
					int mine_cnt=0;
					for(int k=0;k<8;k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(0<=nx&&nx<n&&0<=ny&&ny<n&&map[nx][ny]=='*') {
							mine_cnt++;
						}
					}
					map[i][j]=(char)(mine_cnt+'0');
				}
			}
		}
	}
}
class Mine{
	int x,y;

	public Mine(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
