package week9;

import java.io.*;
import java.util.LinkedList;

/**
 * code inspired from multiple resources
 * especially geeks for geeks implementation of ford fulkerson
 * @author rayo
 *
 */
class ProblemB {
	static int vertice;

	ProblemB(int v) {
		vertice = v;
	}

	public static void main(String[] args) throws java.lang.Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {
			
			/**
			 * The first line of the input contains an integer t. t test cases follow.
Each test case begins with a line containing three integers l, the amount of available policemen, n, the amount of
intersections and m, the amount of roads. m lines follow, each consisting of three integers i, j, k, specifying a road
from intersection i to j with k being the amount of policemen it takes to construct a roadblock on it. All roads are
useable in both directions. The robber always starts at intersection 1 and wants to get to the border (intersection n).
Every test case ends with a blank line.
			 */
			String input[] = br.readLine().split(" ");
			int p = Integer.parseInt(input[0]);
			int n = Integer.parseInt(input[1]);
			int e = Integer.parseInt(input[2]);
			int g[][] = new int[n][n];
			int index1;

			for (index1 = 0; index1 < e; index1++) {
				
				String inp2[] = br.readLine().split(" ");
				int source = Integer.parseInt(inp2[0]);
				int destination = Integer.parseInt(inp2[1]);
				int weight = Integer.parseInt(inp2[2]);
				// initialize the graph
				g[source - 1][destination - 1] +=
						weight;
				g[destination - 1][source - 1] +=
						weight;
			}
			ProblemB maxFlow = new ProblemB(n);
			
			int maxflow = maxFlow.
					fordFulkerson
					(g, 0, n - 1);
		
			String res = "";
			if(maxflow>p){
				res = "no";
			}else{
				res = "yes";
			}
			System.out.println("Case #"+i+": "+res);
			
			br.readLine();

		}
	}

	int fordFulkerson(int gMatrix[][], int s, int t) {
		int u, v;

	    // Create a residual graph and fill the residual graph with
	    // given capacities in the original graph as residual capacities
	    // in residual graph
		int rGraph[][] = new int[vertice][vertice];
		for (u = 0; u < vertice; u++)// Residual graph where rGraph[i][j] indicates 
            // residual capacity of edge from i to j (if there
            // is an edge. If rGraph[i][j] is 0, then there is not)  
			for (v = 0; v < vertice; v++)
				rGraph[u][v] = gMatrix[u][v];
		int parent[] = new int[vertice];// This array is filled by BFS and to store path

		int max_flow = 0; // There is no flow initially
	    // Augment the flow while tere is path from source to sink

		while (bfs(rGraph, s, t, parent)) {
			// Find minimum residual capacity of the edhes along the
	        // path filled by BFS. Or we can say find the maximum flow
	        // through the path found.
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}
			 // update residual capacities of the edges and reverse edges
	        // along the path
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}
	        // Add path flow to overall flow

			max_flow += path_flow;
		}
	    // Return the overall flow

		return max_flow;
	}
	
	/* Returns true if there is a path from source 's' to sink 't' in
	  residual graph. Also fills parent[] to store the path */
	boolean bfs(int rGraph[][], int source, int t, int parent[]) {
	    // Create a visited array and mark all vertices as not visited

		boolean visited[] = new boolean[vertice];
		
		// loop for visiting
		for (int i = 0; i < vertice; ++i)
			// Create a queue, enqueue source vertex and mark source vertex
		    // as visited
			visited[i] = false;
		
		// create LL
		LinkedList<Integer> queue = 
				new LinkedList<Integer>();
		
		queue.add(source);
		
		visited[source] = true;
		parent[source] = -1;
		// looping for bfs
		while (queue.size() != 0) {
			
			// use pq
			int u = queue.poll();

			for (int v = 0; v < vertice; v++) {
				if (visited[v] == false && rGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		
	    // If we reached sink in BFS starting from source, then return
	    // true, else false
		return (visited[t] == true);
	}
	// Returns the maximum flow from s to t in the given graph
}
