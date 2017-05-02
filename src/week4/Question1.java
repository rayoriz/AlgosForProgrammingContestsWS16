package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Question1 {

	public static void main(String[] args) throws NumberFormatException, IOException{

		BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			String[] input1 = br.readLine().split(" ");

			int vertices = Integer.parseInt(input1[0]);
			int edges = Integer.parseInt(input1[1]);

			boolean[] visited = new boolean[vertices];
			int [] distance = new int[vertices];
			int[]	previous = new int[vertices];
			ArrayList<Edge>[] graph = new ArrayList[vertices];

			Arrays.fill(distance, Integer.MAX_VALUE); // initialize array
			Arrays.fill(previous, -1); 

			distance[0] = 0;
			visited[0] = true;

			PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.cost, b.cost));
			Edge edge;

			for (int j = 0; j < edges; j++) {
			input1 = br.readLine().split(" ");

				edge = new Edge(
						Integer.parseInt(input1[0]) - 1,
						Integer.parseInt(input1[1]) - 1, 
						Integer.parseInt(input1[2]));

				if (graph[edge.source] == null)
					graph[edge.source] = new ArrayList<Edge>();
				if (graph[edge.destination] == null)
					graph[edge.destination] = new ArrayList<Edge>();

				graph[edge.source].add(edge);
				graph[edge.destination].add(edge);
			}

			if (graph[0] != null)
				pq.addAll(graph[0]);


			int source, destination;

			while (!pq.isEmpty() && previous[vertices - 1] == -1) {

				edge = pq.remove();
				source = edge.source;
				destination = edge.destination;

				if (visited[source] && visited[destination])
					continue;

				if (visited[destination]) {
					source = edge.destination;
					destination = edge.source;
				}

				if (edge.cost < distance[destination]) {
					distance[destination] = edge.cost;
					previous[destination] = source;
				}

				visited[destination] = true;

				for (Edge e : graph[destination]) {
					pq.add(new Edge(e.source, e.destination, e.cost + distance[destination]));
				}
			}


			System.out.println("Case #"+i+": "+distance[vertices - 1]);					
			br.readLine();
		}

	}




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

