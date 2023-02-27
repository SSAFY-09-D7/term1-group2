package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] croatiaAlphabet = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        for (int i = 0; i < croatiaAlphabet.length; i++) {
            input = input.replace(croatiaAlphabet[i], "0");
        }
        System.out.println(input.length());
    }
}
