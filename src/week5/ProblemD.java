package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemD {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 1; i <= t; i++) {
			String s = br.readLine();
			Character c;
			int base8, base10;
			if (s.length() == 1) {
				base10 = Integer.valueOf(s);
				base10 = base10 % 13;
			} else {
				Character z = s.charAt(0);
				base10 = Integer.valueOf(z.toString());
				for (int j = 1; j < s.length(); j++) {
					c = s.charAt(j);
					base8 = Integer.valueOf(c.toString());
					base10 = ((base10 * 8) + base8) % 13;
				}
			}
			base10 = (base10 + 3) % 13;
			base10 = base10 == 0 ? 13 : base10;
			System.out.println("Case #" + i + ": " + base10);
		}
	}
}