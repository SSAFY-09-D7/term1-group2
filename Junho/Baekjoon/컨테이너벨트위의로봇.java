

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 컨테이너벨트위의로봇 {

	static int n,k,cnt;
	static Deque<Point> up_deque;
	static Deque<Point> down_deque;
	static StringTokenizer st;
	static class Point{
		int durability;
		boolean robot;
		public Point(int durability, boolean robot) {
			super();
			this.durability = durability;
			this.robot = robot;
		}
		@Override
		public String toString() {
			return "Point [durability=" + durability + ", robot=" + robot + "]";
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cnt = 0;
		up_deque = new LinkedList<Point>();
		down_deque = new LinkedList<Point>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			up_deque.add(new Point(Integer.parseInt(st.nextToken()), false));
		}
		for (int i = 0; i < n; i++) {

			down_deque.add(new Point(Integer.parseInt(st.nextToken()), false));
		}
		
		while (true) {
			// 1번
			//로봇과 벨트가 한칸씩 움직인다
			up_deque.addFirst(down_deque.pollLast());
			down_deque.addFirst(up_deque.pollLast());

			robotDown();
			
			// 2번
			for (int i = 0; i < n-1; i++) {
				Point p = up_deque.pollLast();
				//로봇이 움직일 수 있는 경우
				if (!p.robot && up_deque.peekLast().robot && p.durability >= 1) {
					p.robot = true;
					up_deque.peekLast().robot=false;
					p.durability--;
					
				}
				up_deque.addFirst(p);
			}
			up_deque.addFirst(up_deque.pollLast());
			robotDown();
			

			// 3번
			//로봇을 올리는 위치에 올리는 경우
			if (up_deque.peekFirst().durability != 0) {
				up_deque.peekFirst().robot = true;
				up_deque.peekFirst().durability--;
			}

			// 4번
			//내구도 0 개수 확인
			if (count_k()) {
				cnt++;
				break;
			}

			cnt++;
		}
		System.out.println(cnt);

	}

	//내구도 0개수 확인
	private static boolean count_k() {
		Iterator<Point> it = up_deque.iterator();
		Iterator<Point> it2 = down_deque.iterator();
		int sum = 0;
		while (it.hasNext()) {
			Point next = it.next();
			if (next.durability == 0) {
				sum++;
			}
		}
		while (it2.hasNext()) {
			Point next = it2.next();
			if (next.durability == 0) {
				sum++;
			}
		}
		if (sum >= k) {
			return true;
		}
		return false;
	}

	//내리는 위치 로봇 내리기
	private static void robotDown() {
		if (up_deque.peekLast().robot) {
			up_deque.peekLast().robot = false;
		}

	}
}
