package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ProblemC {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int t = 1; t <= cases; t++) {
			int j, k, l = 0;
			Character a, b;
			Character c;
			String input[] = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			String humanSq[] = new String[n];
			String miceSq[] = new String[m];
			Integer count[][] = new Integer[10][2];
			for (int i = 0; i < 10; i++) {
				count[i][0] = 0;
				count[i][1] = i;
			}
			for (j = 0; j < n; j++)
				humanSq[j] = br.readLine();
			for (j = 0; j < m; j++)
				miceSq[j] = br.readLine();
			int size = humanSq[0].length();
			for (j = 0; j < n; j++) {
				for (k = 0; k < m; k++) {
					while (l < size) {
						a = humanSq[j].charAt(l);
						b = miceSq[k].charAt(k);

						switch (a.toString() + b.toString()) {
						case "AC":
							count[0][0]++;
							break;
						case "CA":
							count[0][0]++;
							break;
						case "AT":
							count[1][0]++;
							break;
						case "TA":
							count[1][0]++;
							break;
						case "AG":
							count[2][0]++;
							break;
						case "GA":
							count[2][0]++;
							break;
						case "CT":
							count[3][0]++;
							break;
						case "TC":
							count[3][0]++;
							break;
						case "CG":
							count[4][0]++;
							break;
						case "GC":
							count[4][0]++;
							break;
						case "TG":
							count[5][0]++;
							break;
						case "GT":
							count[5][0]++;
							break;
						case "AA":
							count[6][0]++;
							break;
						case "CC":
							count[7][0]++;
							break;
						case "TT":
							count[8][0]++;
							break;
						case "GG":
							count[9][0]++;
							break;
						}
					}
				}
			}
			for (j = 0; j < 6; j++) {
				count[j][0] = count[j][0] / 2;
			}
			Arrays.sort(count, new Comparator<Integer[]>() {
				@Override
				public int compare(final Integer[] entry1, final Integer[] entry2) {
					final Integer time1 = entry1[0];
					final Integer time2 = entry2[0];
					return time2 - time1;
				}
			});
			Map<String, Integer> hm = new HashMap<String, Integer>();
			int maxMin = -120;
			int totalVal = 0;
			int flag = 0;
			for (j = 0; j < 10; j++) {
				switch (count[j][1]) {
				case 0:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("AC", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("AC", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 1:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("AT", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("AT", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 2:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("AG", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("AG", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 3:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("CT", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("CT", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 4:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("CG", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("CG", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 5:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -20) {
						hm.put("TG", count[j][0] * 5);
						totalVal = totalVal + 10;
						maxMin = maxMin + 10;
					} else {
						hm.put("TG", count[j][0] * 10);
						totalVal = totalVal + 20;
						maxMin = maxMin + 20;
					}
					break;
				case 6:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -5) {
						hm.put("AA", count[j][0] * 5);
						totalVal = totalVal + 5;
					} else {
						hm.put("AA", count[j][0] * 10);
						totalVal = totalVal + 10;
					}
					break;
				case 7:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -5) {
						hm.put("CC", count[j][0] * 5);
						totalVal = totalVal + 5;
					} else {
						hm.put("CC", count[j][0] * 10);
						totalVal = totalVal + 10;
					}
					break;
				case 8:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -5) {
						hm.put("TT", count[j][0] * 5);
						totalVal = totalVal + 5;
					} else {
						hm.put("TT", count[j][0] * 10);
						totalVal = totalVal + 10;
					}
					break;
				case 9:
					if (totalVal + maxMin == 0) {
						flag = 1;
						break;
					}
					if (totalVal + maxMin == -5) {
						hm.put("GG", count[j][0] * 5);
						totalVal = totalVal + 5;
					} else {
						hm.put("GG", count[j][0] * 10);
						totalVal = totalVal + 10;
					}
					break;
				}
				if (flag == 1)
					break;

			}
			
			System.out.println(totalVal);
		}
	}
}
