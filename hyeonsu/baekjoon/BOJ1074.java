package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1074 {

    static int[] dx = {0, 0, 1, 0};
    static int[] dy = {0, 1, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, r, c, len, cnt, ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        len = (int) Math.pow(2, n);

        //로직
        recur(len, 0, 0);

        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void recur(int len, int x, int y) {
        if (len == 2) {
            for (int i = 0; i < 4; i++) {
                x += dx[i];
                y += dy[i];

                if (x == r && y == c) {
                    ans = cnt;
                    return;
                }
                cnt++;
            }
            return;
        }

        int nextLen = len / 2;
        int sumCnt = (int) Math.pow((double)len / 2, 2);

        if (x + nextLen - 1 >= r && y + nextLen - 1 >= c) {
            recur(nextLen, x, y);
        }
        else if (x + nextLen - 1 >= r) {
            cnt += sumCnt;
            recur(nextLen, x, y + nextLen);
        }
        else if (y + nextLen - 1 >= c) {
            cnt += sumCnt * 2;
            recur(nextLen, x + nextLen, y);
        }
        else {
            cnt += sumCnt * 3;
            recur(nextLen, x + nextLen, y + nextLen);
        }
    }
}
