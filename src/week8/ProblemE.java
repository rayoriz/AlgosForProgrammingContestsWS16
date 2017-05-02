package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

// code inspired from multiple sources
public class ProblemE {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			StringBuilder output = new StringBuilder("");
			Map<String, String> map = new HashMap<String, String>();
			String input1[] = br.readLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			int d = Integer.parseInt(input1[1]);
			int a = Integer.parseInt(input1[2]);
			String tempString = input1[3];
			int index1, index2 = 0, index3 = 0;
			
			
			
			StringBuilder result = new StringBuilder("");
			while (index3 < n) {
				
				// read input into the map
				input1 = br.readLine().split("=>");
				map.put(input1[0], input1[1]);
				index3++;
			}
			
			// add keys + and - in map
			map.put("+", "+");
			map.put("-", "-");
			for (index1 = 1; index1 <= d; index1++) {
				while (index2 < tempString.length()) {
					
					result = result.append(map.get(Character
							.valueOf(tempString.charAt(index2))
							.toString()));
					
					
					index2++;
				}
				tempString = result.toString();
				
				
				result = new StringBuilder("");
				index2 = 0;
			}
			
			// initalize cx and cy
			double centerX = 0.0;
			double centerY = 0.0;
			char currentChar;
			double pi = Math.PI / 180;
			int t = 0;
			index1 = 0;
			
			
			// add center
			output.append("Case #");
			output.append(i);
			output.append(":\n");
			output.append(centerX);
			output.append(" ");
			output.append(centerY);
			output.append("\n");
			
			
			// loop in all
			while (index1 < tempString.length()) {
				currentChar = tempString.charAt(index1);
				
				
				if (currentChar != '+' && currentChar != '-') {
					centerY = centerY 
							+ Math.sin(t * pi);
					centerX = centerX 
							+ Math.cos(t * pi);
					output.append(centerX);
					output.append(" ");
					output.append(centerY);
					output.append("\n");
				} else {
					t = currentChar == 
							'+' ? 
							t + a : 
							t - a;
				}
				index1++;
			}
			System.out.print(output.toString());
			br.readLine();
		}

	}
}
