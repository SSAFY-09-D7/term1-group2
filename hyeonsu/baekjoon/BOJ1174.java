package baekjoon;

import java.util.*;

public class BOJ1174 {
    static int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        if (n >= 1023) System.out.println(-1);
        else {
            solve(0, 0, 10, "");
            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    private static void solve(int k, int depth, int len, String num) {
        if (depth == len) {
            if (num.equals("")) return;
            list.add(Long.parseLong(num));
            return;
        }

        if (k > len) return;

        solve(k + 1, depth + 1, len, num);
        solve(k + 1, depth + 1, len, num + nums[k]);
    }
}
