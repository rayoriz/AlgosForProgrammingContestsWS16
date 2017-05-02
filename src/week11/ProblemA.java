package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 
 * @author rayo
 *
 */
class Subject {
	int id;
	boolean flag;
	int cost;
	int score;
/**
 * constructor
 * @param id
 * @param flag
 * @param score
 * @param cost
 */
	Subject(int id, Boolean flag, int score, int cost) {
		super();
		this.id = id;
		this.flag = flag;
		this.score = score;
		this.cost = cost;
	}
}

public class ProblemA {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		Subject[] subjects = new Subject[10010];
		for (int i = 1; i <= cases; i++) {
			int temp = 0;

			/**
			 * The first line of the input contains an integer t, the number of
			 * lectures. t lectures follow, each of them separated by a blank
			 * line. Each lecture starts with a line containing two integers: m,
			 * the number of characters that fit on the allowed cheat sheet, and
			 * n, the number of topics covered. n lines describing the topics
			 * follow. The i-th line contains three integers pi , li and si
			 * where pi is the number of pieces of information available, li is
			 * the length of a piece of information for this topic and si is its
			 * score.
			 * 
			 */
			String[] input1 = br.readLine().split(" ");
			int m = Integer.parseInt(input1[0]);
			int n = Integer.parseInt(input1[1]);
			
			// read input
			for (int index1 = 1; index1 <= n; index1++) {
				String[] inp2 = br.readLine().split(" ");
				int pi = Integer.parseInt(inp2[0]);
				int li = Integer.parseInt(inp2[1]);
				int si = Integer.parseInt(inp2[2]);
				for (int k = 1; k <= pi; k++) {
					subjects[temp + k] = new Subject(index1,false, si, li);
				}
				temp = temp + pi;
			}
			int N = temp;
			//http://introcs.cs.princeton.edu/java/23recursion/Knapsack.java.html

			// opt[n][w] = max profit of packing items 1..n with weight limit w
	        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
			int[][] opt = new int[N + 1][m + 1];
			boolean[][] sol = new boolean[N + 1][m + 1];
			for (int index2 = 1; index2 <= N; index2++) {
				for (int w = 1; w <= m; w++) {
	                // don't take item n

					int option1 = opt[index2 - 1][w];
	                // take item n

					int option2 = Integer.MIN_VALUE;
					
					if (subjects[index2]
							.cost <= w)
						option2 = subjects[index2]
								.score + opt[index2 - 1]
										[w - subjects[index2]
												.cost];
	                // select better of two options

					opt[index2][w] = Math
							.max(option1, option2);
					sol[index2][w] = (option2 > 
					option1);
				}
			}
	        // determine which items to take

			boolean[] isValid = new boolean[N + 1];
			for (int index3 = N, w = m; index3 > 0; index3--) {
				if (sol[index3][w]) {
					isValid[index3] = true;
					w = w - subjects[index3].cost;
				} else {
					isValid[index3] = false;
				}
			}

			// result
			String output = "";
			for (int index4 = 1; index4 <= N; index4++) {
				if (isValid[index4]) {
					output+= subjects[index4].id+" ";
				}
			}

			/**
			 * For each test case, output one line containing Case #i: x where
			 * i is its number, starting at 1, and x is a spaceseparated list of
			 * topics to be added (topic i may appear at most pi times in this
			 * list). The sum of their lengths should be at most m and the sum
			 * of their scores should be as big as possible.
			 * 
			 */
			System.out.println("Case #"+i+": "+output);
				br.readLine();
		}

	}
}
