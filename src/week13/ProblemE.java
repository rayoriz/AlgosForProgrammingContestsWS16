package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Rogue two
 * 
 * @author Alex, Riswan
 *
 */
public class ProblemE {

	static List<Integer> dataList;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			dataList = new ArrayList<Integer>();

			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * starts with a line containing two integers n b, where n is the
			 * number of prizes and b is the cost to draw lots. A second line
			 * follows, containing n space-separated integers a1 an, where ai is
			 * the value of prize i.
			 * 
			 */
			String[] input1 = br.readLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			int b = Integer.parseInt(input1[1]);

			String[] input2 = br.readLine().split(" ");
			for (int index1 = 0; index1 < n; index1++) {
				dataList.add(Integer.parseInt(input2[index1]));
			}

			double low = 0.0;
			double high = 1.0;
			double mid = 0.0;

			while (high - low > 0.0000001) {

				mid = (low + high) / 2;
				if (drawLottery(mid) > b)
					high = mid;
				else
					low = mid;

			}

			/**
			 * For each test case, output one line containing Case #i: x where i
			 * is its number, starting at 1, and x is the maximal winning
			 * probability of a lot such that the expected total payoff is less
			 * or equal than 0 with an absolute error of up to 10 6 . Each line
			 * of the output should end with a line break.
			 * 
			 */
			System.out.println("Case #" + i + ": " + (low + high) / 2);

			br.readLine();
		}

	}

	/**
	 * 
	 * @param prob
	 * @return
	 */
	static double drawLottery(double prob) {
		int count = 1;
		double probability = 0;

		for (int currentDraw : dataList)
			probability += (currentDraw * Math.pow(prob, count++));

		return probability;
	}
}
