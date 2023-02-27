package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    static final int SPACE = 0;
    static final int WALL = 1;
    static final int WATER = 2;
    static final boolean LEFT = false;
    static final boolean RIGHT = true;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int h, w, ans = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        //초기화
        st = new StringTokenizer(br.readLine());
        h = stoi(st.nextToken());
        w = stoi(st.nextToken());
        map = new int[h][w];
        //맵 생성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            int height = stoi(st.nextToken());
            int idx = h - 1;
            for (int j = 0; j < height; j++) {
                map[idx--][i] = WALL;
            }
        }

        //로직
        for (int i = h - 1; i >= 0 ; i--) {
            for (int j = 0; j < w; j++) {
                //현재 위치가 돌이면 종료
                if (map[i][j] == WALL) continue;
                //현재 위치가 물이라면 이미 탐색된 것이므로 종료
                if (map[i][j] == WATER) continue;
                //현재 위치가 빈 공간이라면
                if (map[i][j] == SPACE) {
                    //좌, 우를 탐색하면서 물이 고일 수 있는 공간인지 탐색한다.
                    if (searchWall(i, j, LEFT) && searchWall(i, j, RIGHT)) {
                        //양 옆이 다 돌이라 고일 수 있다면 물을 생성한다.
                        ans += createWater(i, j);
                    }
                }
            }
        }

        //정답 출력
        System.out.println(ans);
    }
    static boolean searchWall(int x, int y, boolean dir) {
            while (true) {
                //현재 좌표를 벗어난다면 벽이 없는것 이므로 물이 고일 수 없다.
                if (y < 0 || y >= w) break;
                //돌이라면 고일 수 있다는 가능성이 있으므로 true를 리턴
                if (map[x][y] == WALL) return true;
                if (dir == LEFT) y--;
                if (dir == RIGHT) y++;
            }

        return false;
    }

    static int createWater(int x, int y) {
        int cnt = 0;
        //현재 위치 기준 왼쪽 탐색
        int r = x;
        int c = y;
        while (true) {
            if (map[r][c] != SPACE) break;
            if (map[r][c] == SPACE) {
                map[r][c--] = WATER;
                cnt++;
            }
        }
        //현재 위치 기준 오른쪽 탐색
        r = x;
        c = y;
        while (true) {
            if (map[r][c] != SPACE) break;
            if (map[r][c] == SPACE) {
                map[r][c++] = WATER;
                cnt++;
            }
        }

        return cnt;
    }
    static int stoi(String s) {return Integer.parseInt(s);}
}
