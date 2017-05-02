package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *  Code inspired from multiple sources, especially geeks for geeks max bipartite matching
 * @author rayo
 *
 */
class ProblemC {
	
	// initialize to 2
	int presents = 2;
	int friends = 2;

	/*
	 * constructor
	 */
	public ProblemC(int n, int m) {
		this.friends = n;
		this.presents = m;
	}
	

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			boolean satisfy = false;
			int maxflow = 0;
			
			/*			 The first line of the input contains an integer t. t test cases follow, each of them separated by a blank line.
Each test case starts with a line containing two integers n, the number of Leas friends, and m, the number of presents
Lea bought. The friends and presents are numbered from 1 to n or m, respectively. n lines follow describing the
preferences of her friends: The i-th line contains a string with the numbers of the presents friend i likes. The numbers
are comma-separated and may be given as sections where the first and last present are separated by a dash. For instance
the string 1,10,3,5-8 represents presents 1, 3, 5, 6, 7, 8 and 10. Note that this string may also be empty.
			 * 
			 */
			String[] input = br.readLine().split(" ");
			// input line is read
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			boolean[][] graph = new boolean[n][m];
			for (int index1 = 0; index1 < n; index1++) {
				String[] input2 = br.readLine().split(",");
				
				if (!(input2[0].equals(""))) { // empty ip
					
					for (int index2 = 0; index2 < input2.length; index2++) {
						
						// - found
						String[] option = input2[index2].split("-");
						if (option.length == 1) {
							// initialize the graph
							graph[index1]
									[(Integer.parseInt(input2[index2])
											- 1)] = true;
						} else {
							// other case
							for (int index3 = Integer.parseInt(option[0]); 
									index3 <= Integer.parseInt(option[1]);
									index3++)
								graph[index1][index3 
								              - 1] = true;
						}

					}
				} else { 
					// constaint satisfied in this case
					
					satisfy = true;
				}
			}
			if (!satisfy && n <= m) { // constraint not satisfied,
				// calculate Maximum Bipartite Matching to check if constraint is satisfied
				// initialize
				ProblemC bipartiate = new ProblemC(n, m);
				
				maxflow = bipartiate.maxBPM(graph);
			}
			
			String result = "yes";
			if (satisfy 
					|| n > m 
					|| maxflow < n) { 
				// constraint not satisfied set result to no
				result = "no";
			}
			System.out.println("Case #"+i+": "+result);
			
			br.readLine();
		}
	}

	/**
	 * from http://www.geeksforgeeks.org/maximum-bipartite-matching/
	 * @param bpGraph
	 * @return
	 */
	// A DFS based recursive function that returns true if a
    // matching for vertex u is possible
	boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matchR[]) {
		// Try every job one by one
		for (int v = 0; v < presents; v++) {
			if (bpGraph[u][v] && !seen[v]) {
				seen[v] = true;// Mark v as visited
 
                // If job 'v' is not assigned to an applicant OR
                // previously assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the above line,
                // matchR[v] in the following recursive call will
                // not get job 'v' again
				if (matchR[v] < 0 || bpm(bpGraph, matchR[v], 
						seen, matchR)) {
					matchR[v] = u;
					return true;
				}
			}
		}
		return false;
	}
/**
 * from http://www.geeksforgeeks.org/maximum-bipartite-matching/
 * @param bpGraph
 * @return
 */
	
	int maxBPM(boolean bpGraph[][])
    {
        // An array to keep track of the applicants assigned to
        // jobs. The value of matchR[i] is the applicant number
        // assigned to job i, the value -1 indicates nobody is
        // assigned.
        int matchR[] = new int[presents];
 
        // Initially all jobs are available
        for(int i=0; i<presents; ++i)
            matchR[i] = -1;
 
        int result = 0; // Count of jobs assigned to applicants
        for (int u = 0; u < friends; u++)
        {
            // Mark all jobs as not seen for next applicant.
            boolean seen[] =new boolean[presents] ;
            for(int i=0; i<presents; ++i)
                seen[i] = false;
 
            // Find if the applicant 'u' can get a job
            if (bpm(bpGraph, u, seen, matchR))
                result++;
        }
        return result;
    }
}
