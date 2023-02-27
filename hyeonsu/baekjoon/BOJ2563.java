package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2563 {
    static final int SIZE = 1002;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] colorPaperCnt;
    static StringTokenizer st;

    static int n;
    static int[][] map = new int[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        colorPaperCnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int startY = stoi(st.nextToken());
            int startX = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            int x = stoi(st.nextToken());

            //색종이 붙이기
            for (int j = startX; j < startX + x; j++) {
                for (int k = startY; k < startY + y; k++) {
                    map[j][k] = i;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                colorPaperCnt[map[i][j]]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(colorPaperCnt[i] + "\n");
        }

        bw.flush();
        bw.close();
    }


    static int stoi(String s) {return Integer.parseInt(s);}
}
