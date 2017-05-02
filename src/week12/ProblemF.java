package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemF {

	public static void main(String[] args) {
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(r);
		String line = "";
		
		try { line = in.readLine(); } 
		catch (IOException e) { e.printStackTrace(); }
		
		int testCases = Integer.parseInt(line);
		StringBuilder builder = new StringBuilder();
		for(int t = 0; t < testCases; t++)
		{
			try { line = in.readLine(); } 
			catch (IOException e) { e.printStackTrace(); }
			String[] data = line.split(" ");
			int width = Integer.parseInt(data[0]);
			int blocks = Integer.parseInt(data[1]);
			int[] arr = new int[width+1];
			int solution = 0;
			
			builder.append("Case #");
			builder.append(t+1);
			builder.append(":");
			
			for(int i = 0; i < blocks; i++) {
				try { line = in.readLine(); } 
				catch (IOException e) { e.printStackTrace(); }
				data = line.split(" ");
				int w = Integer.parseInt(data[0]);
				int h = Integer.parseInt(data[1]);
				int start = Integer.parseInt(data[2]);
				int highest = Integer.MIN_VALUE;
				for(int j = 0; j < w; j++) {
					int pos = start + j;
					if(arr[pos] > highest)
						highest = arr[pos];
				}
				for(int j = 0; j < w; j++) {
					int pos = start + j;
					arr[pos] = highest + h;
				}
				if(highest + h > solution)
					solution = highest + h;
				builder.append(" ");
				builder.append(solution);
			}
			
			builder.append("\n");
			
			if(t < (testCases - 1)) {
				try { line = in.readLine(); } 
				catch (IOException e) { e.printStackTrace(); }
			}
		}
		System.out.println(builder);
	}
}

