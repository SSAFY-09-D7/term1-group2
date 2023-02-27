package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1759 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int l, c;
    static char[] arr;
    static StringBuilder sb;
    static Set<Character> gather;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        //초기화
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        l = stoi(st.nextToken());
        c = stoi(st.nextToken());
        arr = new char[c];
        checked = new boolean[c];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        gather = new HashSet<>();
        gather.add('a');
        gather.add('e');
        gather.add('i');
        gather.add('o');
        gather.add('u');

        //로직
        dfs(0, 0, "");
    }

    static void dfs(int depth, int idx, String str) {
        //정답 조건
        if(depth == l) {
            //1개의 모음, 2개의 자음 인지 확인
            for(int i = 0; i < str.length(); i++) {
                if(gather.contains(str.charAt(i))) {
                    int cnt = 0;
                    for(int j = 0; j < str.length(); j++) {
                        if(!gather.contains(str.charAt(j))) cnt++;
                    }
                    if(cnt >= 2) sb.append(str).append('\n');
                    return;
                }
            }
            return;
        }

        if(idx == arr.length) return;

        for(int i = idx; i < arr.length; i++) {
            dfs(depth+1, i+1, str+arr[i]);
        }

    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
