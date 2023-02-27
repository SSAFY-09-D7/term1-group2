package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ20291 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static String[] inputs;
    static Map<String, Integer> m = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        inputs = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();
            String extender = st.nextToken();
            m.put(extender, m.getOrDefault(extender, 0) + 1);
        }

        Set<Map.Entry<String, Integer>> set = m.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            bw.write(entry.getKey() + " " + entry.getValue() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
