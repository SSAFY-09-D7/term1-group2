package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15787 {
    static final int SEAT_NUMBER = 20;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int ans = 0;
    static int n, m, command, trainNum, seatNum;
    static int[][] trains;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        trains = new int[n][SEAT_NUMBER];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            command = stoi(st.nextToken());
            trainNum = stoi(st.nextToken()) - 1;
            if (command < 3) seatNum = stoi(st.nextToken()) - 1;
            generate();
        }

        //은하수 건너기
        Set<String> dupSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < SEAT_NUMBER; j++) {
                sb.append(trains[i][j]);
            }
            dupSet.add(sb.toString());
        }

        System.out.println(dupSet.size());
    }

   static int stoi(String s) {return Integer.parseInt(s);}

    static void generate() {
        if (command == 1) {
            trains[trainNum][seatNum] = 1;
            return;
        }
        if (command == 2) {
            trains[trainNum][seatNum] = 0;
            return;
        }
        if (command == 3) {
            for (int i = SEAT_NUMBER - 2; i >= 0; i--) {
                trains[trainNum][i + 1] = trains[trainNum][i];
                trains[trainNum][i] = 0;
            }
            return;
        }
        for (int i = 1; i < SEAT_NUMBER; i++) {
            trains[trainNum][i - 1] = trains[trainNum][i];
            trains[trainNum][i] = 0;
        }
    }
}
