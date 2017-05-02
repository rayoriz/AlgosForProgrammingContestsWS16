package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
/**
 * Vertex for graph
 * @author rayo
 *
 */
class Vertex1 {
	int id;
	Boolean flag;
	int value = 0;
	Set<Integer> adjacencySet = new HashSet<Integer>();

	/*
	 * constructor
	 */
	public Vertex1(int id, Boolean flag, int value) {
		super();
		this.id = id;
		this.flag = flag;
		this.value = value;

	}
}

/**
 * Customs, inspired by
 * 
 * @author rayo
 *
 */
public class ProblemD {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			Set<Integer> tempSet = new HashSet<Integer>();

			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * starts with a number n, the number of cities in Poorland, n lines
			 * follow. The i-th line describes city i and contains an integer ki
			 * followed by ki integers c1, ..., cki . ki is the number of
			 * streets exiting city i, c1, ..., cki are the cities directly
			 * connected to city i by streets. Cities are indexed from 1 to n.
			 * You can assume that streets are undirected: If city i is directly
			 * connected to city j, then city j will be directly connected to
			 * city i.
			 */
			int n = Integer.parseInt(br.readLine());
			Vertex1[] graph = new Vertex1[n + 1];

			for (int index1 = 1; index1 <= n; index1++) {
				String input[] = br.readLine().split(" ");
				int path = Integer.parseInt(input[0]);
				Vertex1 v1 = new Vertex1(index1, false,path);
              				graph[index1] = v1;
              				
              				
				for (int index2 = 1; index2 <= path; index2++) {
					
					int ci = Integer.parseInt(input[index2]);
					v1.flag = true;
					if (ci != index1) { // a new vertex is added in this case
						v1.adjacencySet
						.add(ci);
					} else
						v1
						.value--;
				}
			}
/**
 * arrays sorted based on the values - sorted from 1 to n
 */
			Arrays.sort(graph, 1, n, new Comparator<Vertex1>() {
				@Override
				public int compare(Vertex1 v0, Vertex1 v1) {
					if (v0.value < v1.value){
						return 1;
					}
					if (v0.value > v1.value){
						return -1;			
					}
					return 0;
				}
			});		
			for (int index2 = 1; index2 <= n; index2++) {
				Vertex1 v2 = graph[index2];
				int j = -1;
				int k = 0;
				for (Integer s : v2.adjacencySet) {
					if (tempSet
							.contains(s)) k--;
					else k++;
				}
				if (k > 0) {
					j++;
					tempSet.add(v2.id);
				}
			}
			// final list of cities sorted
			int l = 0, m=0;
			int[] finalSet = new int[tempSet.size()];
			for (Integer s : tempSet) {
				finalSet[l] = s; l++;
			}
			Arrays.sort(finalSet);

			String result = "";

			for (int index4 = 0; index4 < finalSet.length; index4++) {
				result += " " + finalSet[index4];
			}

			/**
			 * For each test case, output one line containing Case #i: where i
			 * is its number, starting at 1. Output one more line containing
			 * integers a1...al in ascending order such that when the country is
			 * partitioned into the cities a1, ..., al and the remaining cities,
			 * the number of roads between the parts is at least half as big as
			 * in the optimal solution. If there are multiple solutions, any of
			 * them will be accepted. Each line of the output should end with a
			 * line break.
			 * 
			 */
			System.out.println("Case #" + i + ": " + result);

			br.readLine();

		}
	}
}
