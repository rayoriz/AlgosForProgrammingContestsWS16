package week4;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
              
              String[] input1 = br.readLine().split(" ");
              
              int numberOfVertices = Integer.parseInt(input1[0]);
              int edges = Integer.parseInt(input1[1]);
              Map<Integer, Vertex> vertexMap = new HashMap();

              for(int index=1;index<=numberOfVertices;index++){
            	 vertexMap.put(index,new Vertex(index));
              }
              
              for(int j =1; j<=edges;j++){
                  
                  String[] input2 = br.readLine().split(" ");
                  
                   vertexMap.get(Integer.parseInt(input2[0])).adjacencies.add(new Edge( vertexMap.get(Integer.parseInt(input2[1])) , Integer.parseInt(input2[2]) ) );
                  

              }
          
          
        computePaths(vertexMap.get(1)); // run Dijkstra
        System.out.println("Case #" + i + ": " + vertexMap.get(numberOfVertices).minDistance);

        br.readLine();
    }
    }
}