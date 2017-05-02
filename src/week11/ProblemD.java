package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 * @author rayo
 *
 */
public class ProblemD {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= testCases; i++) {

			int id = 0;
			int totalScore = 0;
			String probability;
			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow. Each test case consists of a line containing an integer n
			 * and a string x. n is the least number of points Lea has to get
			 * when rolling the dice and x is a string describing the dice. A
			 * set of a dice with b sides each (labelled 1 to b will be
			 * described as adb. Multiple sets of dice may be concatenated by +
			 * signs
			 */
			String input1[] = br.readLine().split(" ");
			 int n = Integer.parseInt(input1[0]);
			  String input[] = input1[1].split("\\+");
			ArrayList<Integer> diceRolls = 
					new ArrayList<Integer>();
			
			for (int index1 = 0; index1 < input.length; index1++) {
				String rolls[] = input[index1].split("d");
				int rollId = Integer.parseInt(rolls[0]);
				int val = Integer.parseInt(rolls[1]);
				for (int index2 = 1; 
						index2 <= rollId; index2++) {
					diceRolls.add(val);
				}
				totalScore += rollId * val;
				id += rollId;
			}
			
			/**
			 * For each test case, output one line containing Case #i: y
			 * where i is its number, starting at 1, and y is the
			 * probability to roll at least n points. The probability should
			 * be printed as a simplified rational number in the format
			 * numerator/- denominator. Simplified means that the
			 * numerator and denominator should not have a common divisor
			 * bigger than one and should not be negative. 0 should always
			 * be printed as 0/1.
			 * 
			 */
		// when 0
			if (n > totalScore)
				probability = "0/1";
		// 1/1
			else if (n <= id)
				probability = "1/1";
			// other probabilities
			
			else {
				// using big int because of the biiiig inputs
				
				BigInteger[][] twodMatrix = new BigInteger[id][n + 1];
				BigInteger[][] tempMatrix = new BigInteger[id][n + 1];
				int initVal = 2;
				int clear = 0;
				int tempRoll = diceRolls.get(0);
				int diceValue = tempRoll + 1;
				int finalDiceValue = diceValue;

				BigInteger i1 = BigInteger.valueOf(tempRoll);
				for (int index3 = initVal; index3 <= diceValue
						&& index3 <= n; index3++) {
					tempMatrix [0][index3] = BigInteger
							.valueOf(clear*index3);
					twodMatrix[0][index3] = BigInteger
							.valueOf(index3 - 1);
				}
				for (int index3 = 1; index3 < id; index3++) {

					// 1d3+1d4, then a value in the matrix means how much you
					// need to subtract from the probability=1. for example
					// matrix[1][6]=9 which means 1 - 9/(3*4) = 3/12 so the
					// Probability to get at least 6 points is 3/12

					tempRoll = diceRolls.get(index3);
					
					i1 = i1.multiply(BigInteger
							.valueOf(tempRoll));
					diceValue += tempRoll;
					twodMatrix[index3][initVal] = BigInteger.ZERO;
					initVal++;
					for (int index4 = initVal; 
							index4 <= diceValue
							&& index4 <= n; index4++) {
						BigInteger n1 = twodMatrix[index3]
								[index4 - 1];
						//1 - 9/(3*4) = 3/12
						if (index4 - tempRoll >= initVal) {
							if (index4 > finalDiceValue) {
								BigInteger n2 = twodMatrix[index3 - 1]
										[index4 - 1 - tempRoll];
								
/*								BigInteger n3 = twodMatrix[index4 - 1]
										[finalDiceValue];

								twodMatrix[index3][index4] = n3
										.add(n3
												.subtract(n2));*/
								BigInteger n3 = twodMatrix[index3 - 1]
										[finalDiceValue];

								twodMatrix[index3][index4] = n1
										.add(n3
												.subtract(n2));
							} else {
								
/*								BigInteger n4 = twodMatrix[index3 - 1][index3 - 1 
								                                       - tempRoll];						
								BigInteger n5 = twodMatrix[index3 - 1][index3 - 1];*/
								BigInteger n4 = twodMatrix[index3 - 1][index4 - 1 
								                                       - tempRoll];						
								BigInteger n5 = twodMatrix[index3 - 1][index4 - 1];
								twodMatrix[index3][index4] = n1
										.add(n5
												.subtract(n4));
							}
							
						} else {
							if (index4 > finalDiceValue) {
/*								BigInteger n5 = twodMatrix[index4 - 1]
										[finalDiceValue];
								twodMatrix[index3][index4] = n3
										.add(n5);*/
								BigInteger n5 = twodMatrix[index3 - 1]
										[finalDiceValue];
								twodMatrix[index3][index4] = n1
										.add(n5);
							} else {
								
/*								BigInteger n7 = twodMatrix[index4 - 1]
										[index4 - 1];
								twodMatrix[index3][index4] = n3
										.add(n7);*/
								
								BigInteger n7 = twodMatrix[index3 - 1]
										[index4 - 1];
								twodMatrix[index3][index4] = n1
										.add(n7);
							}
						}
					}
					
					finalDiceValue = diceValue;
				}
				BigInteger individualProbability = i1.subtract(twodMatrix[id - 1][n]);
				BigInteger overallProbability = individualProbability.gcd(i1);
				// final probability
				probability = individualProbability
						.divide(overallProbability) 
						+ "/"
						+ i1
						.divide(overallProbability);
			}
			System.out.println("Case #"+i+": "+probability);
		}
	}
}