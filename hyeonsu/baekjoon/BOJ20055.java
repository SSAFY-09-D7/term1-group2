package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ20055 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k, stage = 1;
    static int[][] belt;
    static boolean[] isRobotExist;

    public static void main(String[] args) throws IOException {
        //초기화
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        belt = new int[2][n];
        isRobotExist = new boolean[n];
        //벨트 생성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            belt[0][i] = stoi(st.nextToken());
        }
        for (int i = n - 1; i >= 0; i--) {
            belt[1][i] = stoi(st.nextToken());
        }

        //로직
        while (true) {
            rotate();
            move();
            putOn();
            if (canExit(countZero())) break;
            stage++;
        }

        //정답 출력
        System.out.println(stage);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void rotate() {
        //벨트 배열과 로봇 배열 모두 한칸 씩 회전한다.
        int tmpN_1 = belt[0][n - 1];
        int tmpN_2 = belt[1][0];

        //상단 벨트
        for (int i = n - 1; i > 0; i--) {
            belt[0][i] = belt[0][i - 1];
            isRobotExist[i] = isRobotExist[i - 1];
            isRobotExist[i - 1] = false;
        }
        //하단 벨트
        for (int i = 0; i < n - 1; i++) {
            belt[1][i] = belt[1][i + 1];
        }

        belt[1][n - 1] = tmpN_1;
        belt[0][0] = tmpN_2;
        isRobotExist[n - 1] = false;
    }

    static void move() {
        //가장 먼저 벨트에 올라간 로봇부터 차례대로 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
        for (int i = n - 1; i > 0; i--) {
            if (isRobotExist[i - 1]) {
                if (belt[0][i] > 0 && !isRobotExist[i]) {
                    isRobotExist[i] = true;
                    isRobotExist[i - 1] = false;
                    belt[0][i]--;
                }
            }
        }
        isRobotExist[n - 1] = false;
    }

    static void putOn() {
        //올리는 위치에 내구도가 1 이상인 경우 올린다. 이 때 내구도는 1 감소한다.
        if (!isRobotExist[0] && belt[0][0] > 0) {
            isRobotExist[0] = true;
            belt[0][0]--;
        }
    }

    static boolean canExit(int cnt) {
        return cnt >= k;
    }

    static int countZero() {
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (belt[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }
}
