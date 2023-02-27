package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ14712 {
    static int[][] dx = {{0, -1, -1}, {-1, -1, 0}, {0, 1, 1}, {0, 1, 1}};
    static int[][] dy = {{-1, -1, 0}, {0, 1, 1}, {-1, -1, 0}, {1, 1, 0}};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] map;

    static int n, m, cnt = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new boolean[n][m];

        //로직
        play(0, 0);

        //정답 출력
        System.out.println(cnt + 1);
    }

    static void play(int x, int y) {
        if (x == n) {
            return;
        }

        //놓을 수 있는 경우
        if (check(x, y)) {
            cnt++;
            map[x][y] = true;
            if (y == m - 1) {
                play(x + 1, 0);
            } else {
                play(x, y + 1);
            }
        }

        //놓지 않는 경우
        map[x][y] = false;
        if (y == m - 1) {
            play(x + 1, 0);
        } else {
            play(x, y + 1);
        }
        map[x][y] = false;
    }

    /*
    해당 위치에 넴모를 놓았을 때  주변에 2*2가 되지 않는지 체크하는 함수
     */
    static boolean check(int x, int y) {
        //4곳 중 한곳이라도 채워진다면 false
        for (int i = 0; i < 4; i++) {
            boolean flag = false;
            for (int j = 0; j < 3; j++) {
                int nx = dx[i][j] + x;
                int ny = dy[i][j] + y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    flag = true;
                    continue;
                }
                if (!map[nx][ny]) flag = true;
            }
            if (!flag) return false;
        }
        return true;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
