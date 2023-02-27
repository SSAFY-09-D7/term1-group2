package baekjoon;

import java.io.*;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
부등
 */

public class BOJ2529 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static String max = String.valueOf(Long.MIN_VALUE);
    static String min = String.valueOf(Long.MAX_VALUE);
    static boolean[] v = new boolean[10];
    static char[] inEqualitys;

    public static void main(String[] args) throws IOException {
       //초기화
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        inEqualitys = new char[st.countTokens()];
        for (int i = 0; i < inEqualitys.length; i++) {
            inEqualitys[i] = st.nextToken().charAt(0);
        }

        //로직
        solve(-1, "");

        //출력
        System.out.println(max);
        System.out.println(min);
    }

    static void solve(int k, String sb) {
        if (k == inEqualitys.length) {
            //숫자로 바꾼다.
            long result = Long.parseLong(sb);
            //max, min도 숫자로 바꾼다.
            long maxNum = Long.parseLong(max);
            long minNum = Long.parseLong(min);
            //비교 후 새로운 값으로 바꿔야 한다면 문자열을 업데이트 해준다.
            max = result > maxNum ? sb : max;
            min = result < minNum ? sb : min;

            return;
        }

        if (k == -1) {
            for (int i = 0; i <= 9; i++) {
                v[i] = true;
                solve(0, sb + i);
                v[i] = false;
            }
            return;
        }

        char curInEqual = inEqualitys[k];

        for (int i = 0; i <= 9; i++) {
            if (v[i]) continue;
            int cur = sb.charAt(sb.length() - 1) - '0';

            if (curInEqual == '<') {
                if (cur < i) {
                    v[i] = true;
                    solve(k + 1, sb + i);
                    v[i] = false;
                }
            }
            else {
                if (cur > i) {
                    v[i] = true;
                    solve(k + 1, sb + i);
                    v[i] = false;
                }
            }
        }
    }
}
