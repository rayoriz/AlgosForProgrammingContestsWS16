/*package week7;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// code inspired by multiple sources - stackoverflow, geeksfor geeks, sanfoundry

public class ProblemC {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			int numberOfPoints = Integer.parseInt(br.readLine());
			Point2D.Double	centroid = new Point2D.Double(0, 0);
			Point2D.Double[] coordinates = new Point2D.Double[numberOfPoints];
			double x, y;
			for (int j = 0; j < numberOfPoints; j++) {
				String[]	input1 = br.readLine().split(" ");
				x = Double.parseDouble(input1[0]);
				y = Double.parseDouble(input1[1]);

				centroid.x = centroid.x + x;
				centroid.y = centroid.y + y;

				coordinates[j] = new Point2D.Double(x, y);
			}

			centroid.x = centroid.x / numberOfPoints;
			centroid.y = centroid.y / numberOfPoints;
			
			Point2D.Double point1 = coordinates[numberOfPoints - 1];

			Point2D.Double p1 = null;

			int nos = 0; // number of stable positions

			for (int k = 0; k < numberOfPoints; k++) {
				p1 = point1;
				point1 = coordinates[k];

				if (!checkCenterOfGravity(p1, point1,centroid))
					continue;

				Point2D.Double testPoint = null;
				boolean isValid = true;
				boolean isClockwise = false;
				boolean isCounterClockwise = false;

				for (int j = 0; j < numberOfPoints; j++) {
					testPoint = coordinates[j];
					if (p1 == testPoint || point1 == testPoint)
						continue;
					double ccw = rotateCCW(p1, point1, testPoint);

					if (ccw == 0) {
						isValid = false;
						break;
					}
					if (ccw < 0) {
						if (isCounterClockwise) {
							isValid = false;
							break;
						} else
							isClockwise = true;
					} else if (ccw > 0) {
						if (isClockwise) {
							isValid = false;
							break;
						} else
							isCounterClockwise = true;
					}
				}

				if (isValid)
					nos++;
			}

			System.out.println("Case #" + i + ": " + nos);
				br.readLine();
		}
	}

	public static double rotateCCW(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}


	public static boolean checkCenterOfGravity(Point2D.Double p1, Point2D.Double p2, Point2D.Double centroid) {
		Point2D.Double perpendicular = keepStraight(p1, p2, centroid);

		double xmin = p1.x < p2.x ? p1.x : p2.x;
		double ymin = p1.y < p2.y ? p1.y : p2.y;
		
		double xmax = p1.x > p2.x ? p1.x : p2.x;
		double ymax = p1.y > p2.y ? p1.y : p2.y;

		if (perpendicular.x >= xmin && perpendicular.x <= xmax && perpendicular.y >= ymin && perpendicular.y <= ymax)
			return true;

		return false;
	}
	
	public static Point2D.Double keepStraight(Point2D.Double p1, Point2D.Double p2, Point2D.Double q) {
		double px = p2.x - p1.x;
		double py = p2.y - p1.y;

		double dist2 = px * px + py * py;
		double u = ((q.x - p1.x) * px + (q.y - p1.y) * py) / dist2;

		return new Point2D.Double(p1.x + u * px, p1.y + u * py);
	}


}
*/