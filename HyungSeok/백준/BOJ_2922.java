package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 2922 : 즐거운 단어
public class BOJ_2922 {

    static String s;
    static int under_line;
    static boolean[] Gather = new boolean[26];
    static long answer;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        s = bf.readLine();

        answer = 0;

        char[] mom = {'A', 'E', 'I', 'O', 'U'};

        for (int i = 0; i < 5; i++) {
            Gather[mom[i] - 'A'] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                list.add(i);
                under_line++;
            }
        }

        perm(0, new char[under_line]);

        System.out.println(answer);
    }

    private static void perm(int idx, char[] input) {
        if (idx == input.length) {
            if (L_check(input)) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (check(input, idx)) {
                input[idx] = (char) (i + 'A');
                perm(idx + 1, input);
            }
        }
    }

    private static boolean L_check(char[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'L') {
                return true;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                return true;
            }
        }

        return false;
    }

    private static boolean check(char[] input, int idx) {
        char[] tmp = s.toCharArray();

        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j] == '_') {
                    tmp[j] = input[i];
                    break;
                }
            }
        }

        for (int i = 0; i < idx; i++) {
            int mom = 0;
            int son = 0;
            if (list.get(i) - 2 >= 0) {
                for (int j = list.get(i) - 2; j <= list.get(i); j++) {
                    if (tmp[j] != '_') {
                        if (Gather[tmp[j] - 'A']) {
                            mom++;
                        } else {
                            son++;
                        }
                    }
                }

                if (mom == 3 || son == 3) {
                    return false;
                }
            }

            if (list.get(i) - 1 >= 0 && list.get(i) + 1 < tmp.length) {
                mom = 0;
                son = 0;
                for (int j = list.get(i) - 1; j <= list.get(i) + 1; j++) {
                    if (tmp[j] != '_') {
                        if (Gather[tmp[j] - 'A']) {
                            mom++;
                        } else {
                            son++;
                        }
                    }
                }

                if (mom == 3 || son == 3) {
                    return false;
                }
            }

            if (list.get(i) + 2 < tmp.length) {
                mom = 0;
                son = 0;
                for (int j = list.get(i); j <= list.get(i) + 2; j++) {
                    if (tmp[j] != '_') {
                        if (Gather[tmp[j] - 'A']) {
                            mom++;
                        } else {
                            son++;
                        }
                    }
                }

                if (mom == 3 || son == 3) {
                    return false;
                }
            }
        }

        return true;
    }
}