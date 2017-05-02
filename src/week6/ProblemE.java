package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// inspired from multiple sources -geeks for geeks,stack overflow - shortest path algorithm
public class ProblemE {


	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			int totalPath = 100000;
			int path = 0;
			int n = Integer.parseInt(br.readLine());
			int grid[] = new int[n - 1];
			int adjacencyMatrix[][] = new int[n][n];
			int shortestPath[] = new int[n - 1];
			int j;
			int k = 1;
			int index, index2;


			for (j = 0; j < n; j++) {
				String input[] = br.readLine().split(" ");
				for (k = 0; k < n; k++) {
					adjacencyMatrix[j][k] = Integer.parseInt(input[k]);
				}
			}
			if (n > 1) {
				for (index = 0; index < n - 1; index++)
					grid[index] = index + 1;
			}
			do { 
				path = adjacencyMatrix[0][grid[0]];
				for (index2 = 0; index2 < n - 2; index2++) {
					path = path + adjacencyMatrix[grid[index2]][grid[index2 + 1]];
				}
				path = path + adjacencyMatrix[grid[n - 2]][0];
				if (path < totalPath) {
					totalPath = path;
					shortestPath = grid.clone();
				}
			} while (nextStep(grid));
			String result = "1 ";
			for (k = 0; k < n - 1; k++) {
				Integer t = shortestPath[k] + 1;
				result = result.concat(t.toString() + " ");
			}
			System.out.println("Case #" + i + ": " + result);
			br.readLine();

		}
	}
	
	static boolean nextStep(int[] array) {
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;

		if (i <= 0){
			return false;
		}

		int j = array.length - 1;
		while (array[j] <= array[i - 1]){
			j--;
		}

		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		return true;
	}
}