package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author rayo
 *
 */

class Subject{
	
	int id;
	int length;
	int score;
	double ratio;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Subject(int id,int length, int score) {
		super();
		this.id =id;
		this.length = length;
		this.score = score;
		this.ratio = (double) score/length;
	}
	
	
}
public class ProblemA {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			/**
			 * The first line of the input contains an integer t, the number of
			 * lectures. t lectures follow, each of them separated by a blank
			 * line. Each lecture starts with a line containing two integers: m,
			 * the number of characters that fit on the allowed cheat sheet, and
			 * n, the number of topics covered. n lines describing the topics
			 * follow. The i-th line contains two integers li and si where li is
			 * the length of a piece of information for this topic and si is its
			 * score.
			 * 
			 */
			String[] input = br.readLine().split(" ");
			int maxchar = Integer.parseInt(input[0]);
			int numOfTopics = Integer.parseInt(input[1]);
			Map<Integer, Double> ratioMap = new HashMap<>();
			Map<Integer, int[]> map = new HashMap<>();
			Subject[] subjects = new Subject[numOfTopics];

			for (int index = 1; index <= numOfTopics; index++) {

				int[] array = new int[2];
				String[] input2 = br.readLine().split(" ");
				int length = Integer.parseInt(input2[0]);
				int score = Integer.parseInt(input2[1]);
				Subject sub = new Subject(index,length, score);
				subjects[index-1] = sub;
				//double ratio = (double) score / (double) length;
				/*array[0] = length;
				array[1] = score;
				ratioMap.put(index, ratio);
				map.put(index, array);*/
				
				
			}
			//Object[] a = ratioMap.entrySet().toArray();

			// set comparator
			Arrays.sort(subjects, new Comparator<Subject>() {
				public int compare(Subject o1, Subject o2) {
					if(o1.ratio < o2.ratio)
						return 1;
					if(o1.ratio > o2.ratio)
						return -1;
					return 0;
				}
			});

			/*
			 * for (Object e : a) { System.out.println(((Map.Entry<Integer,
			 * Double>) e).getKey() + " : " + ((Map.Entry<Integer, Double>)
			 * e).getValue()); }
			 */

			int tempWeight = 0;
			String outPut = "";
			
			for(int index2 = 0; index2 < numOfTopics; index2++) {
				int length = subjects[index2].length;
				while(tempWeight+length <= maxchar) {
					tempWeight += length;
					outPut += " "+subjects[index2].id;
					
				}
			}

			System.out.println("Case #"+i+":"+outPut);
			br.readLine();

		}
	}

}
