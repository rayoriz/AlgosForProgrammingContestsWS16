package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Code inspired from multiple sources:
 * stack overflow and https://sites.google.com/site/indy256/algo/dinic_flow
 *  *  * @author rayo
 *
 */
public class ProblemD {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		// initalize arrays
		Tournament[] matches = new Tournament[cases];
		/**
		 * The first line of the input contains an integer t. t test cases follow, each of them separated by a blank line.
Each test case starts with two integers, n and m, the number of teams n indexed from 1 to n and the number of matches
m that are scheduled to be played. The next line contains n space separated integers wi
, where wi
is the number of
wins team i already has. m lines follow describing the matches that are to be played. Line i contains two distinct
integers ai and bi
, the two teams that play each other in match i.
		 */
		
		for (int i = 0; i < cases; i++) {
			String[] input = br.readLine().split(" ");
			int teamNo = Integer.parseInt(input[0]);
			int matchNo = Integer.parseInt(input[1]);
			
			Tournament t = new Tournament(teamNo);
			matches[i] = t;

			String[] s = br.readLine().split(" ");
			// read teams
			for (int id = 1; id <= teamNo; id++) {
				t.teamArray[id - 1] = new Team(id,
						Integer.parseInt
						(s[id - 1]));
			}

			for (int mId = 0; mId < matchNo; mId++) {
				
				String[] outComes =
						br.readLine().split(" ");
				int first = Integer.parseInt(outComes[0]);
				int second = Integer.parseInt(outComes[1]);
				
				t.matchGraph[first][second] += 1;
				t.matchGraph[second][first] += 1;
				t.teamArray[first - 1].maxWins += 1;
				t.teamArray[second - 1].maxWins += 1;
			}

			t.results();

			String result = "";
			for (Team team : t.teamArray) {
				if (team.win)
					result += "yes ";
				else {
					result += "no ";
				}
			}

			System.out.println("Case #" + (i + 1) + ": " + result);

			br.readLine();
		}

	}
 }

/**
 * class for the tournament entity
 * @author rayo
 *
 */
class Tournament {
	int n;
	int[][] matchGraph;
	Team[] teamArray;
	int size;
	
	/**
	 * constructor
	 * @param teams
	 */
	
	public Tournament(int teams) {
		this.n = teams;
		
		matchGraph = new 
				int[teams + 1][teams + 1];
		// initialize the graph
		for (int i = 0; i < matchGraph.length; i++) {
			// fill with zeroes
			
			Arrays.fill(matchGraph[i], 0);
		}
		
		teamArray = new Team[teams];
	}

	/**
	 * find results
	 */
	public void results() {
		
		Team[] temp = new Team[n];
		
		System.arraycopy(teamArray, 0,
				temp, 0, n);
// sort the results
		  Arrays.sort(temp);

		int maxVal = 0;
		for (Team t : temp) {
			
			maxVal = t.wins > maxVal 
					? t.wins 
				:maxVal;
		}

		for (Team t : temp) {
			if (t.maxWins < maxVal) {
				t.win = false;
				continue;
			}
			int clashes = 0;
			int id = t.id;
			for (int i = 1; i < matchGraph.length; i++) {
				for (int j = 1; j < matchGraph.length; j++) {
					if (i != id && 
							j != id && 
							matchGraph[i][j] != 0 && 
							i < j) {
						
						clashes += matchGraph[i][j];
					}
				}
			}
			Vertice s = new Vertice(0);
			
			int maxTarget = 600;
			Graph graph = new Graph(0, maxTarget);
			Vertice r = new Vertice(maxTarget);
			// add edges to graph
			
			graph.vertex.put(0, s);
			graph.vertex.put(maxTarget, r);
			int maxVertices = 199;

			for (int i = 1; i < matchGraph.length; i++) {
				if (i != id) {
					Vertice teamNode = new Vertice(i);
					graph.vertex.put(i
							, teamNode);
					teamNode
					.edgeList.add(new Edge(i, maxTarget,							
				teamArray[id - 1]
						.maxWins - teamArray[i - 1]
								.wins, r
								.edgeList.size()));
					r.edgeList.add(new Edge(maxTarget,
							i, 0, teamNode
							.edgeList.size() - 1));
				}
				for (int j = 1; j < matchGraph.length; j++) {
					
					if (i != id 
							&& j != id) {
						if (i > j 
								&& matchGraph[i][j] > 0) {
							
							// initialize vertex
							Vertice matchNode = 
									new Vertice(++maxVertices);
							graph.vertex
							.put(maxVertices, matchNode);

							matchNode.edgeList
							.add(new Edge(maxVertices, 0, 0, matchNode
									.edgeList.size()));
							
							s.edgeList
									.add(new Edge(0, maxVertices,
											matchGraph[i][j], 
											matchNode.edgeList.size() - 1));

							matchNode.edgeList
							.add(new Edge(maxVertices, i, Integer.MAX_VALUE
									, graph.vertex.get(i)
									.edgeList.size()));
							
							graph.vertex.get(i)
							.edgeList
									.add(new Edge(i, maxVertices, 0,
											matchNode.edgeList.size()
											- 1));

							matchNode.edgeList.add(
									new Edge(maxVertices, 
											j, Integer.MAX_VALUE, 
											graph.vertex.get(j)
											.edgeList.size()));
							
							
							graph.vertex.get(j)
							.edgeList
									.add(new Edge(j, maxVertices,
											0, matchNode.edgeList.size() 
											- 1));
						}
					}
				}
			}

			graph.doDinitz();
			if (graph.maxFlow == clashes) {
				t.win = true;
			} else {
				t.win = false;
			}
		}
	}
}
// define the vertices in the flow graph
/**
 * vertex
 * @author rayo
 *
 */
class Vertice {
	int weight;
	int id;
	List<Edge> edgeList;

	/**
	 * construtor
	 * @param id
	 */
	public Vertice(int id) {
		this.edgeList = new ArrayList<Edge>();
		this.id = id;
		this.weight = 0;
	}
}

/**
 * edge
 * @author rayo
 *
 */
class Edge {
	
	int weight;
	int flowLimit;
	int rev;	
	int source;
	int sink;

	/**
	 * constructor
	 * @param s
	 * @param d
	 * @param wt
	 * @param rev
	 */
	public Edge(int s, int d, int wt, int rev) {
		this.source = s;
		this.sink = d;
		this.weight = wt;
		this.flowLimit = 0;
		this.rev = rev;
	}
}


/**
 * team class
 * @author rayo
 *
 */
class Team implements Comparable<Team> {
	
	int id;
	boolean win;
	int maxWins;
	int wins;

	/**
	 * constructor
	 * @param id
	 * @param wins
	 */
	public Team(int id, int wins) {
		this.id = id;
		this.wins = wins;
		this.maxWins = wins;
		win = false;
	}
/**
 * comparable
 */
	@Override
	public int compareTo(Team t1) {
		return Integer.compare(maxWins, t1.maxWins);
	}
}

/**
 * graph class
 * @author rayo
 *
 */
class Graph {
	Map<Integer, Vertice> vertex;

	int src;
	int target;
	int maxFlow;

	/**
	 * constructor
	 * @param source
	 * @param target
	 */
	public Graph(int source, int target) {
		vertex = new HashMap<Integer, Vertice>();
		this.src = source;
		this.target = target;
		maxFlow = 0;
	}

	// code from net  https://sites.google.com/site/indy256/algo/dinic_flow
	/**
	 * start
	 */
	private boolean doDinicBfs(int src, int sink, Map<Integer, Integer> d) {
		d.clear();
		d.put(src, 0);
		int[] Q = new int[vertex.size()];
		int sizeOfQ = 0;
		Q[sizeOfQ++] = src;
		for (int i = 0; i < sizeOfQ; i++) {
			int u = Q[i];
			for (Edge e : 
				vertex.get(u).edgeList) {
				if (!d.containsKey(e.sink) 
						&& e.flowLimit 
						< e.weight) {
					d.put(e.sink, d.get(u) + 1);
					Q[sizeOfQ++] = e.sink;
				}
			}
		}
		return d.containsKey(sink);
	}

	public void doDinitz() {
		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
		while (doDinicBfs(src, target, dist)) {
			Map<Integer, Integer> ptr = new HashMap<Integer, Integer>();
			for (Vertice v : vertex.values()) {
				ptr.put(v.id, 0);
			}
			while (true) {
				int df = doDinicDfs(ptr, dist,
						target, src, 
						Integer.MAX_VALUE);
				if (df == 0)
					break;
				maxFlow += df;
			}
		}
	}


	private int doDinicDfs(Map<Integer, Integer> pointer,
			Map<Integer, Integer> d, int sink, 
			int u, int flow) {
		if (u == sink) {
			return flow;
		}
		for (; pointer.get(u) < vertex.get(u).edgeList.size(); pointer.replace(u, pointer.get(u) + 1)) {
			Edge e = vertex.get(u).edgeList.get(pointer.get(u));
			if (d.containsKey(e.sink) 
					&& d.containsKey(u)
					&& (d.get(e.sink) == d.get(u) + 1)
					&& e.flowLimit < e.weight) {
				
				int df = doDinicDfs(pointer, d, sink, e.sink,
						Math.min(flow, e.weight - e.flowLimit));
				if (df > 0) {
					e.flowLimit += df;
					vertex.get(e.sink)
					.edgeList.get(e.rev)
					.flowLimit -= df;
					return df;
				}
			}
		}
		return 0;
	}
	/**
	 * end
	 */
}

