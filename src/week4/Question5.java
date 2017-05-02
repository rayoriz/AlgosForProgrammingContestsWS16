package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

// code inspired from multiple sources - Kruskal and Prims implementaion using priority queues
public class Question5 {

	
public static void main(String[] args) throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader (new InputStreamReader ( System.in ));
		int cases = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= cases; i++) {
			
			String[] input1Array = br.readLine().split(" ");
			
			int nodes = Integer.parseInt(input1Array[0]);
			int edges = Integer.parseInt(input1Array[1]);
			int dungeonRooms = Integer.parseInt(input1Array[2]);
			
			int[][] dungeonMatrix = new int[nodes][nodes];
			ArrayList<Edge>[] nodeList = new ArrayList[nodes];
			 boolean[] dungeon = new boolean[nodes];
			 PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.cost, b.cost));
			Edge edge;
			
			for (int j = 0; j < dungeonRooms; j++){
				dungeon[Integer.parseInt(br.readLine()) - 1] = true;
			}
			
			for (int k = 0; k < edges; k++) {
				input1Array = br.readLine().split(" ");
				
				edge = new Edge(
						Integer.parseInt(input1Array[0]) - 1,
						Integer.parseInt(input1Array[1]) - 1, 
						Integer.parseInt(input1Array[2]));
				
				if (nodeList[edge.source] == null)
					nodeList[edge.source] = new ArrayList<Edge>();
				if (nodeList[edge.destination] == null)
					nodeList[edge.destination] = new ArrayList<Edge>();
				
				nodeList[edge.source].add(edge);
				nodeList[edge.destination].add(edge);
			}
			
			

			
			
			for (int index = 0; index < nodes; index++) {
				if (!dungeon[index] && index != 0)
					continue;
				
				boolean[] visited = new boolean[nodes];
				boolean[] ninjaVisited = new boolean[nodes];
				pq.clear();
				if (nodeList[index] != null)
					pq.addAll(nodeList[index]);
				
				visited[index] = true;
				
				int sourceNode, destinationNode;
				int totalFreedNinjas = dungeon[index] ? 1 : 0;
				
				// find shortest paths between the different dungeons - added to dungeon matrix
				while (!pq.isEmpty()) {
					
					edge = pq.remove();
					sourceNode = edge.source;
					destinationNode = edge.destination;
					
					if (visited[sourceNode] && visited[destinationNode])
						continue;
					
					if (visited[destinationNode]) {
						sourceNode = edge.destination;
						destinationNode = edge.source;
					}
					
					if (dungeonMatrix[index][destinationNode] == 0 || edge.cost < dungeonMatrix[index][destinationNode]) {
						dungeonMatrix[index][destinationNode] = edge.cost;
						dungeonMatrix[destinationNode][index] = edge.cost;
					}
					
					visited[destinationNode] = true;
					
					if (dungeon[destinationNode] && !ninjaVisited[destinationNode]) {
						ninjaVisited[destinationNode] = true;
						if (++totalFreedNinjas == dungeonRooms)
							break;
					}
					
					for (Edge e : nodeList[destinationNode]) {
						if (!visited[e.source] || !visited[e.destination])
							pq.add(new Edge(e.source, e.destination, e.cost + dungeonMatrix[index][destinationNode]));
					}
				}
			}
			
			pq.clear();
			boolean[] visited = new boolean[nodes];
			int minimumSneaks = Integer.MAX_VALUE;
			int index = 0;
			// find the closes dungeons to starting point
			for (int index3 = 0; index3 < nodes; index3++)
				if (dungeon[index3] && minimumSneaks > dungeonMatrix[0][index3]) {
					index = index3;
					minimumSneaks = dungeonMatrix[0][index3];
				}
			
			visited[index] = true;
			visited[0] = true;
			
			for (int j = 0; j < nodes; j++) {
				if (dungeon[j] && !visited[j])
					pq.add(new Edge(index, j, dungeonMatrix[index][j]));
			}
			
			dungeonRooms--;
			// use prims to find the shortest path between all the dungeons
			while  (dungeonRooms > 0) {
				edge = pq.poll();
				if (visited[edge.destination]) {
					continue;
				}
				
				minimumSneaks += edge.cost;
				visited[edge.destination] = true;
				
				for (int j = 0; j < nodes; j++) {
					if (dungeon[j] && !visited[j])
						pq.add(new Edge(edge.destination, j, dungeonMatrix[edge.destination][j]));
				}
				dungeonRooms--;
			}
			

			
		System.out.println("Case #"+i+": "+minimumSneaks);
			
			
				br.readLine();
		}
		
	}
	// edge class
static class Edge {
	int source;
	int destination;
	int cost;
	Edge(int i, int j, int cost) {
		this.source = i;
		this.destination = j;
		this.cost = cost;
	}
}

}
