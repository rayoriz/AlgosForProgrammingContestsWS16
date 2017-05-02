package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayDeque;
import java.util.Queue;

// inspired from multiple sources - a little hack on top of  kruskal to speed up
public class Question2{
		
public static void main(String[] args) throws NumberFormatException, IOException{
		
	BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
	int cases = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= cases; i++) {

			
			int nodes = Integer.parseInt(br.readLine());
			int[][] graph = new int[nodes][]; // 2 d array hack
			int[] distance = new int[nodes];
			int [] previous = new int[nodes];
			int[] time = new int[nodes];
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			Arrays.fill(previous, -1);
			
			int successors;
			
			for (int index1 = 0; index1 < nodes; index1++) {
				String[] input1 = br.readLine().split(" ");
				time[index1] = -1 * Integer.parseInt(input1[0]);
				successors = Integer.parseInt(input1[1]);
				graph[index1] = new int [successors];
				
				for (int j = 0; j < successors; j++)
					graph[index1][j] = Integer.parseInt(input1[j + 2]) - 1;
			}
			
			distance[0] = time[0];
		    Queue<Integer>	q = new ArrayDeque<Integer>();
			// add the first element
		    q.add(0);
		    
			while (!q.isEmpty()) {				
				int item = q.remove();						
				for (int currentNode : graph[item]) {
					if (distance[currentNode] > time[currentNode] + distance[item]) {
						distance[currentNode] = time[currentNode] + distance[item];
						previous[currentNode] = item;
						q.add(currentNode);
					}
				}
			}
			
			System.out.println("Case #"+i+": "+distance[nodes - 1] * -1);					
			
		    br.readLine();
		}
		
	}
	
}
