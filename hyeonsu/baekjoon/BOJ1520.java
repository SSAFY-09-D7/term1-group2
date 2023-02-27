package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1520 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        dp = new long[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for (long[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        dp[0][0] =  1;
        System.out.println(top_down(n - 1, m - 1, -100));
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static long top_down(int x, int y, int dir) {
        if (x == 0 && y == 0) return dp[0][0];
        if (dp[x][y] != -1) return dp[x][y];

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;


            if (nx < 0 || nx >= n || ny < 0 || ny >= m || i == dir) continue;

            if (map[nx][ny] > map[x][y]) {
                int nextDir = -1;
                if (i == 0) nextDir = 1;
                if (i == 1) nextDir = 0;
                if (i == 2) nextDir = 3;
                if (i == 3) nextDir = 2;
                sum += top_down(nx, ny, nextDir);
            }
        }

        return dp[x][y] = sum;
    }

}
