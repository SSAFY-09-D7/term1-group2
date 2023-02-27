package swea;

import java.io.*;
import java.util.*;

public class SWEA1949 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n, k, max, ans, tNum = 1;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            ans = Integer.MIN_VALUE;
            max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            k = stoi(st.nextToken());
            map = new int[n][n];
            v = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = stoi(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            //로직
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (max == map[i][j]) {
                        v[i][j] = true;
                        dfs(i, j, 1, false);
                        v[i][j] = false;
                    }
                }
            }

            //정답 입력
            bw.write("#" + tNum++ + " " + ans + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void dfs(int x, int y, int cnt, boolean f) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (v[nx][ny]) continue;

            //나무를 안잘라도 되는 경우
            if (map[x][y] > map[nx][ny]) {
                v[nx][ny] = true;
                dfs(nx, ny, cnt + 1, f);
                v[nx][ny] = false;
            }
            //나무를 잘라야 하는 경우
            for (int j = 1; j <= k; j++) {
                if (!f && map[x][y] > map[nx][ny] - j) {
                    int tmp = map[nx][ny];
                    map[nx][ny] -= j;
                    if (map[nx][ny] < 0) map[nx][ny] = 0;
                    v[nx][ny] = true;
                    dfs(nx, ny, cnt + 1, true);
                    v[nx][ny] = false;
                    map[nx][ny] = tmp;
                }
            }
        }


        ans = Math.max(ans,  cnt);
    }

}

