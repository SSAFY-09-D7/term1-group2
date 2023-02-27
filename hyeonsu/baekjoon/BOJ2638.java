package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2638 {
    static final int STUCKED_AIR = 0;
    static final int CHEESE = 1;
    static final int NOT_STUCKED_AIR = 2;
    static final int MARKED = 3;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] checked;
    static int x, y, cheeseCnt = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        x = stoi(st.nextToken());
        y = stoi(st.nextToken());
        map = new int[x][y];

        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                int cur = stoi(st.nextToken());
                if (cur == CHEESE) {
                    cheeseCnt++;
                    map[i][j] = cur;
                }
            }
        }

        //로직
        int hour = 0;
        while (cheeseCnt > 0) {
            checked = new boolean[x][y];
            //순환하는 공기에 대해서 업데이트 해준다.
            bfsAir(0, 0);
            bfsAir(0, y - 1);
            bfsAir(x -1, 0);
            bfsAir(x - 1, y - 1);

            //각 격자별로 순회하면서 치즈라면 bfs를 돌린다.
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (map[i][j] != CHEESE || checked[i][j]) continue;
                    bfsCheese(i, j, hour);
                }
            }
            hour++;
        }
        System.out.println(hour);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void bfsAir(int startX, int startY) {
        boolean[][] checked = new boolean[x][y];
        Queue<Point> aq = new LinkedList<>();
        aq.add(new Point(startX, startY));
        checked[startX][startY] = true;

        while (!aq.isEmpty()) {
            Point cur = aq.poll();
            //순회
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= x || ny < 0 || ny >= y) continue;
                if (map[nx][ny] == CHEESE || checked[nx][ny]) continue;

                map[nx][ny] = NOT_STUCKED_AIR;
                checked[nx][ny] = true;
                aq.add(new Point(nx, ny));
            }
        }
    }

    static void bfsCheese(int startX, int startY, int hour) {
        //시작값을 넣어준다.
        Queue<Point> pointQ = new LinkedList<>();
        pointQ.add(new Point(startX, startY));

        //bfs 실행
        while (!pointQ.isEmpty()) {
            Point cur = pointQ.poll();
            //녹는 치즈인가?
            if (shouldMeltedInHour(cur.x, cur.y)) {
                map[cur.x][cur.y] = STUCKED_AIR;
                cheeseCnt--;
                checked[cur.x][cur.y] = true;
                continue;
            }
            //인접한 곳 순회
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur.x;
                int ny = dx[i] + cur.y;

                //방문했거나 좌표범위를 넘은경우 처리
                if (nx < 0 || nx >= x || ny < 0 || ny >= y) continue;
                if (checked[nx][ny]) continue;

                //치즈라면 큐에 넣고 체크인
                if (map[nx][ny] == CHEESE) {
                    pointQ.add(new Point(nx, ny));
                    checked[nx][ny] = true;
                }
            }
        }
    }

    static boolean shouldMeltedInHour(int r, int c) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nr = dx[i] + r;
            int nc = dy[i] + c;

            if (nr < 0 || nr >= x || nc < 0 || nc >= y) continue;
            if (map[nr][nc] == NOT_STUCKED_AIR) cnt++;
        }

        return cnt >= 2;
    }

}
