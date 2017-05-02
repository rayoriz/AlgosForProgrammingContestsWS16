package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// code inspired from multiple sources, cross product from Stackoverflow
class CoOrdinate3D {
	double x, y, z;
    
	//Constructor
	public CoOrdinate3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}


public class ProblemA {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 String line = br.readLine();

		int cases = Integer.parseInt(line);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < cases; i++) {
			line = br.readLine();

			String data[] = line.split(" ");
			CoOrdinate3D point11 = new CoOrdinate3D(Double.parseDouble(data[0]), Double.parseDouble(data[1]), 1);
			CoOrdinate3D point12 = new CoOrdinate3D(Double.parseDouble(data[2]), Double.parseDouble(data[3]), 1);
			CoOrdinate3D point13 = new CoOrdinate3D(Double.parseDouble(data[4]), Double.parseDouble(data[5]), 1);
			CoOrdinate3D point21 = new CoOrdinate3D(Double.parseDouble(data[6]), Double.parseDouble(data[7]), 1);
			CoOrdinate3D point22 = new CoOrdinate3D(Double.parseDouble(data[8]), Double.parseDouble(data[9]), 1);
			CoOrdinate3D point23 = new CoOrdinate3D(Double.parseDouble(data[10]), Double.parseDouble(data[11]), 1);

			CoOrdinate3D l1 = crossProduct(point11, point12);
			CoOrdinate3D l2 = crossProduct(point21, point22);
			CoOrdinate3D vectorTemp = new CoOrdinate3D(0, 0, 1);
			CoOrdinate3D line1 = crossProduct(l1, vectorTemp);
			
			// rotate
			double x = line1.x;
			line1.x = line1.y;
			line1.y = -x;
			
			// cross product 
			line1 = crossProduct(point13, line1);
			CoOrdinate3D line2 = crossProduct(l2, vectorTemp);
			
			// rotate again
			x = line2.x;
			line2.x = line2.y;
			line2.y = -x;
			
			// cross product 
			line2 = crossProduct(point23, line2);
			CoOrdinate3D p14 = crossProduct(line1, l1);
			
			//transformation with reference to z axis
			p14.x = p14.x / p14.z;
			p14.y = p14.y / p14.z;
			CoOrdinate3D p24 = crossProduct(line2, l2);
			p24.x = p24.x / p24.z;
			p24.y = p24.y / p24.z;
            
			
			// if z coordinate exists, apply transformation
			CoOrdinate3D s = crossProduct(line1, line2);
			if (s.z != 0) {
				s.x = s.x / s.z;
				s.y = s.y / s.z;
			}
			
			
			
			result.append("Case #");
			result.append(i + 1);
			result.append(": ");
			
			// dr strange :)
			if (s.y < p14.y 
					|| s.y < p24.y 
					|| s.z == 0)
				result.append("strange");
			else {
				// actual result
				result.append(s.x);
				result.append(" ");
				result.append(s.y);
			}
			result.append("\n");
		}
		System.out.println(result);
	}

	/**
	 * straight forward cross product
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static CoOrdinate3D crossProduct(CoOrdinate3D p1, CoOrdinate3D p2) {
		double x = p1.y * p2.z - p1.z * p2.y;
		double y = p1.z * p2.x - p1.x * p2.z;
		double z = p1.x * p2.y - p1.y * p2.x;
		return new CoOrdinate3D(x, y, z);
	}
}

