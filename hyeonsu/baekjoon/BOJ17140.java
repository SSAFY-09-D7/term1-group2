package baekjoon;

import java.io.*;
import java.util.*;
/*
문제 요약
A[r][c] == k 가 되기 위한 최소 시간을 구해라.

R연산 : 배열의 모든 행에 대해서 정렬 수행 IF 행의 개수 >= 열의 개수
C연산 : 배열의 모든 열에 대해서 정렬 수행 IF 열의 개수 > 행의 개수

정렬 조건 (공통)
1. 각각의 수가 몇번 나왔는지 찾는다.
2. 빈도 수를 오름차순 정렬한다.
    2-1. 빈도수가 같다면 수가 커지는 순으로 정렬한다. => Comparator 사용
3. 정렬된 결과를 다시 배열 A에 넣는다.
4. 결과를 배열에 넣을 때 수 , 등장횟수를 모두 넣으며 수를 앞에 넣는다.
EX) [3, 1, 1] => [3, 1, 1, 2] => [2, 1, 3, 1, 1, 2] => [3, 1, 2, 2, 1, 3] ...

조건 (공통)
결과를 배열에 넣고 R연산이 적용된 경우 가장 큰행을 기준으로 모든 행의 크기가 변하고 C연산도 열이 동일하게 변한다.
행 또는 열의 크기가 커진 곳에는 0이 채워진다.
0이 채워지는 곳은 정렬할 때 무시한다.
행 OR 열의 크기가 100을 넘어가는 경우 처음 100개를 제외한 나머지는 버린다.

풀이 아이디어
2차원 배열이지만 행, 열의 크기가 계속 동적으로 변하기 때문에 단순히 2차원 배열을 쓰기엔 어려워 보인다.
=> 가변 배열 사용
=> 애초에 100을 넘어가지 않기 때문에 100 * 100 배열을 만들어서 편하게 조절
C연산 시 해당 열에 대해서는 어떻게 처리할것인지? = 100*100 배열을 만들면해결

흐름
연산 -> 배열 처리 -> A[r][c] 수 검사
 */
class Node {
    int cnt;
    int number;

    public Node(int cnt, int number) {
        this.cnt = cnt;
        this.number = number;
    }
}
public class BOJ17140 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[][] map = new int[100][100];
    static int[] rowLens = new int[100];
    static int[] colRens = new int[100];
    public static void main(String[] args) throws IOException {
        //초기화
        st = new StringTokenizer(br.readLine());
        int r = stoi(st.nextToken()) - 1;
        int c = stoi(st.nextToken()) - 1;
        int k = stoi(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = stoi(st.nextToken());
            }
            rowLens[i] = 3;
            colRens[i] = 3;
        }

        //로직
        int minute = 0;
        int maxRow = 3;
        int maxCol = 3;
        while (minute <= 100) {
            //정답확인
            if (map[r][c] == k) break;

            //행과 열 사이즈 비교
            if (maxRow >= maxCol) {
                //R연산
                for (int i = 0; i < maxRow; i++) {
                    rowLens[i] = calculateR(i);
                    maxCol = Math.max(rowLens[i], maxCol);
                }
            } else {
                //C연산
                for (int i = 0; i < maxCol; i++) {
                    colRens[i] = calculateC(i);
                    maxRow = Math.max(maxRow, colRens[i]);
                }
            }
            minute++;
        }

        //정답출력
        System.out.println(minute == 101 ? -1 : minute);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static int calculateR(int idx) {
        //빈도 수를 구한다.
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            if (map[idx][i] == 0) continue;
            m.put(map[idx][i], m.getOrDefault(map[idx][i], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> s = m.entrySet();
        List<Node> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> cur : s) {
            list.add(new Node(cur.getValue(), cur.getKey()));
        }

        //빈도수를 오름차순 정렬한다.
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                //빈도수가 같다면 원래 번호를 기준으로 오름차순 정렬한다.
                if (o1.cnt == o2.cnt) return o1.number - o2.number;
                return o1.cnt - o2.cnt;
            }
        });

        //정렬된 결과를 원래 배열에 넣고 길이를 업데이트한다.
        int curLen = list.size() * 2;
        int curIdx = 0;
        int listIdx = 0;
        for (Node cur : list) {
            map[idx][curIdx++] = cur.number;
            map[idx][curIdx++] = cur.cnt;
            if (curIdx == 100) break;
        }
        //뒤부터 0 처리
        for (int i = curIdx; i < 100; i++) {
            map[idx][i] = 0;
        }
        return curLen;
    }

    static int calculateC(int idx) {
        //빈도 수를 구한다.
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            if (map[i][idx] == 0) continue;
            m.put(map[i][idx], m.getOrDefault(map[i][idx], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> s = m.entrySet();
        List<Node> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> cur : s) {
            list.add(new Node(cur.getValue(), cur.getKey()));
        }

        //빈도수를 오름차순 정렬한다.
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                //빈도수가 같다면 원래 번호를 기준으로 오름차순 정렬한다.
                if (o1.cnt == o2.cnt) return o1.number - o2.number;
                return o1.cnt - o2.cnt;
            }
        });

        //정렬된 결과를 원래 배열에 넣고 길이를 업데이트한다.
        int curLen = list.size() * 2;
        int curIdx = 0;
        for (Node cur : list) {
            map[curIdx++][idx] = cur.number;
            map[curIdx++][idx] = cur.cnt;
            if (curIdx == 100) break;
        }
        //뒤부터 0 처리
        for (int i = curIdx; i < 100; i++) {
            map[i][idx] = 0;
        }

        return curLen;
    }
}
