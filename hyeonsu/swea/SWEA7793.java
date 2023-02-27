package swea;

import java.io.*;
import java.util.*;

public class SWEA7793 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static List<Pair> devilPoint;
    static int t, n, m, cnt, tNum = 1;
    static char[][] map;
    static boolean[][] v;

    static Suyeon suyeon;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {

            //초기화
            cnt = 0;
            devilPoint = new ArrayList<>();
            suyeon = null;
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            map = new char[n][m];
            v = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                char[] a = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    map[i][j] = a[j];
                    if (map[i][j] == '*') devilPoint.add(new Pair(i, j));
                    if (map[i][j] == 'S') suyeon = new Suyeon(i, j, 0);
                }
            }

            //로직
            String ans = bfs() ? String.valueOf(cnt) : "GAME OVER";

            //정답 입력
            bw.write("#" + tNum++ + " " + ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static boolean bfs() {
        boolean flag = false;
        //악마가 먼저 퍼저야 하니 먼저 넣어준다.
        Queue<Pair> q = new LinkedList<>(devilPoint);
        q.add(suyeon);

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == 'X' || v[nx][ny]) continue;
                if (map[nx][ny] == '*') continue;
                if (!(cur instanceof Suyeon) && map[nx][ny] == 'D' && map[cur.x][cur.y] == '*') continue;

                v[nx][ny] = true;
                //수연이가 여신님을 만나는 순간 종료한다.
                if (cur instanceof Suyeon && map[nx][ny] == 'D') {
                    cnt = ((Suyeon) cur).cnt + 1;
                    flag = true;
                    break;
                }

                if (cur instanceof Suyeon) {
                    q.add(new Suyeon(nx, ny, ((Suyeon) cur).cnt + 1));
                } else {
                    map[nx][ny] = '*';
                    q.add(new Pair(nx, ny));
                }
            }
        }

        return flag;
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Suyeon extends Pair {
        int cnt;

        public Suyeon(int x, int y, int cnt) {
            super(x, y);
            this.cnt = cnt;
        }
    }

}

