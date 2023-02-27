package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1244 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static boolean[] switches;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        switches = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            switches[i] = st.nextToken().charAt(0) == '1';
        }
        m = stoi(br.readLine());

        //로직
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = stoi(st.nextToken());
            int num = stoi(st.nextToken()) - 1;

            if (gender == 1) {
                //자기가 받은 수의 배수의 상태를 바꾼다.
                for (int j = num; j < n; j += num + 1) {
                    switches[j] = !switches[j];
                }
            } else {
                /*
                자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서
                그 구간에 속한 스위치의 상태를 모두 바꾼다. 이 때 구간에 속한 스위치 개수는 항상 홀수가 된다.
                 */
                int l = num - 1;
                int r = num + 1;
                switches[num] = !switches[num];
                if (l < 0 || r >= n) continue;

                while (true) {
                    if (l < 0 || r >= n) break;
                    if (switches[l] != switches[r]) break;
                    switches[l] = !switches[l--];
                    switches[r] = !switches[r++];
                }
            }
        }

        //정답 출력
        int idx = 0;

        while (true) {
            if (idx == switches.length) break;
            sb.append(switches[idx++]? "1 " : "0 ");
            if (idx % 20 == 0) sb.append("\n");
        }

        System.out.println(sb);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
