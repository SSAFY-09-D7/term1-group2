package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2468 {
    static int[] dr= {1,-1,0,0};
    static int[] dc= {0, 0, 1,-1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    static byte[][] map;
    static int[][] v;


    static ArrayList<Byte> rain=new ArrayList<>();
    static byte rainh;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        map=new byte[n][n];
        v = new int[n][n];

        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                byte in=Byte.parseByte(st.nextToken());
                map[i][j]=in;
                if(!rain.contains(in)) rain.add(in);
            }
        }
        rain.add((byte)0);
        rain.sort(Comparator.naturalOrder());



        int maxcount=0;
        for(int k=0;k<rain.size() - 1;k++) {
            rainh=rain.get(k);

            //물에 잠길 지역을 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] <= rainh) v[i][j] = 2;
                    else v[i][j] = 0;
                }
            }

            int count=0;
            for(byte i=0;i<n;i++) {
                for(byte j=0;j<n;j++) {
                    //다음번에서 찾아가지 않음
                    if(v[i][j] == 0) {
                        count++;
                        bfs(i,j);
                    }
                }
            }
            if(maxcount<=count) {
                maxcount=count;
            }
        }
        bw.write(maxcount+"");
        bw.close();

    }

    static class Point{
        byte r,c;

        public Point(byte r, byte c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

    private static void bfs(byte r, byte c) {
        Queue<Point> q = new LinkedList<>();

        v[r][c]=1;
        q.offer(new Point(r,c));

        while(!q.isEmpty()) {
            Point p = q.poll();

            for(int d=0;d<4;d++) {
                byte nr=(byte)(p.r+dr[d]);
                byte nc=(byte)(p.c+dc[d]);

                if(nr>=0&&nr<n&&nc>=0&&nc<n&&v[nr][nc]==0) {
                    v[nr][nc] = 1;
                    q.add(new Point(nr, nc));
                }
            }
        }
    }
}



