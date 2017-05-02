package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemB {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		int outputArray[];
		for (int it = 1; it <= cases; it++) {
			String input[] = br.readLine().split(" ");

			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * starts with a single line containing two integers n and c. n is
			 * the number of coin and note values and c is the amount of money
			 * that must be spent. The next line consists of n distinct integers
			 * v1, . . . , vn describing the coin/note values in increasing
			 * order. You may assume that a coin of value 1 is always available,
			 * and that Lea has as many of all the coins/notes as she needs.
			 * 
			 */
			int n = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int[] totalVal = new int[c + 1];
			int count1[] = new int[n];
			totalVal[0] = 1;
			int[] tempIndex = new int[c + 1];
			String coin[] = br.readLine().split(" ");
			int values[] = new int[n];
			ArrayList<Integer> ci = new ArrayList<Integer>();
			for (int index4 = 0; index4 < coin.length; index4++) {
				// coin val
				values[index4] = (Integer.valueOf(coin[index4]));
				// cindex
				ci.add(Integer.valueOf(coin[index4]));
			}
			int val = values.length;
			for (int index1 = 0; index1 < c; index1++)
				if (totalVal[index1] > 0)
					for (int index2 = 0; index2 < val; index2++) {
						// add index and value
						int spentNow = index1 + values[index2];
						
						if (spentNow <= c) {
							
							if (totalVal[spentNow] == 0 ||
									totalVal[spentNow] > 
							totalVal[index1] + 1) {
								
								totalVal[spentNow] = totalVal[index1] 
										+ 1;
								tempIndex[spentNow] = index2;
							}
						}
					}

			if (totalVal[c] == 0) {
				//null array
				outputArray = null;
			} else {

				int[] result = new int[totalVal[c] - 1];
				int index = c;
				while (index > 0) {
/*					
					result[totalVal[index2] - 2] = values[tempIndex[index2]];
					index = index2 + values[tempIndex[index2]];*/
					
					result[totalVal[index] - 2] = values[tempIndex[index]];
					index = index - values[tempIndex[index]];
				}

				outputArray = result;
				
			}
			for (int index5 = 0; index5 < outputArray.length; index5++) {
				int index = ci
						.indexOf(outputArray[index5]);
				count1[index]++;
			}
			String optimal = "";
			for (int index6 = 0; index6 < coin.length; index6++) {
				optimal = optimal + " " + count1[index6];
			}
			/**
			 * For each test case, output one line containing Case #i: x where i
			 * is its number, starting at 1, and x is a spaceseparated sequence
			 * of n integers a1, . . . , an, where ai means that the optimal
			 * solution uses exactly ai coins/notes of value vi .
			 * 
			 */
			System.out.println("Case #" + it + ": " + optimal);
			br.readLine();
		}
	}

}