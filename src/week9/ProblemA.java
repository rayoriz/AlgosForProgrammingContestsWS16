package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * code inspired from multiple resources
 * especially geeks for geeks implementation of ford fulkerson
 * @author rayo
 *
 */
public class ProblemA {
	
	
	int vertice;

	//  constructor
	public ProblemA(int v) {
		this.vertice = v;
	}

	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= cases; i++) {
			
			
			/**
			 * The first line of the input contains an integer t. t test cases follow, each of them separated by a blank line.
Each test case starts with four integers n k m l, the number of chocolate fountains n, distribution stations k, wrapping
stations m and conveyor belts l. l lines follow describing conveyor belts, each containing three integers vi wi ci where
vi and wi are locations and ci
is the capacity of the conveyor belt connecting vi and wi
. The locations will be described
by integers where 1 to n are chocolate fountains, n + 1 to n + k are distribution stations, n + k + 1 to n + k + m are
wrapping stations.

			 */
			
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int k = Integer.parseInt(input[1]);
			int m = Integer.parseInt(input[2]);
			int l = Integer.parseInt(input[3]);
			
			int size = n + k + m + 2;
			int graph[][] = new int[size]
					[size];
			
			for (int index1 = 1; index1 <= l; index1++) {
				String[] inp2 = br.readLine().split(" ");
				graph[Integer.parseInt(inp2[0])]
						[Integer.parseInt(inp2[1])] = Integer
						.parseInt(inp2[2]);
				graph[Integer
				      .parseInt(inp2[1])]
				  [Integer.parseInt(inp2[0])] = Integer.
			parseInt(inp2[2]);
			}
		
			
			for (int index2 = 1; index2 <= n; index2++) {
				graph[0][index2] = 
						Integer
						.MAX_VALUE; // add sources
			}
			
			for (int index3 = n + k + 1; index3 <= n + k + m; index3++) {
				graph[index3][n + k + m + 1] = 
						Integer
						.MAX_VALUE; // add destinations
				
			}
			int vertices =size;
			
			ProblemA maxflow = new ProblemA(vertices);
			int maxFlow = maxflow.fordFulkerson(graph, 0, vertices - 1);
			
			System.out.println("Case #"+i+": "+maxFlow);
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
