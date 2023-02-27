package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16435 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, l;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        l = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (arr[i] > l) break;
            if (arr[i] <= l) l++;
        }

        System.out.println(l);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
