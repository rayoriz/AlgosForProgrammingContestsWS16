package week6;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
	
	static class CoOrdinate {
		int x;
		int y;

		public CoOrdinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
	
	static int columnLength, rowLength;
	static int[][] visited;
	static Point start;
	static int toolCount;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader r = new InputStreamReader ( System . in );
		BufferedReader in = new BufferedReader ( r );
		int testCasesCount = Integer.parseInt(in.readLine());
		String input;
		String[] tokens;
		char field;
		
		for (int i = 1; i <= testCasesCount; i++) {
			tokens = in.readLine().split(" ");
			columnLength = Integer.parseInt(tokens[0]);
			rowLength = Integer.parseInt(tokens[1]);
			toolCount = 0;
			visited = new int[rowLength][columnLength];
			
			for (int j = 0; j < rowLength; j++) {
				input = in.readLine();
				for (int k = 0; k < columnLength; k++) {
					field = input.charAt(k);
					if (field == '#')
						visited[j][k] = 2;
					else if (field == 'L') {
						start = new Point(k, j);
					} else if (field == 'T') {
						visited[j][k] = 1;
						toolCount++;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder(12);
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			
			if (hasPath(start.x, start.y, visited, 0))
				sb.append("yes");
			else
				sb.append("no");
			
			System.out.println(sb);
			
			if (i != testCasesCount)
				in.readLine();
		}
	}
	
	public static boolean hasPath(int x, int y, int[][] cave, int gotTools) {
		
		boolean isTool = false;
		if (cave[y][x] == 1) {
			gotTools++;
			isTool = true;
		}
		
		if (toolCount == gotTools)
			return true;
		
		cave[y][x] = 3;
		
		if (x - 1 >= 0 && (cave[y][x - 1] <= 1) && hasPath(x - 1, y, cave, gotTools))
			return true;
		if (x + 1 < columnLength && (cave[y][x + 1] <= 1) && hasPath(x + 1, y, cave, gotTools))
			return true;
		if (y - 1 >= 0 && (cave[y - 1][x] <= 1) && hasPath(x, y - 1, cave, gotTools))
			return true;
		if (y + 1 < rowLength && (cave[y + 1][x] <= 1) && hasPath(x, y + 1, cave, gotTools))
			return true;
		
		if (isTool)
			cave[y][x] = 1;
		else
			cave[y][x] = 0;
		
		return false;
	}
	
	

}
