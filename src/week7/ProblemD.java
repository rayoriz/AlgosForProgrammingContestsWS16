package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//http://stackoverflow.com/questions/5514366/how-to-know-if-a-line-intersects-a-rectangle
// and multiple other sources

class Vertex { // vertex
	int x;
	int y;
	boolean visited = false;
	double dist = Double.MAX_VALUE;
	Vertex previousVertex = this;
	HashMap<Vertex, Double> adjacencyList = new HashMap<Vertex, Double>();

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Rectangle { // rectangle class - nothing inbuilt
	int left;
	int right;
	int bottom;
	int top;

	public Rectangle(int left, int right, int bottom, int top) {
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.top = top;
	}
}

public class ProblemD {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int cases = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= cases; t++) {
			// priority queue implementation, takes time, but works
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
				@Override
				public int compare(Vertex n1, Vertex n2) {
					if (n1.dist > n2.dist) {
						return 1;
					}
					if (n1.dist < n2.dist) {
						return -1;
					}
					return 0;
				}
			});
			String[] input1 = br.readLine().split(" ");
			int obs = Integer.parseInt(input1[2]);
			Vertex[] vertices = new Vertex[obs * 4 + 2];
			Rectangle[] rectangles = new Rectangle[obs];
			for (int k = 0; k < obs; k++) {
				input1 = br.readLine().split(" ");
				int v1 = Integer.parseInt(input1[0]);
				int v2 = Integer.parseInt(input1[1]);
				int v3 = Integer.parseInt(input1[2]);
				int v4 = Integer.parseInt(input1[3]);
				vertices[k * 4] = new Vertex(v1, v2);
				vertices[k * 4 + 1] = new Vertex(v1 + v3, v2);
				vertices[k * 4 + 2] = new Vertex(v1, v2 + v4);
				vertices[k * 4 + 3] = new Vertex(v1 + v3, v2 + v4);
				rectangles[k] = new Rectangle(v1, v1 + v3, v2, v2 + v4);
			}

			input1 = br.readLine().split(" ");
			int value1 = Integer.parseInt(input1[0]);
			int value2 = Integer.parseInt(input1[1]);
			Vertex start = new Vertex(value1, value2);
			vertices[obs * 4] = start;

			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			input1 = line.split(" ");
			value1 = Integer.parseInt(input1[0]);
			value2 = Integer.parseInt(input1[1]);
			Vertex last = new Vertex(value1, value2);
			vertices[obs * 4 + 1] = last;

			pq.add(start);
			start.dist = 0.0;
			ArrayList<Vertex> sol = new ArrayList<Vertex>();
			// kinda like dijistras
			while (!pq.isEmpty()) {
				Vertex v = pq.remove();
				v.visited = true;
				
/*				 for (int i = 1; i <= number_of_nodes; i++)
			            for (int j = 1; j <= number_of_nodes; j++)
			                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
			 
			        for (int i = 1; i <= number_of_nodes; i++)
			        {
			            distances[i] = Integer.MAX_VALUE;
			        }
			 
			        priorityQueue.add(new Node(source, 0));
			        distances[source] = 0;
			        while (!priorityQueue.isEmpty())
			        {
			            evaluationNode = getNodeWithMinimumDistanceFromPriorityQueue();
			            settled.add(evaluationNode);
			            evaluateNeighbours(evaluationNode);*/
				
				if (v == last)
					break;
				toLoop1: for (int index1 = 0; index1 < vertices.length; index1++) {
					Vertex n = vertices[index1];
					if (n.visited)
						continue;
					for (int j = 0; j < obs; j++) {
						Rectangle rect = rectangles[j];
						// check if rectangle intersects
						if (intersects(rect, n, v))
							continue toLoop1;
					}
					v.adjacencyList.put(n, Math.sqrt(Math.pow(n.x - v.x, 2) + Math.pow(n.y - v.y, 2)));
				}
				for (Map.Entry<Vertex, Double> ne : v.adjacencyList.entrySet()) {
					double totalDist = v.dist + ne.getValue();
					Vertex neighbor = ne.getKey();
					if (neighbor.dist > totalDist && !neighbor.visited) {
						neighbor.dist = totalDist;
						neighbor.previousVertex = v;
						pq.add(neighbor);
					}
				}
			}
			Vertex temp = last;
			while (temp != start) {
				sol.add(temp);
				temp = temp.previousVertex;
			}
			sol.add(start);
			sb.append("Case #");
			sb.append(t);
			sb.append(":");
			for (int i = sol.size() - 1; i >= 0; i--) {
				sb.append(" (");
				sb.append(sol.get(i).x);
				sb.append(",");
				sb.append(sol.get(i).y);
				sb.append(")");
			}
			sb.append("\n");
			line = br.readLine();
		}
		System.out.println(sb);
	}

	/*
	 * public bool Intersects(Point a, Point b, Rectangle r) { var line = new
	 * Line(a, b);
	 * 
	 * if (r.Left > line.XMax || r.Right < line.XMin) { return false; }
	 * 
	 * if (r.Top < line.YMin || r.Bottom > line.YMax) { return false; }
	 * 
	 * var yAtRectLeft = line.CalculateYForX(r.Left); var yAtRectRight =
	 * line.CalculateYForX(r.Right);
	 * 
	 * if (r.Bottom > yAtRectLeft && r.Bottom > yAtRectRight) { return false; }
	 * 
	 * if (r.Top < yAtRectLeft && r.Top < yAtRectRight) { return false; }
	 * 
	 * return true; }
	 */

	public static boolean intersects(Rectangle rectangle, Vertex v, Vertex vMin) {
		int xMin, yMin, xMax, yMax;
		Vertex left, right;
		if (v.x < vMin.x) {
			xMin = v.x;
			xMax = vMin.x;
			left = v;
			right = vMin;
		} else {
			xMin = vMin.x;
			xMax = v.x;
			left = vMin;
			right = v;
		}
		if (rectangle.left >= xMax || rectangle.right <= xMin)
			return false;
		if (v.y < vMin.y) {
			yMin = v.y;
			yMax = vMin.y;
		} else {
			yMin = vMin.y;
			yMax = v.y;
		}
		if (rectangle.top <= yMin || rectangle.bottom >= yMax)
			return false;
		double m = ((double) right.y - left.y) / (right.x - left.x);
		double b = (double) v.y - m * v.x;
		double yL = m * rectangle.left + b;
		double yR = m * rectangle.right + b;
		if (rectangle.bottom >= yL && rectangle.bottom >= yR)
			return false;
		if (rectangle.top <= yL && rectangle.top <= yR)
			return false;
		return true;
	}

}
