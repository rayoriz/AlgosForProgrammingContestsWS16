package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class ProblemA {
	
	static class Wrapper {
		   public int[] data;

		   Wrapper (int[] data) {
			   this.data = data;
		   }
		   @Override
		   public boolean equals(Object o) {
			   Wrapper other = (Wrapper)o;
			   	return data[0] == other.data[0] &&
			   			data[1] == other.data[1] &&
			   			data[2] == other.data[2] &&
			   			data[3] == other.data[3];
			   }
		   @Override
		   public int hashCode() {
			   return data[3] + data[2] * 10 + data[1] * 100 + data[0] * 1000;
		   }
		}
	
	static int[][] valid;
	
public static void main(String[] args) throws NumberFormatException, IOException {
		
		init(); 
	
		InputStreamReader r = new InputStreamReader ( System . in );
		BufferedReader in = new BufferedReader ( r );
		int testCasesCount = Integer.parseInt(in.readLine());
		int len;
		String input;
		
		for (int i = 1; i <= testCasesCount; i++) {
			len = Integer.parseInt(in.readLine());
			int[][] arr = new int[len][4];

			for (int j = 0; j < len; j++) {
				input = in.readLine();
				arr[j][0] = Character.getNumericValue(input.charAt(0));
				arr[j][1] = Character.getNumericValue(input.charAt(1));
				arr[j][2] = Character.getNumericValue(input.charAt(3));
				arr[j][3] = Character.getNumericValue(input.charAt(4));
				
				if ((arr[j][0] > 2) ||
					(arr[j][0] == 2 && arr[j][1] > 3) ||
					(arr[j][2] > 5)) {
				}
			}
			
			System.out.print(buildResult(i, arr));
			
			if (i != testCasesCount)
				in.readLine();
		}
	}

	public static void init() {
		valid = new int[10][];

		for (int i = 0; i < valid.length; i++) {
			switch (i) {
			case 0:
				valid[i] = new int[] { 0, 8 };
				break;
			case 1:
				valid[i] = new int[] { 0, 1, 3, 4, 7, 8, 9 };
				break;
			case 2:
				valid[i] = new int[] { 2, 8 };
				break;
			case 3:
				valid[i] = new int[] { 3, 8, 9 };
				break;
			case 4:
				valid[i] = new int[] { 4, 8, 9 };
				break;
			case 5:
				valid[i] = new int[] { 5, 6, 8, 9 };
				break;
			case 6:
				valid[i] = new int[] { 6, 8 };
				break;
			case 7:
				valid[i] = new int[] { 0, 3, 7, 8, 9 };
				break;
			case 8:
				valid[i] = new int[] { 8 };
				break;
			case 9:
				valid[i] = new int[] { 8, 9 };
				break;
				
			}
		}
	}
	
	public static StringBuilder buildResult(int caseNo, int[][] input) {
		HashSet<Wrapper> old = null;
		int i = 0;
		for (int[] time : input) {
			HashSet<Wrapper> set = getPossibleTimes(time);
			if (old == null) {
				old = set;
			}
			else {
				int temp = i;
				old.removeIf(e -> !set.contains(new Wrapper(getNextTime(e.data, temp))));
			}
			i++;
		}
		
		Wrapper[] list = old.toArray(new Wrapper[0]);
		Arrays.sort(list, (a, b) -> Integer.compare(a.hashCode(), b.hashCode()));
		
		StringBuilder sb = new StringBuilder(12);
		sb.append("Case #");
		sb.append(caseNo);
		sb.append(":");
		sb.append(System.lineSeparator());
		
		if (list.length == 0) {
			sb.append("none");
			sb.append(System.lineSeparator());
		}
		
		for (Wrapper time : list) {
			sb.append(time.data[0]);
			sb.append(time.data[1]);
			sb.append(":");
			sb.append(time.data[2]);
			sb.append(time.data[3]);
			sb.append(System.lineSeparator());
		}
		
		return sb;
	}
	
	public static HashSet<Wrapper> getPossibleTimes(int[] input) {
		
		HashSet<Wrapper> set = new HashSet<Wrapper>();
		
		int[] hr1 = valid[input[0]];
		int[] hr2 = valid[input[1]];
		int[] min1 = valid[input[2]];
		int[] min2 = valid[input[3]];
		
		for (int i : hr1) {
			for (int j : hr2) {
				if ((i > 2) || (i == 2 && j > 3))
					break;
				for (int k : min1) {
					if (k > 5)
						break;
					for (int l : min2) {
						set.add(new Wrapper(new int[] { i, j, k, l }));
					}
				}
			}
		}
		
		return set;
	}
	
	public static int[] getNextTime(int[] input, int minCount) {
		int[] result = input.clone();
		result[3] += minCount;
		
		if (result[3] > 9) {
			result[2] += result[3] / 10;
			result[3] = result[3] % 10;
			
			
			if (result[2] > 5) {
				result[2] -= 6;
				result[1]++;
				
				if (result[1] == 10 && result[0] < 2) {
					result[1] = 0;
					result[0]++;
				} else if (result[1] == 4 && result[0] == 2) {
					result[1] = 0;
					result[0] = 0;
				}
			}
		}
		
		return result;
	}
}
