package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

	public class QuestionC {

		// Graph implementation inspired from Geeks for Geeks
	  static class Vertex{
	    public final int scores;
	    public final HashSet<Edge> inwardEdges;
	    public final HashSet<Edge> outwardEdges;
	    public Vertex(int scores) {
	      this.scores = scores;
	      inwardEdges = new HashSet<Edge>();
	      outwardEdges = new HashSet<Edge>();
	    }
	    public Vertex addEdge(Vertex node){
	      Edge e = new Edge(this, node);
	      outwardEdges.add(e);
	      node.inwardEdges.add(e);
	      return this;
	    }
	  }

	  static class Edge{
	    public final Vertex from;
	    public final Vertex to;
	    public Edge(Vertex from, Vertex to) {
	      this.from = from;
	      this.to = to;
	    }
	  }
	  public static void main(String[] args) throws IOException {
	    
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int cases = Integer.parseInt(br.readLine());
			for(int i=1;i<=cases;i++)
			{
				String input[]=br.readLine().split(" ");
				int vertices=Integer.valueOf(input[0]);
				int paths=Integer.valueOf(input[1]);
				int maxScore=0;
				String scores[]=br.readLine().split(" ");
				ArrayList<Vertex> graph = new ArrayList<Vertex>();
				// Initiate graph with vertices
				for(int index1=0;index1<vertices;index1++){
					graph.add(new Vertex(Integer.valueOf(scores[index1])));
				}
				
				// add all the edges of the graph
				for(int index2=0;index2<paths;index2++){
					String pair[]=br.readLine().split(" ");
					int from=Integer.valueOf(pair[0]);
					int to=Integer.valueOf(pair[1]);
					graph.get(from-1).addEdge(graph.get(to-1));
				}
				
		// Vertex with inward edges are added to a set		
	    HashSet<Vertex> vertexSet = new HashSet<Vertex>(); 
	    for(Vertex node : graph){
	      if(node.inwardEdges.size() == 0){
	        vertexSet.add(node);
	      }
	    }

	    while(!vertexSet.isEmpty()){
	      Vertex node = vertexSet.iterator().next();
	      maxScore=maxScore+node.scores;
	      vertexSet.remove(node);
	      // iterator implemetation from stackoverflow
	      for(Iterator<Edge> it = node.outwardEdges.iterator();it.hasNext();){
	        Edge e = it.next();
	        Vertex v = e.to;
	        it.remove();
	        v.inwardEdges.remove(e);
	        if(v.inwardEdges.isEmpty()){
	          vertexSet.add(v);
	        }
	      }
	    }

		System.out.println("Case #"+i+": "+maxScore);
		br.readLine();
	   
}
}}