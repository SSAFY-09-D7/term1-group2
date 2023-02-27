package baekjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ10026 {
    static final int NONE = 0;
    static final int RED = 1;
    static final int GREEN = 2;
    static final int BLUE = 3;
    static final int RED_GREEN_WEAKNESS = 4;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static char[][] map;
    static int[][] v;

    public static void main(String[] args) throws IOException {
        //초기화
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        v = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = str.charAt(j);
                if (c == 'R') map[i][j] = RED;
                else if (c == 'G') map[i][j] = GREEN;
                else if (c == 'B') map[i][j] = BLUE;
            }
        }

        //로직
        //일반인 기준으로 구역을 새준다.
        int cnt = 0;
        int blueCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] == NONE) {
                    cnt++;
                    if (map[i][j] == BLUE) blueCnt++;
                    bfs(i, j, map[i][j], false);
                }
            }
        }
        bw.write(cnt + " ");

        cnt = 0;
        //적록색약 기준으로 구역을 다시 나눠준다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] == RED || v[i][j] == GREEN) {
                    cnt++;
                    bfs(i, j, RED_GREEN_WEAKNESS, true);
                }
            }
        }
        bw.write((cnt + blueCnt) + "\n");
        //정답 출력
        bw.flush();
        bw.close();
    }

    static void bfs(int r, int c, int type, boolean isWeakness) {
        Queue<Pair> q = new ArrayDeque<>();
        v[r][c] = type;
        q.add(new Pair(r, c));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = dx[i] + cur.x;
                int nc = dy[i] + cur.y;

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                //이미 방문한 노드
                if (v[nr][nc] == type) continue;
                //다른 타입의 구역 (일반인 기준)
                if (!isWeakness && map[nr][nc] != type) continue;
                //파란 구역은 볼 필요 없음 (적록색약 기준)
                if (isWeakness && map[nr][nc] == BLUE) continue;

                v[nr][nc] = type;
                q.add(new Pair(nr, nc));
            }
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
