package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17136 {

    static final int N = 10;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] map = new int[N][N];
    static int[] paperBox = {0, 5, 5, 5, 5, 5};
    static int min = 0x7fffffff;

    static List<Point> bq  = new ArrayList<>();
    static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        //맵 생성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 1) {
                    bq.add(new Point(i, j));
                }
            }
        }
        checked = new boolean[N][N];

        //로직
        backtacking(0, 0);

        System.out.println(min == 0x7fffffff ? -1 : min);
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static void backtacking(int cnt, int idx) {
        if (idx == bq.size()) {
            min = Math.min(min, cnt);
            return;
        }

        if (cnt >= min) return;

        Point cur = bq.get(idx);
        if (!checked[cur.x][cur.y]) {
            for (int j = 5; j >= 1; j--) {
                if (paperBox[j] > 0 && check(j, cur.x, cur.y)) {
                    attach(j, cur.x, cur.y);
                    paperBox[j]--;
                    backtacking(cnt + 1, idx + 1);
                    remove(j, cur.x, cur.y);
                    paperBox[j]++;
                }
            }
        } else {
            backtacking(cnt, idx + 1);
        }
    }

    static boolean check(int n, int x, int y) {

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (i < 0 || i >= N || j < 0 || j >= N) return false;
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void attach(int n, int x, int y) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                map[i][j] = 2;
                checked[i][j] = true;
            }
        }
    }

    static void remove(int n, int x, int y) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                map[i][j] = 1;
                checked[i][j] = false;
            }
        }
    }
}
