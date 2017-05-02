package week6;

//inspired from Geeks for Geeks map coloring
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProblemG {
	// limit on vertices, hence possible in finite time
	int V = 6;
	int color[];

	ProblemG(int V) {
		this.V = V;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			String input[] = br.readLine().split(" ");
			ProblemG Coloring = new ProblemG(Integer.parseInt(input[0]));
			
			int graph[][] = new int[Integer.parseInt(input[0])][Integer.parseInt(input[0])];
			int m = Integer.parseInt(input[2]);
			for (int j = 0; j < Integer.parseInt(input[1]); j++) {
				String line[] = br.readLine().split(" ");
				graph[Integer.parseInt(line[0]) - 1][Integer.parseInt(line[1]) - 1] = 1;
				graph[Integer.parseInt(line[1]) - 1][Integer.parseInt(line[0]) - 1] = 1;
			}
			System.out.print("Case #" + i + ": ");
			Coloring.graphColoring(graph, m);
			br.readLine();
		}

	}
	
	boolean graphColoring(int graph[][], int m) {
		color = new int[V];
		for (int i = 0; i < V; i++)
			color[i] = 0;

		if (!graphColoringUtil(graph, m, color, 0)) {
			System.out.println("impossible");
			return false;
		}
		printSolution(color);
		return true;
	}

	boolean validMove(int v, int graph[][], int color[], int c) {
		for (int i = 0; i < V; i++)
			if (graph[v][i] == 1 && c == color[i])
				return false;
		return true;
	}

	boolean graphColoringUtil(int graph[][], int m, int color[], int v) {
		if (v == V)
			return true;
		for (int c = 1; c <= m; c++) {
			if (validMove(v, graph, color, c)) {
				color[v] = c;
				if (graphColoringUtil(graph, m, color, v + 1))
					return true;
				color[v] = 0;
			}
		}
		return false;
	}

	void printSolution(int color[]) {
		for (int i = 0; i < V; i++)
			System.out.print(color[i] + " ");
		System.out.println();
	}
}