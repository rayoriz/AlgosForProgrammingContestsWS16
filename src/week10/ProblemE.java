package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * using dijkistra to solve the problem, code inspired from dijkstra's implementaion from multiple sources in net
 * 
 * @author rayo
 *
 */
class Vertex {

	int id;
	int steps = 0;
	ArrayList<Integer> adjacentDistances = new ArrayList<Integer>();
	// initialize to maximum 
	int connectingDistance = Integer.MAX_VALUE;
	int connections;
	ArrayList<Integer> adjacencyList = new ArrayList<Integer>();
	Boolean flag;
	int nextDistance;
	HashMap<Integer, Integer> distanceMap;

	/**
	 * constructor with more params
	 * 
	 * @param nodes
	 * @param steps
	 * @param distance
	 */
	public Vertex(Vertex nodes, int steps, int distance, int nextDistance) {
		this.id = nodes.id;
		this.steps = steps;
		this.flag = false;
		this.adjacencyList = nodes.adjacencyList;
		this.nextDistance = nextDistance;
		this.adjacentDistances = nodes.adjacentDistances;
		this.connectingDistance = distance;
	}

	/**
	 * constructor
	 * 
	 * @param id
	 */
	public Vertex(int id, Boolean k) {
		this.id = id;
		// empty hashmap
		if(k)
		distanceMap = new HashMap<Integer, Integer>();
	}

}
/**
 * 
 * @author rayo
 *
 */
public class ProblemE {

	/**
	 * main block
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		StringBuilder builder = new StringBuilder();
		/**
		 * The first line of the input contains an integer t. t test cases
		 * follow. Each test case begins with a line containing three integers n
		 * m s, where n is the amount of places (indexed from 1 to n), m is the
		 * amount of connecting flights of stairs and s is the point Lea chose
		 * to start in. m lines follow. The i-th line consists of three integers
		 * ai , bi , ci separated by spaces, meaning that there is a flight of
		 * stairs from place ai to bi with ci steps. All flights of stairs can
		 * be used in both directions, but are only given going upward, i.e. to
		 * go from ai to bi you would go ci steps up, and to go from bi to ai
		 * you would go ci steps down.
		 * 
		 */
		for (int i = 1; i <= cases; i++) {
			// initialize the priority queue
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.connectingDistance 
							- o2.connectingDistance;
							}
						});
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			// initialize the graph as vertex array
			Vertex[] graph = new Vertex[n + 1];		
			for (int index1 = 1; index1 <= n; index1++){
				//add the new vertices to the graph
				graph[index1] = new Vertex(index1, true);
			}		
			
			for (int index2 = 0; index2 < m; index2++) {
				input = br.readLine().split(" ");
				int ai = Integer.parseInt(input[0]);
				int bi = Integer.parseInt(input[1]);
				int ci = Integer.parseInt(input[2]);
				
				Vertex vertexA = graph[ai];
				vertexA.flag = true;
				vertexA.adjacencyList.add(bi);
				vertexA.adjacentDistances.add(ci);
				
				Vertex vertexB = graph[bi];
				vertexB.flag = true;
				vertexB.adjacencyList.add(ai);
				vertexB.adjacentDistances.add(-ci);
			}
			
			// setting the start node
			Vertex initialVertex = graph[s];
			pq.add(initialVertex);
			initialVertex.connectingDistance = 0;

			// run dijkistra's
			label1: while (!pq.isEmpty()) {
				
				//pop least vertex
				Vertex v1 = pq.remove();
				
				for (int index3   = 0; index3 < 
						v1.adjacencyList.size(); index3++) {
					Vertex adjacentVertex = 
							graph[v1.adjacencyList
							      .get(index3)];
					int distance = v1.adjacentDistances.get(index3);
					int nextDistance = v1.steps 
							+ distance;
					int totalDistance = v1.connectingDistance + 1;
					if (adjacentVertex.id == s) {
						// with the starting vertex 
						if (nextDistance == 0)
							continue; // loop forward
						else {
							initialVertex.connectingDistance = totalDistance;
							//startover
							break label1; 
						}
					}
					if (!adjacentVertex.distanceMap.containsKey(nextDistance)
							|| 
							totalDistance < 
							adjacentVertex.distanceMap
							.get(nextDistance)) {
						
						Vertex connectingVertex = 
						new Vertex(adjacentVertex, nextDistance, totalDistance, distance);
						// add new connection
						adjacentVertex.distanceMap.put(nextDistance, totalDistance);
						pq.add(connectingVertex);
					}
				}
			}

			/**
			 * For each test case, print a line containing Case #i: possible
			 * if there is no path from s to s such that the sum of steps is
			 * different from 0. Otherwise, print Case #i: k, where k is a
			 * minimal number of flights of stairs Lea can take that lead her
			 * back to s with a step-sum different from 0. This number should
			 * correspond exactly to the path she took, so if she takes the same
			 * flight of stairs more than once, it is also counted again.
			 */
			
			if(initialVertex.connectingDistance == 0 ){
				System.out.println("Case #"+i+": possible");
			}else{
				System.out.println("Case #"+i+": "+initialVertex.connectingDistance);
			}
			br.readLine();

		}
	}

}
