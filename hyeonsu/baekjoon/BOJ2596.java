package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2596 {
    static final String[] arr = new String['I'];
    static final String[] tmp = {"000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        //초기화
        int charNum = Integer.parseInt(br.readLine());
        String code = br.readLine();
        String ans = "";
        int idx = 0;
        for(int c = 'A'; c < 'I'; c++) {
            arr[c] = tmp[idx++];
        }

        //로직
        for (int i = 0; i < charNum; i++) {
            String curCode = code.substring(i * 6 , i * 6 + 6);
            boolean isValid = false;

            for (int c = 'A'; c < 'I'; c++) {
                if (curCode.equals(arr[c])) {
                    ans += String.valueOf((char)c);
                    isValid = true;
                    break;
                }
            }

            //true일 경우 안 구해봐도 된다.
            if (isValid) continue;

            //문자가 하나만 다를 경우 그것으로 인식
            for (int j = 'A'; j < 'I'; j++) {
                if (isCorrect(curCode, arr[j])) {
                    ans += String.valueOf((char)j);
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                ans = String.valueOf(i + 1);
                break;
            }
        }
        //답 출력
        System.out.println(ans);
    }

    /*
    @p
     */
    static boolean isCorrect(String curCode, String code) {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (cnt > 1) return false;
            if (curCode.charAt(i) != code.charAt(i)) cnt++;
        }

        return cnt <= 1;
    }

}