package swea;

import java.io.*;
import java.util.*;

public class SWEA4615 {
    static final int BLACK = 1;
    static final int WHITE = 2;

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int t, tNumber = 1;

    public static void main(String[] args) throws IOException {
        t = stoi(br.readLine());

        while (t-- > 0) {
            //초기화
            int bCnt = 2;
            int wCnt = 2;
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int m = stoi(st.nextToken());
            int[][] board = new int[n][n];

            //가운데에 시작 돌 놓기
            boolean flag = true;
            for (int i = n / 2 - 1; i <= n / 2; i++) {
                for (int j = n / 2 - 1; j <= n / 2; j++) {
                    board[i][j] = flag ? WHITE : BLACK;
                    flag = !flag;
                }
                flag = false;
            }

            //로직
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int r = stoi(st.nextToken()) - 1;
                int c = stoi(st.nextToken()) - 1;
                int turn = stoi(st.nextToken());

                //말놓기
                board[r][c] = turn;
                if (turn == BLACK) bCnt++;
                else wCnt++;

                //탐색하며 먹을 수 있는 말 8방탐색
                for (int j = 0; j < 8; j++) {
                    int nr = dx[j] + r;
                    int nc = dy[j] + c;

                    //좌표 범위가 벗어난 경우
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                    //한칸 앞이 상대방 돌 이면서 마지막 돌이 내 돌인 경우 전부 먹을 수 있다.
                    if (board[nr][nc] != 0 && board[nr][nc] != turn) {
                        int cnt = 0;
                        boolean isValid = false;
                        while (true) {
                            //좌표가 벗어났거나 돌이 없는 경우
                            if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 0) break;
                            //한 개 이상의 다른 돌이 사이에 있는 경우
                            if (board[nr][nc] == turn) {
                                isValid = true;
                                break;
                            }
                            nr += dx[j];
                            nc += dy[j];
                            cnt++;
                        }

                        //돌이 사이에 있는 경우에 전부 바꿔준다.
                        if (isValid) {
                            int sr = dx[j] + r;
                            int sc = dy[j] + c;

                            while (true) {
                                if (sr == nr && sc ==nc) {
                                    break;
                                }
                                board[sr][sc] = turn;
                                sr += dx[j];
                                sc += dy[j];
                                if (turn == BLACK) {
                                    bCnt++;
                                    wCnt--;
                                } else {
                                    wCnt++;
                                    bCnt--;
                                }
                            }
                        }
                    }
                }
            }

            //정답 입력
            bw.write("#" + tNumber++ + " " + bCnt + " " + wCnt + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
