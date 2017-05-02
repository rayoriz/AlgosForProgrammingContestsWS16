package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Rogue Two
 * 
 * @author Alex, Riswan
 *
 */
public class ProblemB {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			String input1[] = br.readLine().split(" ");
			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow. Each test case consists of a single line n k of two
			 * space-separated integers n and k, where n is the number of pigs
			 * in the circle and k is the number whispered into Lea’s ear.
			 * 
			 */
			int n = Integer.parseInt(input1[0]); // pigs
			List<Integer> pigs = new ArrayList<Integer>();
			for (int index1 = 1; index1 <= n; index1++) {
				pigs.add(index1);
			}
			int k = Integer.parseInt(input1[1]); // whispered pigs
			int temp =0;
			int val = 0;
			boolean flag = false;
			temp = (k - 1) 
					% (n); 
						
			while (pigs.size() 
					> 1) {
				val++;
				pigs.remove(temp);
				// move one
				temp = (temp + (k - 1)) % (n - val);
			}
			int p = pigs.get(0);
			
			/**
			 * For each test case, print a line containing Case #i: p where i is its number, starting at 1 and p is the position of the
pig that Lea should replace. The number 1 denotes the starting position the leader points to and the position numbers
are assumed to be increased in clockwise order. Each line of the output should end with a line break.

			 */
			
			System.out.println("Case #" + i + ": " + p);
		}
	}
}
