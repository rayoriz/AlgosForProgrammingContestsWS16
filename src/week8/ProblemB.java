package week8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.geom.*;
import java.io.BufferedReader;

// code inspired from multiple sources to calculte orthocenter and ccenter
public class ProblemB {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {

			String[] input1 = br.readLine().split(" ");
			// point 1
			Point2D point1 = new Point2D.Double(Double.parseDouble(input1[0]), Double.parseDouble(input1[1]));
			input1 = br.readLine().split(" ");
			// point 2
			Point2D point2 = new Point2D.Double(Double.parseDouble(input1[0]), Double.parseDouble(input1[1]));
			input1 = br.readLine().split(" ");
			Point2D point3 = new Point2D.Double(Double.parseDouble(input1[0]), Double.parseDouble(input1[1]));
			StringBuilder result = new StringBuilder();

			result.append("Case #").append(i).append(": \n");
			Point2D orthoCenter = calculateOthoCenter(point1, point2, point3);
			Point2D centroid = calculateCentroid(point1, point2, point3);
			Point2D cCenter = getCCenter(point1, point2, point3);
			result.append(centroid.getX()).append(" ").append(centroid.getY()).append("\n");
			result.append(orthoCenter.getX()).append(" ").append(orthoCenter.getY()).append("\n");
			result.append(cCenter.getX()).append(" ").append(cCenter.getY());
			System.out.println(result);
			br.readLine();
		}
	}

	/**
	 * average of all points
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	static Point2D calculateCentroid(Point2D a, Point2D b, Point2D c) {
		return new Point2D.Double((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
	}

	static Point2D getCCenter(Point2D a, Point2D b, Point2D c) {
		double d = 2 * (a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY())
				+ c.getX() * (a.getY() - b.getY()));
		double x = ((Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * (b.getY() - c.getY())
				+ (Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)) * (c.getY() - a.getY())
				+ (Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)) * (a.getY() - b.getY())) / d;
		double y = ((Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * (c.getX() - b.getX())
				+ (Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)) * (a.getX() - c.getX())
				+ (Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)) * (b.getX() - a.getX())) / d;
		return new Point2D.Double(x, y);
	}

	static Point2D calculateOthoCenter(Point2D a, Point2D b, Point2D c) {
		Line2D.Double p1, p2;
		p2 = new Line2D.Double(a, intersectionOf(b, c, a));
		p1 = new Line2D.Double(c, intersectionOf(a,b,c));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
		double x1 = p1.getX1();
		double y1 = p1.getY1();
		double x2 = p1.getX2();
		double y2 = p1.getY2();
		double x3 = p2.getX1();
		double y3 = p2.getY1();
		double x4 = p2.getX2();
		double y4 = p2.getY2();
		double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		double x = 0.0d, y = 0.0d;
		if (d != 0.0d) {
			x = ((x3 - x4) * (x1 * y2 - y1 * x2)
					- (x1 - x2) 
					* (x3 * y4 - y3 * x4)) / d;
			y = ((y3 - y4) 
					* (x1 * y2 - y1 * x2) 
					- (y1 - y2) 
					* (x3 * y4 - y3 * x4)) / d;
		}

		return new Point2D.Double(x, y);
	}

	static Point2D intersectionOf(Point2D a, Point2D b, Point2D c) {
		double x = a.getX()
				+ (((c.getX() - a.getX()) 
						* (b.getX() - a.getX())
						+ (c.getY() - a.getY()) 
						* (b.getY() - a.getY()))
						/ (Math.pow((b.getX() 
								- a.getX()), 2) 
								+ Math.pow((b.getY() 
										- a.getY()), 2))
						* (b.getX() - a.getX()));
		
		
		double y = a.getY()
				+ (((c.getX() - a.getX()) * (b.getX() - a.getX())
						+ (c.getY() - a.getY()) * (b.getY() - a.getY()))
						/ (Math.pow((b.getX() - a.getX()), 2) 
								+ Math.pow((b.getY() - a.getY()), 2))
						* (b.getY() - a.getY()));
		return new Point2D.Double(x, y);
	}
}
