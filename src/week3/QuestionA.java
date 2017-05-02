package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QuestionA {

	// code inspired from prim's implementation in geeksfor geeks
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases= Integer.parseInt(br.readLine());
		
		for(int i=1;i<=cases;i++){
			
			int numberOfPlanets = Integer.parseInt(br.readLine());
			int[][] graph = new int[numberOfPlanets][numberOfPlanets];
		//	Map <Integer,String> graphMap = new HashMap<>();
			Map <Integer,int[]> graphMap = new HashMap<>();


			
			for(int j=1;j<=numberOfPlanets;j++){
				
				String[] coord = br.readLine().split(" ");
				int[] coOrdinates = new int[3];
				coOrdinates[0] = Integer.parseInt(coord[0]);
				coOrdinates[1] = Integer.parseInt(coord[1]);
				coOrdinates[2] = Integer.parseInt(coord[2]);				
				graphMap.put(j, coOrdinates);
				
			}
			
			
			formDistanceGraphFromMap(graphMap,graph);
			
			int cost = primMST(graph,numberOfPlanets);
			
//			for(int[] row : graph) {
//	            printRow(row);
//	        }
//			
			
			System.out.println("Case #"+i+": "+cost);
			br.readLine();
		}
		
	}

	
	 static int primMST(int graph[][], int V)
	    {
	        // Array to store constructed MST
	        int parent[] = new int[V];
	 
	        // Key values used to pick minimum weight edge in cut
	        int key[] = new int [V];
	 
	        // To represent set of vertices not yet included in MST
	        Boolean mstSet[] = new Boolean[V];
	 
	        // Initialize all keys as INFINITE
	        for (int i = 0; i < V; i++)
	        {
	            key[i] = Integer.MAX_VALUE;
	            mstSet[i] = false;
	        }
	 
	        // Always include first 1st vertex in MST.
	        key[0] = 0;     // Make key 0 so that this vertex is
	                        // picked as first vertex
	        parent[0] = -1; // First node is always root of MST
	 
	        // The MST will have V vertices
	        for (int count = 0; count < V-1; count++)
	        {
	            // Pick thd minimum key vertex from the set of vertices
	            // not yet included in MST
	            int u = minKey(key, mstSet, V);
	 
	            // Add the picked vertex to the MST Set
	            mstSet[u] = true;
	 
	            // Update key value and parent index of the adjacent
	            // vertices of the picked vertex. Consider only those
	            // vertices which are not yet included in MST
	            for (int v = 0; v < V; v++)
	 
	                // graph[u][v] is non zero only for adjacent vertices of m
	                // mstSet[v] is false for vertices not yet included in MST
	                // Update the key only if graph[u][v] is smaller than key[v]
	                if (graph[u][v]!=0 && mstSet[v] == false &&
	                    graph[u][v] <  key[v])
	                {
	                    parent[v]  = u;
	                    key[v] = graph[u][v];
	                }
	        }
	 
	        // print the constructed MST
	       int cost = findCost(parent, V, graph);
	       return cost;
	    }
	 
	private static int findCost(int[] parent, int v, int[][] graph) {
		
		int cost = 0;
	        for (int i = 1; i < v; i++){
	          cost += graph[i][parent[i]];
	        }
			return cost;
		
	}


	// A utility function to find the vertex with minimum key
	    // value, from the set of vertices not yet included in MST
	    static int minKey(int key[], Boolean mstSet[], int V)
	    {
	        // Initialize min value
	        int min = Integer.MAX_VALUE, min_index=-1;
	 
	        for (int v = 0; v < V; v++)
	            if (mstSet[v] == false && key[v] < min)
	            {
	                min = key[v];
	                min_index = v;
	            }
	 
	        return min_index;
	    }

	private static void formDistanceGraphFromMap(Map<Integer, int[]> graphMap, int[][] graph) {

		for(int i=0; i<graphMap.size(); i++){
			
			for(int j=0; j<graphMap.size(); j++){
				
				/*String[] firstPlanet = graphMap.get(i+1).split(" ");
				String[] secondPlanet = graphMap.get(j+1).split(" ");

				
				
				graph[i][j] = Math.abs(Integer.parseInt(firstPlanet[0])-Integer.parseInt(secondPlanet[0])) +
						Math.abs(Integer.parseInt(firstPlanet[1])-Integer.parseInt(secondPlanet[1])) +
						Math.abs(Integer.parseInt(firstPlanet[2])-Integer.parseInt(secondPlanet[2]));*/
				int[] firstPlanet = graphMap.get(i+1);
				int[] secondPlanet = graphMap.get(j+1);

				
				graph[i][j] = Math.abs(firstPlanet[0]-secondPlanet[0])+
						Math.abs(firstPlanet[1]-secondPlanet[1])+
						Math.abs(firstPlanet[2]-secondPlanet[2]);
						
			}
			
		}
		
	}
	
	 public static void printRow(int[] row) {
	        for (int i : row) {
	            System.out.print(i);
	            System.out.print("\t");
	        }
	        System.out.println();
	    }
}
