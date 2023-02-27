package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

class Dog {
    int a;
    int b;

    public Dog(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
public class BOJ2991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Dog[] dogs = new Dog[2];
    static int[] info = new int[3];
    static int[] ans = new int[3];
    static boolean[][] log = new boolean[2][1000];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        dogs[0] = new Dog(stoi(st.nextToken()), stoi(st.nextToken()));
        dogs[1] = new Dog(stoi(st.nextToken()), stoi(st.nextToken()));

        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            info[i] = stoi(st.nextToken());
            max = Math.max(max, info[i]);
        }

        //강아지 한마리씩 구한다.
        for (int i = 0; i < 2; i++) {
            int idx = 1;
            Loop:
            while (true) {
                for (int j = 0; j < dogs[i].a; j++) {
                    log[i][idx++] = true;
                    if (idx > max) break Loop;
                }

                for (int j = 0; j < dogs[i].b; j++) {
                    log[i][idx++] = false;
                    if (idx > max) break Loop;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (log[0][info[i]]) ans[i]++;
            if (log[1][info[i]]) ans[i]++;
        }

        for (int a : ans) {
            System.out.println(a);
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
