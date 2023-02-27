package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] nums;
    static int[] operator = new int[4];

    public static void main(String[] args) throws IOException {
        //초기화
        n = stoi(br.readLine());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] =stoi(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = stoi(st.nextToken());
        }
        //로직
        recur(nums[0], 0);

        System.out.println(max);
        System.out.println(min);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void recur(int sum, int depth) {
        if (depth == n - 1) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0){
                operator[i]--;
                if (i == 0) {
                    recur(sum + nums[depth + 1], depth + 1);
                }
                if (i == 1) {
                    recur(sum - nums[depth + 1], depth + 1);
                }
                if (i == 2) {
                    recur(sum * nums[depth + 1], depth + 1);
                }
                if (i == 3) {
                    recur(sum / nums[depth + 1], depth + 1);
                }
                operator[i]++;
            }
        }
    }

}
