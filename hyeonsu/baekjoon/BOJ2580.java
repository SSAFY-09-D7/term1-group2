package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2580 {
    static final int N = 9;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static Pair[] zeroP;
    static int[][] map = new int[N][N];
    static int zeroCnt = 0;
    static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zeroCnt++;
            }
        }
        zeroP = new Pair[zeroCnt];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    zeroP[idx++] = new Pair(i, j);
                }
            }
        }
        //로직
        solve(0);

        bw.flush();
        bw.close();
    }

    static void solve(int depth) throws IOException {
        if (depth == zeroCnt) {
            isEnd = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bw.write(map[i][j] + " ");
                }
                bw.write("\n");
            }
            return;
        }
        if (isEnd) return;
        boolean[] numbers = new boolean[10];
        //해당 칸이 어디 3x3 안에 있는지 확인하고 있는 숫자 체크
        int startX = zeroP[depth].x / 3 * 3;
        int startY = zeroP[depth].y / 3 * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (map[i][j] == 0) continue;
                numbers[map[i][j]] = true;
            }
        }
        //사방 탐색
        for (int i = 0; i < 4; i++) {
            int nx = zeroP[depth].x + dx[i];
            int ny = zeroP[depth].y + dy[i];

            while (true) {
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                if (map[nx][ny] != 0) numbers[map[nx][ny]] = true;
                nx += dx[i];
                ny += dy[i];
            }
        }

        //1 ~ 9 중에서 갈 수 있는 곳은 간다.
        for (int i = 1; i <= N; i++) {
            if (!numbers[i]) {
                map[zeroP[depth].x][zeroP[depth].y] = i;
                solve(depth + 1);
                map[zeroP[depth].x][zeroP[depth].y] = 0;
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
