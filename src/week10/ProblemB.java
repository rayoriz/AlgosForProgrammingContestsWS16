package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author rayo
 *
 */
public class ProblemB {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * starts with three integers n d, the length of the main street ,
			 * the number of street lights on main street n, and the radius of
			 * the light cone of each street light d, which indicates how far
			 * each light shines. Then, another line follows, consisting of n
			 * integers p1 p2 . . . pn, describing the positions of the street
			 * lights.
			 * 
			 */
			int finalvalue = 0;
			int distanceLight = 0;
			String[] input = br.readLine().split(" ");
			int l = Integer.parseInt(input[0]);
			int n = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]);
			int[] lichtArray = new int[n];
			input = br.readLine().split(" ");
			// there can be zero lamposts? 
			if (n > 0) { //-- check
				int index1 = 0;
				for (String inp : input){
					lichtArray[index1] = Integer.parseInt(inp);
					index1++;
				}
			}
			Arrays.sort(lichtArray);
			int t = 0;
			// check distance
			Boolean flag = false;

			for (int lk : lichtArray) {
				if (lk - d > t) {
					// impossible case - get out
					flag = false;
					break;
				} else {
					if (lk - d > distanceLight) {
						distanceLight = t;
						finalvalue++;
					}
					
					// possible case flag set to true
					t = lk + d;
					if (t >= l) {
						finalvalue++;
						flag = true;
						break;
					}
				}
			}

			/**
			 * For each test case, output one line containing Case #i: x where
			 * i is its number, starting at 1, and x is either the smallest
			 * number of street lights that are needed to illuminate the whole
			 * main street (which goes in a straight line from 0 to `), or
			 * impossible if there is no way to illuminate the whole street.
			 * To illuminate the whole main street, there should be light from
			 * at least one street light at every point on the main street
			 * between 0 and . The boundary of each light cone is considered to
			 * be illuminated as well. In particular, this means that a point on
			 * the street is illuminated if two light cones touch there, they do
			 * not need to intersect. See the sample data explanation
			 */
			if (flag) {
				System.out.println("Case #" + i + ": " + finalvalue);
			} else {
				System.out.println("Case #" + i + ": " + "impossible");
			}

			br.readLine();
		}
	}
}
