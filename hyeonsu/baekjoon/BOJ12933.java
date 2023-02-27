package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ12933 {
    /*
    애초에 q로 시작하지 않으면 바로 -1을 리턴
    오리가 추가될 때 : q가 나왔을 때 모든 오리가 아직 울고 있거나 한번 다 운 오리가 아예 없는 경우
    오리가 추가되지 않을 때 : 오리가 1마리 이상이면서 한마리라도 울음을 끝냈을 경우
    잘못된 녹음인 경우 : q가 아니면서 해당 위치의 문자가 울고있는 오리 전부와 싱크가 맞지 않는 경우 / q가 아니면서 사이즈가 0인경우
    k인 경우 : 끝나기 직전인 오리가 있다면 스택을 비워준다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final char[] duck = {'.', 'q', 'u', 'a', 'c', 'k'};
    static String input;
    static int duckCnt = 0;
    static List<Stack<Character>> log = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        input = br.readLine();

        Loop:
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            //'q'일 때
            if (cur == 'q') {
                //size가 0이면 오리 추가
                if (log.size() == 0) {
                    log.add(new Stack<>());
                    log.get(0).add(cur);
                    duckCnt++;
                    continue;
                }

                //현재 울고있는 오리를 순회하면서 울음 소리가 끝난 오리가 있는지 찾는다.
                boolean shouldAdd = true;
                for (int j = 0; j < log.size(); j++) {
                    //울음소리가 끝난 오리가 있다면 문자를 추가하고 플래그 변경
                    if (log.get(j).size() == 0) {
                        log.get(j).add(cur);
                        shouldAdd = false;
                        break;
                    }
                }

                //울음소리가 끝난 오리가 없다면 오리를 추가한다.
                if (shouldAdd) {
                    log.add(new Stack<>());
                    log.get(log.size() - 1).add(cur);
                    duckCnt++;
                }
            } else {
                //q가 아닌데 size가 0이라면 잘못된 녹음이므로 빠져나온다.
                if (log.size() == 0) {
                    break;
                }
                //모든 오리를 순회하며 울음순서가 맞는 오리가 있는지 확인한다.
                boolean isValid = false;
                for (int j = 0; j < log.size(); j++) {
                    //있는 경우 추가해주고 k까지 나왔으면 전부 삭제한다.
                    if (duck[log.get(j).size() + 1] == cur) {
                        if (cur== 'k') {
                            log.get(j).removeAll(log.get(j));
                        } else {
                            log.get(j).add(cur);
                        }
                        isValid = true;
                        break;
                    }
                }
                //울음순서가 맞는 오리가 없다면 -1을 리턴
                if (!isValid) {
                    duckCnt = 0;
                    break;
                }
            }
        }

        //울다 만 경우를 확인
        if (duckCnt > 0) {
            for (int i = 0; i < log.size(); i++) {
                if (log.get(i).size() > 0) {
                    duckCnt = 0;
                    break;
                }
            }
        }

        System.out.println(duckCnt == 0 ? -1 : duckCnt);
    }
}
