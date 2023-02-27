package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ6443 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static String[] input;
    static List<String> list;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        input = new String[n];

        for (int i = 0; i < n; i++) {
            input[i] = br.readLine();
            v = new boolean[input[i].length()];
            list = new ArrayList<>();
            solve(i, 0, input[i].length(), "");
            Collections.sort(list);
            for (String s : list) bw.write(s + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void solve(int idx, int depth, int len, String str) {
        if (depth == len) {
            list.add(str);
            return;
        }

        boolean[] alphabet = new boolean[27];
        for (int i = 0; i < len; i++) {
            int alphabetIdx = input[idx].charAt(i) - 'a';
            if (!alphabet[alphabetIdx] && !v[i]) {
                alphabet[alphabetIdx] = true;
                v[i] = true;
                solve(idx, depth + 1, len, str + (char)(alphabetIdx + 'a'));
                v[i] = false;
            }
        }
    }

}
