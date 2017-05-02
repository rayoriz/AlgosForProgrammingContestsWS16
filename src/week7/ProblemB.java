package week7;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class ProblemB {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());

		for (int t = 1; t <= cases; t++) {

			String[] inp = br.readLine().split(" ");

			int xImpact = Integer.parseInt(inp[0]);

			int yImpact = Integer.parseInt(inp[1]);

			int n = Integer.parseInt(inp[2]);

			Map<String, Point> map = new HashMap<>();

			String sPoint = null;

			for (int i = 0; i < n; i++) {

				String[] side = br.readLine().split(" ");
				int x1 = Integer.parseInt(side[0]);
				int y1 = Integer.parseInt(side[1]);
				int x2 = Integer.parseInt(side[2]);
				int y2 = Integer.parseInt(side[3]);

				String s1 = x1 + "," + y1;
				String s2 = x2 + "," + y2;

				Point p1 = map.get(s1);
				Point p2 = map.get(s2);

				if (p1 == null) {
					p1 = new Point(x1, y1);
					map.put(s1, p1);
				}

				if (p2 == null) {
					p2 = new Point(x2, y2);
					map.put(s2, p2);
				}

				p1.adjacencies.add(p2);
				p2.adjacencies.add(p1);

				if (sPoint == null)
					sPoint = s1;
			}

			Polygon polygon = new Polygon();

			Stack<Point> stack = new Stack<>();

			stack.add(map.get(sPoint));

			while (!stack.isEmpty()) {
				Point p = stack.pop();

				p.isVisited = true;

				polygon.addPoint(p.x, p.y);

				for (Point pt : p.adjacencies) {
					if (!pt.isVisited && !stack.contains(pt))
						stack.add(pt);
				}
			}

			if (polygon.contains(xImpact, yImpact))
				System.out.println("Case #" + t + ": " + "jackpot");
			else
				System.out.println("Case #" + t + ": " + "too bad");

			br.readLine();
		}
	}

}

class Point {
	int x;
	int y;
	boolean isVisited = false;
	List<Point> adjacencies;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
		adjacencies = new ArrayList<>();
	}
}
