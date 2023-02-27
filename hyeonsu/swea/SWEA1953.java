package swea;

import java.io.*;
import java.util.*;

public class SWEA1953 {
    static final int[][] dx = {{0}, {-1, 1, 0, 0}, {-1, 1, 0, 0}, {0, 0, 0, 0}, {-1, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {-1, 0, 0, 0}};
    static final int[][] dy = {{0}, {0, 0, -1, 1}, {0, 0, 0, 0}, {0, 0, -1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, -1, 0}, {0, 0, -1, 0}};

    static final String[] check = {"1256", "1247", "1345", "1367"};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n, m, r, c, l, ans, tNum = 1;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            ans = 0;
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            r = stoi(st.nextToken());
            c = stoi(st.nextToken());
            l = stoi(st.nextToken());
            map = new int[n][m];
            v = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            if (l == 1) ans = 1;
            else bfs(r, c);

            bw.write("#" + tNum++ + " " + ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.cnt == l) break;

            int[] dxx = dx[map[cur.x][cur.y]];
            int[] dyy = dy[map[cur.x][cur.y]];

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dxx[i];
                int ny = cur.y + dyy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (nx == cur.x && ny == cur.y) continue;
                if (map[nx][ny] == 0 || v[nx][ny]) continue;
                if (!check[i].contains(String.valueOf(map[nx][ny]))) continue;

                ans++;
                v[nx][ny] = true;
                q.add(new Point(nx, ny, cur.cnt + 1));
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            super();
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}

