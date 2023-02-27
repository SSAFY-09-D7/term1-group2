package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ3109 {
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int R, C;
    static int ans = 0;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        //로직
        for (int i = 0; i < R; i++) {
            solve(i, 0);
        }

        System.out.println(ans);
    }

    static boolean solve(int r, int c) {
        if (c == C - 1) {
            ans++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (map[nr][nc] != '.') continue;
            map[nr][nc] = '+';
            if (solve(nr, nc)) return true;
        }

        return false;
    }
}
