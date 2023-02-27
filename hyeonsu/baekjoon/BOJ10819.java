package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10819 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = stoi(st.nextToken());
        }

        backtracking(0);

        System.out.println(max);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void backtracking(int depth) {
        if (depth == n) {
            max = Math.max(max, sum());
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(i, depth);
            backtracking(depth + 1);
            swap(i, depth);
        }
    }

    static int sum() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(nums[i] - nums[i + 1]);
        }

        return sum;
    }

    static void swap(int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
