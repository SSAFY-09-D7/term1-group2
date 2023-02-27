package baekjoon;

import java.io.*;
import java.util.Stack;

public class BOJ2504 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String input;
    static int ans = 0;
    static boolean flag = true;

    static Stack<String> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        input = input.replace("()", "2");
        input = input.replace("[]", "3");
        input = input.replace("(]", "0");
        input = input.replace("[)", "0");
        if (input.length() < 2 && !Character.isDigit(input.charAt(0))) {
            flag = false;
        } else {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            //0이라면 유효하지 않은 문자열
            if (c - '0' == 0) {
                flag = false;
                break;
            }

            //c가 닫는 괄호 종류일 때 = 곱해야 할 때
            if (c == ')' || c == ']') {
                //스택이 비어있다면 유효하지 않은 문자열
                if (s.isEmpty()) {
                    flag = false;
                    break;
                }
                int num = stoi(s.pop());
                if (s.isEmpty()) {
                    flag = false;
                    break;
                }
                //숫자 뒤에 스택의 요소가 짝이 아니면 유효하지 않은 문자열
                if ((c == ')' && !s.peek().equals("(")) || (c == ']' && !s.peek().equals("["))) {
                    flag = false;
                    break;
                }
                //유효하다면 pop 해주고 숫자를 곱해주고 뒤에 숫자가 여러개 있다면 계속 더해주고 push 한다.
                s.pop();
                num = c == ')' ? num * 2 : num * 3;
                    while (!s.isEmpty() && Character.isDigit(s.peek().charAt(0))) {
                        num += stoi(s.pop());
                    }
                s.push(String.valueOf(num));
            }

            //c가 여는 괄호일 때
            else if (c == '(' || c == '[') {
                //push 한다.
                s.push(String.valueOf(c));
            }

            //c가 숫자일 때
            else {
                //스택이 비었거나 숫자가 아니면 그냥 넣고 아니라면 pop해서 더해주고 push한다.
                if (s.isEmpty() || !Character.isDigit(s.peek().charAt(0))) s.push(String.valueOf(c));
                else {
                    int num = stoi(s.pop()) + (c - '0');
                    s.push(String.valueOf(num));
                }
            }
        }
        }

        //스택에 괄호가 남아있는 경우도 봐야한다.
        if (flag && s.size() == 1) ans = stoi(s.pop());
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
