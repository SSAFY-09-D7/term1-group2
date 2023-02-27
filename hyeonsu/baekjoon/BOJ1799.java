package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1799 {
    static final int BISHOP = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, ans = Integer.MIN_VALUE;
    static int blackCnt = Integer.MIN_VALUE, whiteCnt = Integer.MIN_VALUE;
    static int[][] map;
    static List<Pair>[] blackDiagonals, whiteDiagonals;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        map = new int[n][n];
        blackDiagonals = new ArrayList[n];
        whiteDiagonals = new ArrayList[n - 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = stoi(st.nextToken());
            }
            blackDiagonals[i] = new ArrayList<>();
            if (i < n - 1) whiteDiagonals[i] = new ArrayList<>();
        }
        //대각선 인덱스 구해놓기
        createDiagonal(true);
        createDiagonal(false);

//        for (List<Pair> cur : blackDiagonals) {
//            System.out.println(cur.toString());
//        }
//        System.out.println("========================");
//        for (List<Pair> cur : whiteDiagonals) {
//            System.out.println(cur.toString());
//        }

        //로직
        solve(blackDiagonals, true, blackDiagonals.length, 0, 0);
        solve(whiteDiagonals, false, whiteDiagonals.length, 0, 0);
        ans = blackCnt + whiteCnt;

        //정답 출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(List<Pair>[] arr, boolean isBlack, int len, int k, int cnt) {
        if (k == len) {
            if (isBlack) blackCnt = Math.max(blackCnt, cnt);
            else whiteCnt = Math.max(whiteCnt, cnt);
            return;
        }

        //대각선에서 한군데 씩 보면서 놓을 수 있는지 확인 후 놓는다.
        for (int i = 0; i < arr[k].size(); i++) {
            int r = arr[k].get(i).x;
            int c = arr[k].get(i).y;
            if (map[r][c] == 1 && check(r, c)) {
                map[r][c] = BISHOP;
                solve(arr, isBlack, len, k + 1, cnt + 1);
                map[r][c] = 1;
            } else {
                solve(arr, isBlack, len, k + 1, cnt);
            }
        }
    }

    private static boolean check(int x, int y) {
        //우상만 체크해주면 된다.
        while (true) {
            if (x < 0 || x >= n || y < 0 || y >= n) break;
            if (map[x][y] == BISHOP) return false;
            x--;
            y++;
        }
        return true;
    }

    static void createDiagonal(boolean status) {
        if (status) {
            int idx = 0;
            for (int i = n - 1; i >= 0; i -= 2) {
                int x = 0;
                int y = i;
                while (true) {
                    if (x < 0 || x >= n || y < 0 || y >= n) break;
                    blackDiagonals[idx].add(new Pair(x, y));
                    x++;
                    y++;
                }
                idx++;
            }
            for (int i = n % 2 == 0 ? 1 : 2; i < n; i += 2) {
                int x = i;
                int y = 0;
                while (true) {
                    if (x < 0 || x >= n || y < 0 || y >= n) break;
                    blackDiagonals[idx].add(new Pair(x, y));
                    x++;
                    y++;
                }
                idx++;
            }
        } else {
            int idx = 0;
            for (int i = n - 2; i >= 0; i -= 2) {
                int x = 0;
                int y = i;
                while (true) {
                    if (x < 0 || x >= n || y < 0 || y >= n) break;
                    whiteDiagonals[idx].add(new Pair(x, y));
                    x++;
                    y++;
                }
                idx++;
            }
            for (int i = n % 2 == 0 ? 2 : 1; i < n; i += 2) {
                int x = i;
                int y = 0;
                while (true) {
                    if (x < 0 || x >= n || y < 0 || y >= n) break;
                    whiteDiagonals[idx].add(new Pair(x, y));
                    x++;
                    y++;
                }
                idx++;
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

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
