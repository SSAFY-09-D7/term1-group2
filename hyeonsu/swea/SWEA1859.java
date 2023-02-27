package swea;

import java.util.*;
import java.io.*;

class SWEA1859 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, tNum = 1;
    static long[] res;

    public static void main(String args[]) throws IOException {
        t = stoi(br.readLine());
        res = new long[t];

        while (t-- > 0) {
            int n = stoi(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[j] = stoi(st.nextToken());
            }
            bw.write("#" + tNum++ + " " + solve(arr, n) + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    public static long solve(int[] arr, int n) {
        long profit = 0;
        int maxIdx = n-1;
        int idx = maxIdx-1;

        while(idx >= 0) {
            if(arr[maxIdx] > arr[idx]) {
                profit += arr[maxIdx] - arr[idx];
                idx--;
            }
            else {
                maxIdx = idx;
                idx = maxIdx - 1;
                continue;
            }
        }
        return profit;
    }
}