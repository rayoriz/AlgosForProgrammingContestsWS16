package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * segment tree implementation adapted from //
 * https://gist.github.com/shobhit6993/7088061
 * 
 * @author rayo
 *
 */
public class ProblemA {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input1 = br.readLine().split(" ");
		/*
		 * The first line of the input contains an integer t. t test cases
		 * follow, each of them separated by a blank line. Each test case starts
		 * with a line containing two integers n k, where n is the number of
		 * locations and k is the number of events to follow. The locations are
		 * numbered from 1 through n. k more lines follow. Each is of one of the
		 * following forms: s a: A Catmon spawns at location a, increasing the
		 * number of Catmon at location a by one. d a: A Catmon despawns at
		 * location a, reducing the number of Catmon at location a by one if
		 * there currently is at least one Catmon at that location. c l r: Lea
		 * catches all the Catmon in the interval l to r (including l and r).
		 * The caught Catmon are added to Leas inventory and the number of
		 * Catmon in that interval is reduced to zero. A despawn event at
		 * location a happens only after a spawn event at location a, however
		 * there may be a catch event including that location in between.
		 * 
		 */
		int cases = Integer.parseInt(input1[0]);
		for (int i = 1; i <= cases; i++) {
			int result = 0;
			String[] input2 = br.readLine().split(" ");
			int n = Integer.parseInt(input2[0]); // number of locations
			int k = Integer.parseInt(input2[1]); // number of events
			int[] inputArr = new int[n];
			SegmentTree st = new SegmentTree(inputArr,false);
			for (int index1 = 0; index1 < k; index1++) {
				String[] input3 = br.readLine().split(" ");
				if (input3[0].equals("s")) {
					int val1 = Integer.parseInt(input3[1]);
					st.updateTree(1,0, n - 1, val1 - 1, val1 - 1, 1);

				} else if (input3[0].equals("d")) {
					int val2 = Integer.parseInt(input3[1]);
					st.updateTree(1,0, n - 1, val2 - 1, val2 - 1, -1);
				} else if (input3[0].equals("c")) {
					int left = Integer.parseInt(input3[1]);
					int right = Integer.parseInt(input3[2]);
					result = result + st.queryTree(1,0, n - 1, left - 1, right - 1);
					st.resetTree(1,0, n - 1, left - 1, right - 1);
				}
			}
			/**
			 * For each test case, output one line containing Case #i: x where i
			 * is its number, starting at 1, and x is number of Catmon Lea has
			 * caught after all k events have occurred in the given order. Each
			 * line of the output should end with a line break.
			 */
			System.out.println("Case #" + i + ": " + result);
			br.readLine();
		}

	}

}

// https://gist.github.com/shobhit6993/7088061

/*
 * 
 * int arr[N]; int tree[MAX]; int lazy[MAX]; void build_tree(int node, int a,
 * int b) { if(a > b) return; // Out of range
 * 
 * if(a == b) { // Leaf node tree[node] = arr[a]; // Init value return; }
 * 
 * build_tree(node*2, a, (a+b)/2); // Init left child build_tree(node*2+1,
 * 1+(a+b)/2, b); // Init right child
 * 
 * tree[node] = max(tree[node*2], tree[node*2+1]); // Init root value }
 * 
 * /** Increment elements within range [i, j] with value value
 * 
 * void update_tree(int node, int a, int b, int i, int j, int value) {
 * 
 * if(lazy[node] != 0) { // This node needs to be updated tree[node] +=
 * lazy[node]; // Update it
 * 
 * if(a != b) { lazy[node*2] += lazy[node]; // Mark child as lazy lazy[node*2+1]
 * += lazy[node]; // Mark child as lazy }
 * 
 * lazy[node] = 0; // Reset it }
 * 
 * if(a > b || a > j || b < i) // Current segment is not within range [i, j]
 * return;
 * 
 * if(a >= i && b <= j) { // Segment is fully within range tree[node] += value;
 * 
 * if(a != b) { // Not leaf node lazy[node*2] += value; lazy[node*2+1] += value;
 * }
 * 
 * return; }
 * 
 * update_tree(node*2, a, (a+b)/2, i, j, value); // Updating left child
 * update_tree(1+node*2, 1+(a+b)/2, b, i, j, value); // Updating right child
 * 
 * tree[node] = max(tree[node*2], tree[node*2+1]); // Updating root with max
 * value }
 * 
 *//**
	 * Query tree to get max element value within range [i, j]
	 *//*
	 * int query_tree(int node, int a, int b, int i, int j) {
	 * 
	 * if(a > b || a > j || b < i) return -inf; // Out of range
	 * 
	 * if(lazy[node] != 0) { // This node needs to be updated tree[node] +=
	 * lazy[node]; // Update it
	 * 
	 * if(a != b) { lazy[node*2] += lazy[node]; // Mark child as lazy
	 * lazy[node*2+1] += lazy[node]; // Mark child as lazy }
	 * 
	 * lazy[node] = 0; // Reset it }
	 * 
	 * if(a >= i && b <= j) // Current segment is totally within range [i, j]
	 * return tree[node];
	 * 
	 * int q1 = query_tree(node*2, a, (a+b)/2, i, j); // Query left child int q2
	 * = query_tree(1+node*2, 1+(a+b)/2, b, i, j); // Query right child
	 * 
	 * int res = max(q1, q2); // Return final result
	 * 
	 * return res; }
	 */

class SegmentTree {

	int[] inputArray, segmentTree, lazyAttribute;
	boolean flag;
	boolean count;

	/**
	 * 
	 * @param input
	 */
	public SegmentTree(int[] input, boolean lazy) {
		int powerLog = (int) (Math.log(input.length) / Math.log(2)) + 1;
		int treeSize = (int) (2 * Math.pow(2, powerLog));
		lazyAttribute = new int[treeSize];
		// initialize lazy to -1 
		Arrays.fill(lazyAttribute, -1);
		this.segmentTree = new int[treeSize];
		this.flag = lazy;
		this.inputArray = input;
	}

	public void makeTree(int vertex, int val1, int val2, boolean lazy) {

		if (val1 == val2) {
			segmentTree[vertex] = inputArray[val1];
			return;
		}
		if (val1 > val2) {
			return;
		}

		makeTree(vertex * 2, 
				val1, 
				(val1 + val2) / 2,
				true);
		
		makeTree((vertex * 2) + 1,
				1 + (val1 + val2) / 2, 
				val2, 
				true);

		segmentTree[vertex] = segmentTree[vertex * 2] +
				segmentTree[(vertex * 2) + 1]; 
	}

	public void updateTree(int node,int a, int b, int i, int j, int value) {

		
		if (lazyAttribute[node] != -1) { 
			if (lazyAttribute[node] == 0) {
				segmentTree[node] =
						lazyAttribute[node]; 
				if (a != b) {
					lazyAttribute[node * 2] = 
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] =
							lazyAttribute[node]; 
				}
			} else {
				segmentTree[node] +=
						lazyAttribute[node]; 
				if (a != b) {
					lazyAttribute[node * 2] +=
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] += 
							lazyAttribute[node]; 
				}
			}
			lazyAttribute[node] = -1;
		}

		if (a > b 
				|| a > j 
				|| b < i) 
			return;

		if (a >= i 
				&& b <= j) { 
			if (segmentTree[node] 
					+ value >= 0) {
				segmentTree[node] 
						+= value;

				if (a != b) { 
					lazyAttribute[node * 2] 
							+= value;
					lazyAttribute[node * 2 + 1] 
							+= value;
				}
			}
			return;

		}

		updateTree( node * 2,
				a,
				(a + b) / 2, 
				i, 
				j, 
				value); 
		updateTree(1 + (node * 2),
				1 + (a + b) / 2, 
				b, 
				i, 
				j, 
				value); 

		segmentTree[node] = segmentTree[node * 2] 
				+ segmentTree[node * 2 + 1];
	}


	public int queryTree(int node, int a, int b, int i, int j ) {
		if (a > b 
				|| a > j 
				|| b < i)
			return 0; 

		if (lazyAttribute[node] != -1) { 
			if (lazyAttribute[node] == 0) {
				segmentTree[node] = lazyAttribute[node]; 

				if (a != b) {
					lazyAttribute[node * 2] =
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] = 
							lazyAttribute[node]; 
				}
			} else {
				segmentTree[node] += 
						lazyAttribute[node]; 
				if (a != b) {
					lazyAttribute[node * 2] += 
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] += 
							lazyAttribute[node]; 
				}
			}
			lazyAttribute[node] = -1; 
		}

		if (a >= i && b <= j) 
			return segmentTree[node];

		int query1 = queryTree(node * 2, 
				a,
				(a + b) / 2,
				i,
				j ); 
		int query2 = queryTree(1 +node * 2,
				1 + (a + b) / 2, 
				b, 
				i, 
				j  ); 

		int res = query1 
				+ query2;

		return res;
	}

	public void resetTree(int node, int a, int b, int i, int j ) {
		
		if (lazyAttribute[node] != -1) { 
			if (lazyAttribute[node] == 0) {
				segmentTree[node] = 
						lazyAttribute[node]; 

				if (a != b) {
					lazyAttribute[node * 2] = 
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] =
							lazyAttribute[node]; 
				}
			} else {
				segmentTree[node] += 
						lazyAttribute[node]; 

				if (a != b) {
					lazyAttribute[node * 2] += 
							lazyAttribute[node]; 
					lazyAttribute[node * 2 + 1] += 
							lazyAttribute[node]; 
				}
			}
			lazyAttribute[node] = -1; 
		}

		if (a > b || a > j || b < i) 
			return;

		if (a >= i && b <= j) { 
			segmentTree[node] = 0;

			if (a != b) { 
				lazyAttribute[node * 2] = 0;
				lazyAttribute[node * 2 + 1] = 0;
			}

			return;

		}

		resetTree(node * 2,
				a, 
				(a + b) / 2,
				i,
				j ); 
		resetTree(1 + (node * 2),
				1 + (a + b) / 2, 
				b, 
				i, 
				j ); 

		segmentTree[node] = 
				segmentTree[node * 2] 
				+ segmentTree[node * 2 + 1];

	}

}
