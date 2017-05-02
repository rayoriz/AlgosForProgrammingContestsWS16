package z;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


// Inspired from Stack overflow
class Vertex implements Comparable<Vertex>
{
    public final int name;
    public List<Edge> adjacencies = new ArrayList();
    public int minDistance = Integer.MAX_VALUE;
    public Vertex previous;
    public Vertex(int argName) { name = argName; }
  //  public int toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Integer.compare(minDistance, other.minDistance);
    }

}


class Edge
{
    public final Vertex target;
    public final int weight;
    public Edge(Vertex argTarget, int argWeight)
    { target = argTarget; weight = argWeight; }
}

public class DijkstraPQ
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
        Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
            vertexQueue.remove(u);

            v.minDistance = distanceThroughU ;
            v.previous = u;
            vertexQueue.add(v);
        }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws IOException
    {

    	
    	  InputStreamReader inputReader = new InputStreamReader(System.in);
          BufferedReader br = new BufferedReader(inputReader); 

          
          int lines = Integer.parseInt(br.readLine());
          
	for(int i=1; i<=lines; i++){
			
			String[] input1Array = br.readLine().split(" ");
			
			int vertices = Integer.parseInt(input1Array[0]);
			int edges = Integer.parseInt(input1Array[1]);
            Map<Integer, Vertex> vertexMap = new HashMap();


			
			int dungeonNumber = Integer.parseInt(input1Array[2]);
			
			int[] dungeons = new int[dungeonNumber+1];
			
			dungeons[0]=1;
			
			for(int index4=1; index4<=dungeonNumber; index4++ ){
				
				dungeons[index4] = Integer.parseInt(br.readLine());
			}
			
            for(int index=1;index<=vertices;index++){
          	 vertexMap.put(index,new Vertex(index));
            }
			
			for(int indexk=1; indexk<=edges; indexk++){
				
			      String[] input2 = br.readLine().split(" ");
                  
                  vertexMap.get(Integer.parseInt(input2[0])).adjacencies.add(new Edge( vertexMap.get(Integer.parseInt(input2[1])) , Integer.parseInt(input2[2]) ) );
                 

			}
			
			
			// form a adjacency matrix using distances only between the dungeons
			
			int[][] dungeonMatrix = new int[dungeons.length][dungeons.length];
			
			for(int c=0;c<dungeons.length; c++){
				
		        computePaths(vertexMap.get(dungeons[c])); // run Dijkstra
		        
		        for(int c2=0;c2<dungeons.length;c2++){
		        	
		        	dungeonMatrix[c][c2] = vertexMap.get(c2+1).minDistance;
		        }

			}
			
			System.out.println(Arrays.deepToString(dungeonMatrix));
            System.out.println();
		/*	int cost = primMST(dungeonMatrix,dungeons.length);
			

			System.out.println("Case #"+i+": "+cost);
			br.readLine();*/
		}
          
         
    }
    

}