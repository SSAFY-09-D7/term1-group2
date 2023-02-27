package baekjoon;

/*
계란에는 내구도와 무게가 정해져있다.
계란으로 계란을 치면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
내구도가 0이 되는 순간 계란은 깨진다.
ex) 내구도 = 7 무게 = 5, 내구도 = 3 무게 4
계란 1로 계란2를 치게 되면 계란 1의 내구도는 4만큼 감소해 3이 되고 계란 2의 내구도는 5만큼 감소해 -2가 된다.
계란 2는 내구도가 0 이하로 떨어졌기 때문에 깨지고 계란 1은 깨지지 않았다.

일렬로 놓여있는 계란에 대해 왼쪽부터 차례로 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제
1. 가장 왼쪽의 계란을 든다.
2. 깨지지 않은 다른 계란 중 하나를 친다.
2-1. 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘긴다. (손에서 내려놓음.)
3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에들고 2번 과정 진행.
3-1. 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.

깰 수 있는 계란의 최대 갯수를 구해라.
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ16987 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, max = 0;
    static Egg[] eggs;
    static boolean[] isBroken;

    public static void main(String[] args) throws IOException {

        //초기화
        n = stoi(br.readLine());
        eggs = new Egg[n];
        isBroken = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        //로직
        solve(0);

        //정답 출력
        System.out.println(max);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(int idx) {
        if (idx == n) {
            int cnt = 0;
            for (boolean b : isBroken) {
                if (b) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        boolean canStrike = false;

        // 현재 손에 든 계란이 깨지지 않았는가?
        if (!isBroken[idx]) {
            // 현재 든 계란 외에 깨지지 않은 계란 순회
            for (int i = 0; i < n; i++) {
                if (i == idx){
                    continue;
                }
                //계란이 깨졌다면 다음 계란으로
                if (isBroken[i]) continue;
                canStrike = true;

                //계란을 친다.
                eggs[idx].dura -= eggs[i].weight;
                eggs[i].dura -= eggs[idx].weight;
                //맞는 계란이 내구도가 0 이하로 내려가면 깬 표시를 남긴다.
                if (eggs[i].dura <= 0) {
                    isBroken[i] = true;
                }
                if (eggs[idx].dura <= 0) {
                    isBroken[idx] = true;
                }
                solve(idx + 1);
                //다시 원상복구
                if (eggs[i].dura <= 0) {
                    isBroken[i] = false;
                }
                if (eggs[idx].dura <= 0) {
                    isBroken[idx] = false;
                }
                eggs[idx].dura += eggs[i].weight;
                eggs[i].dura += eggs[idx].weight;

            }
        }

        // 손에 든 계란이 깨졌거나 모든 계란이 깨져있는 경우 다음 오른쪽 계란으로 넘어간다.
        if (!canStrike) solve(idx + 1);

    }

}

class Egg{
    int dura;
    int weight;

    public Egg(int dura, int weight) {
        this.dura = dura;
        this.weight = weight;
    }
}
