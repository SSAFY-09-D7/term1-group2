package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Audience {
    int start;
    int end;

    public Audience(int start, int end) {

        this.end = end;
    }
}
public class BOJ3985 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int l, n, expec = 0, ans;
    static Audience[] a;
    static int[] rollCake;

    public static void main(String[] args) throws IOException {
        //초기화
        l = stoi(br.readLine());
        n = stoi(br.readLine());
        rollCake = new int[l + 1];
        a = new Audience[n];

        Arrays.fill(rollCake, -1);

        //입력 받으면서 동시에 예상값 찾기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = new Audience(stoi(st.nextToken()), stoi(st.nextToken()));
            int expecCnt = a[expec].end - a[expec].start + 1;
            int curCnt = a[i].end - a[i].start + 1;
            expec = curCnt > expecCnt ? i : expec;
        }

        //로직
        for (int cur = 0; cur < n; cur++) {
            for (int curIdx = a[cur].start; curIdx <= a[cur].end; curIdx++) {
                if (rollCake[curIdx] != -1) continue;
                rollCake[curIdx] = cur;
            }
        }

        //가장 많은 사용자 찾기
        int [] frequencyCnt = new int[n];
        for (int i = 0; i < rollCake.length; i++) {
            if (rollCake[i] == -1) continue;
            frequencyCnt[rollCake[i]]++;
        }
        ans = 0;
        for (int i = 1; i < n; i++) {
            ans = frequencyCnt[i] > frequencyCnt[ans] ? i : ans;
        }

        //정답출력
        System.out.println(expec + 1);
        System.out.println(ans + 1);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
