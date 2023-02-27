package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17276 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int t;
    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            //배열 초기화
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            boolean turnRight = d > 0;
            d = Math.abs(d) / 45;
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = stoi(st.nextToken());
                }
            }

            //로직
            while (d-- > 0) {
                arr = turnRight ? rotateRight(arr, n) : rotateLeft(arr, n);
            }

            //배열 입력
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(arr[i][j] + " ");
                }
                bw.write("\n");
            }

        }

        //정답 출력
        bw.flush();
        bw.close();
    }

    static int[][] rotateRight(int[][] arr, int n) {
        int[][] tmp = createArr(arr, n);

        //첫 대각선 -> 수직선
        for (int j = 0; j < n; j++) {
            tmp[j][n / 2] = arr[j][j];
        }
        //수직선 -> 부대각선
        for (int j = 0; j < n; j++) {
            tmp[j][n-j-1] = arr[j][n / 2];
        }
        //부대각선 -> 수평선
        for (int j = 0; j < n; j++) {
            tmp[n / 2][j] = arr[n-j-1][j];
        }
        //수평선 -> 첫 대각선
        for (int j = 0; j < n; j++) {
            tmp[j][j] = arr[n / 2][j];
        }
        return tmp;
    }

    static int[][] rotateLeft(int[][] arr, int n) {
        int[][] tmp = createArr(arr, n);

        //첫 대각선 -> 수평선
        for (int j = 0; j < n; j++) {
            tmp[n / 2][j] = arr[j][j];
        }
        //수평선 -> 부대각선
        for (int j = 0; j < n; j++) {
            tmp[n-j-1][j] = arr[n / 2][j];
        }
        //부대각선 -> 수직선
        for (int j = 0; j < n; j++) {
            tmp[n-j-1][n / 2] = arr[n-j-1][j];
        }
        //수직선 -> 첫 대각선
        for (int j = 0; j < n; j++) {
            tmp[n-j-1][n-j-1] = arr[n-j-1][n / 2];
        }
        return tmp;
    }

    static int[][] createArr(int[][] arr, int n) {
        int[][] ret = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = arr[i][j];
            }
        }

        return ret;
    }
    static int stoi(String s) {
        return Integer.parseInt(s);}
}
