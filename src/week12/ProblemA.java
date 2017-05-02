package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Trie implementation copied from:
 * http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in-java/
 * 
 * @author rayo
 *
 */
// Trie Node, which stores a character and the children in a HashMap
class TrieNode {

	char value;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isEnd;
	boolean bIsEnd;


	public TrieNode() {
		// empty node - root is empty
	}

	public TrieNode(char ch) {
		this.value = ch;
	}
}

// Implements the actual Trie

public class ProblemA {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * The first line of the input contains an integer t. t test cases
		 * follow, each of them separated by a blank line. Each test case
		 * consists of an integer n, the amount of contacts Lea has in her
		 * phone. n lines follow, each line containing the name of a contact
		 * (where the first letter is in A to Z and the rest is in a to z).
		 * 
		 */
		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			int output = 0;

			ProblemA trie = new ProblemA();
			// the amount of contacts Lea has in her phone
			int n = Integer.parseInt(br.readLine());
			String contact[] = new String[n];

			for (int index1 = 0; index1 < n; index1++) {
				// individual contact
				contact[index1] = br.readLine();
				// added to trie
				trie.insert(contact[index1]);
			}

			for (int index2 = 0; index2 < n; index2++) {
				if (!trie.findMatchingPrefix(contact[index2]))
					// increment the result to print
					output++;

			}
			/**
			 * For each test case, output one line containing Case #i: x where i
			 * is its number, starting at 1, and x is the minimal amount of
			 * contacts Lea has to rename. Each line of the output should end
			 * with a line break.
			 */
			System.out.println("Case #" + i + ": " + output);
			br.readLine();
		}
	}

	private TrieNode root;

	/**
	 * constructor
	 */
	public ProblemA() {
		root = new TrieNode();
	}

	// Method to insert a new word to Trie
	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;
		int length = word.length();
		// Traverse through all characters of given word
		for (int level = 0; level < length; level++) {
			char ch = word.charAt(level);
			TrieNode crawl;
			// If this is end of a word, then update prevMatch
			if (children.containsKey(ch)) {
				crawl = children.get(ch);
			} else {
				crawl = new TrieNode(ch);
				children.put(ch, crawl);
			}
			children = crawl.children;

			if (level == length - 1)

				crawl.isEnd = true;
		}
	}

	/**
	 * Get the matching result, check if in trie
	 * 
	 * @param input
	 * @return
	 */
	public boolean findMatchingPrefix(String input) {
		Map<Character, TrieNode> children = root.children;
		// set to null first
		TrieNode trieNode = null;
		for (int i = 0; i < input.length(); i++) {

			char ch = input.charAt(i);
			if (children.containsKey(ch)) {
				// match found
				trieNode = children.get(ch);
				children = trieNode.children;
			}
		}
		// check if empty or not
		return trieNode.children.isEmpty();
	}

}
