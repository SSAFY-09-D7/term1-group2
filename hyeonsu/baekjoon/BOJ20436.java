package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;
    

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class BOJ20436 {
    /*
    모음 set 자음 set을 만든다.
    모음, 좌음 각각 좌표를 저장하는 map을 만든다.
    문자열에서 n번 째 문자가 모음인지 자음인지 판별한다.
    모음이면 왼쪽 손가락 위치에서 n번 째 문자의 위치를 판별한다.
    자음이면 오른쪽 손가락 위치에서 위와 동일하게 진행.
    누르는 시간 1초를 추가한다.
     */
    static String vowelsEng = "qwertasdfgzxcv";
    static String consonantsEng = "yuiophjklbnm";
    static String keyboard = "qwertyuiopasdfghjklzxcvbnm";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String input;
    static char left, right;
    static Set<Character> vowels = new HashSet<>(), consonants = new HashSet<>();
    static Map<Character, Point> m = new HashMap<>();
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        //초기화
        st = new StringTokenizer(br.readLine());
        left = st.nextToken().charAt(0);
        right = st.nextToken().charAt(0);
        input = br.readLine();

        //자음, 모음 set을 각각 만든다.
        for (int i = 0; i < vowelsEng.length(); i++) {
            vowels.add(vowelsEng.charAt(i));
        }
        for (int i = 0; i < consonantsEng.length(); i++) {
            consonants.add(consonantsEng.charAt(i));
        }
        
        //해당 알파벳의 좌표 정보를 담고 있는 맵을 생성한다.
        int engIdx = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 10; j++) {
                if (i == 2 && j == 10) continue;
                if (i == 3 && j == 8) break;
                m.put(keyboard.charAt(engIdx++), new Point(i, j));
            }
        }

        //로직
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);

            //현재 문자가 자음인지 모음인지 판별한다.
            //자음이라면 왼쪽손가락의 현재위치 -> 현재 문자 위치까지 거리를 계산
            if (vowels.contains(cur)) {
                ans += Math.abs(m.get(left).x - m.get(cur).x) + Math.abs(m.get(left).y - m.get(cur).y) + 1;
                //현재 손가락 위치를 업데이트
                left = cur;
            }
            //모음이라면 오른쪽 손가락의 현재위치 -> 현재 문자 위치까지 거리를 계산
            else {
                ans += Math.abs(m.get(right).x - m.get(cur).x) + Math.abs(m.get(right).y - m.get(cur).y) + 1;
                //현재 손가락 위치를 업데이트
                right = cur;
            }
        }

        //정답 출력
        System.out.println(ans);
    }
}
