package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2206 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static final int NONE_CHECK = 0;
    static final int NONE_COMPLETE_CHECK = 1;
    static final int COMPLETE_CHECK = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, min = Integer.MAX_VALUE;
    static boolean[][] map;
    static int[][] v;
    static Queue<Point> q = new ArrayDeque<>(1000001);

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new boolean[n][m];
        v = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0' == 1;
            }
        }

        //로직
        bfs();

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void bfs() {
        q.add(new Point(0, 0, false, 1));
        v[0][0] = COMPLETE_CHECK;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.x == n - 1 && cur.y == m - 1) {
                min = cur.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                //좌표를 벗어난 경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                //이미 방문한 경우
                if (v[nx][ny] == COMPLETE_CHECK) continue;
                // 벽을 뚫은 경로 이면서 이미 간 경우
                if (v[nx][ny] == NONE_COMPLETE_CHECK && cur.isBroken) continue;
                //벽이고 이미 한번 뚫은 경우는 뚫고가지 못한다.
                if (map[nx][ny] && cur.isBroken) continue;
                //벽이면서 뚫은 적이 없는 경우 뚫고간다.
                if (map[nx][ny] && !cur.isBroken) {
                    v[nx][ny] = COMPLETE_CHECK;
                    q.add(new Point(nx, ny, true, cur.cnt + 1));
                }
                //벽이 아닌 경우 그냥 간다.
                if (!map[nx][ny]) {
                    v[nx][ny] = cur.isBroken ? NONE_COMPLETE_CHECK : COMPLETE_CHECK;
                    q.add(new Point(nx, ny, cur.isBroken, cur.cnt + 1));
                }
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class Point{
    int x;
    int y;
    boolean isBroken;
    int cnt;

    public Point(int x, int y, boolean isBroken, int cnt) {
        this.x = x;
        this.y = y;
        this.isBroken = isBroken;
        this.cnt = cnt;
    }
}
