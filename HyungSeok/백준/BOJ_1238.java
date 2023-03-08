package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1238 : 파티
public class BOJ_1238 {

    static class Node {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M, X;
    static ArrayList<Node>[] adj;
    static ArrayList<Node>[] reverse_adj;
    static boolean[] visited;
    static boolean[][] reverse_visited;
    static int[] dist;
    static int[] reverse_dist;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        reverse_adj = new ArrayList[N+1];

        dist = new int[N+1];
        reverse_dist = new int[N+1]; // 자신의 위치에서 X로 가는 최단 경로

        visited = new boolean[N+1];
        reverse_visited = new boolean[N+1][N+1];

        for (int i = 0 ; i < N+1 ; i++) {
            adj[i] = new ArrayList<>();
            reverse_adj[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        Arrays.fill(reverse_dist, Integer.MAX_VALUE);


        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, c));
            reverse_adj[e].add(new Node(s, c));
        }

        solve();

        reverse_solve();

        int answer = Integer.MIN_VALUE;

//        for (int i = 1 ; i < N+1 ; i++) {
            for (int j = 1 ; j < N+1 ; j++) {
                if (reverse_dist[j] != Integer.MAX_VALUE && dist[j] != Integer.MAX_VALUE) {
                    answer = Math.max(answer, reverse_dist[j] + dist[j]);
                }
            }
//        }

        System.out.println(answer);
    }

    private static void solve() {
        dist[X] = 0; // X에서 출발

        for (int i = 0 ; i < N ; i++) {
            int minIdx = -1;
            int minD = Integer.MAX_VALUE;

            for (int j = 1 ; j < N+1 ; j++) {
                if (minD > dist[j] && !visited[j]) {
                    minD = dist[j];
                    minIdx = j;
                }
            }

            if (minIdx == -1) {
                break;
            }

            visited[minIdx] = true;

            for (Node next : adj[minIdx]) {
                if (dist[next.end] > dist[minIdx] + next.cost && !visited[next.end]) {
                    dist[next.end] = dist[minIdx] + next.cost;
                }
            }
        }
    }

    // 각각의 위치에서 X로 도착하는 최단경로
    private static void reverse_solve() {
        for (int i = 1 ; i < N+1 ; i++) {
            if (i != X) {
                reverse_dist[i] = 0; // i에서 출발

                int minIdx = -1;
                int minD = Integer.MAX_VALUE;

                for (int j = 1 ; j < N+1 ; j++) {
                    if (minD > reverse_dist[j] && !reverse_visited[i][j]) {
                        minIdx = j;
                        minD = reverse_dist[j];
                    }
                }

                if (minIdx == -1)
                    break;

                reverse_visited[i][minIdx] = true;

                for (Node next : reverse_adj[minIdx]) {
                    if (reverse_dist[next.end] > reverse_dist[minIdx] + next.cost && !reverse_visited[i][next.end]) {
                        reverse_dist[next.end] = reverse_dist[minIdx] + next.cost;
                    }
                }
            }

        }
    }
}
