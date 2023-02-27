package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ6987 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static Team[][] resultTable = new Team[4][6];
    static boolean[] isValidTable = new boolean[4];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                int win = stoi(st.nextToken());
                int draw = stoi(st.nextToken());
                int lose = stoi(st.nextToken());
                resultTable[i][j] = new Team(win, draw, lose);
            }
        }

        //로직
        Team[] tmpTeam = new Team[6];
        for (int j = 0; j < 6; j++) {
            tmpTeam[j] = new Team(0, 0, 0);
        }
        solve(tmpTeam, 0, 1, 0);
        for (int i = 0; i < 4; i++) {
            bw.write(isValidTable[i] ? "1 " : "0 ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(Team[] table, int curTeam, int otherTeam, int matchCnt) {
        if (matchCnt == 15) {
            for (int i = 0; i < 4; i++) {
                boolean flag = true;
                for (int j = 0; j < 6; j++) {
                    if (table[j].win != resultTable[i][j].win || table[j].draw != resultTable[i][j].draw || table[j].lose != resultTable[i][j].lose) {
                        flag = false;
                        break;
                    }
                }
                if (flag) isValidTable[i] = true;
            }
            return;
        }

        //각각의 라운드에서 이기거나 비기거나 지는 경우의 수가 있다.
        table[curTeam].win++;
        table[otherTeam].lose++;
        if (otherTeam == 5) solve(table, curTeam + 1, curTeam + 2, matchCnt + 1);
        else solve(table, curTeam, otherTeam + 1, matchCnt + 1);
        table[curTeam].win--;
        table[otherTeam].lose--;
        table[curTeam].draw++;
        table[otherTeam].draw++;
        if (otherTeam == 5) solve(table, curTeam + 1, curTeam + 2, matchCnt + 1);
        else solve(table, curTeam, otherTeam + 1, matchCnt + 1);
        table[curTeam].draw--;
        table[otherTeam].draw--;
        table[curTeam].lose++;
        table[otherTeam].win++;
        if (otherTeam == 5) solve(table, curTeam + 1, curTeam + 2, matchCnt + 1);
        else solve(table, curTeam, otherTeam + 1, matchCnt + 1);
        table[curTeam].lose--;
        table[otherTeam].win--;
    }

    static class Team {
        int win;
        int draw;
        int lose;

        public Team(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }
}
