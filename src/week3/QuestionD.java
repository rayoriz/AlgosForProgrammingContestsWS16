package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QuestionD {

	// code inspired from prim's implementation in geeksfor geeks
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases= Integer.parseInt(br.readLine());
		
		for(int c=1;c<=cases;c++){
			
			int numberOfPeople = Integer.parseInt(br.readLine());
			int[][] graph = new int[numberOfPeople][numberOfPeople];
			
			// pq implementation from stack overflow
			PriorityQueue<Integer> pq=new PriorityQueue<Integer>(new Comparator<Integer>(){

				@Override
				public int compare(Integer arg0, Integer arg1) {
					// TODO Auto-generated method stub
					return arg1-arg0;   // reverse the comparator to get the max value and not the min value
				}
        		
        	});
		

			
			for(int j=0;j<numberOfPeople;j++){
				
				String[] trustString = br.readLine().split(" ");

				
				for(int k=0;k<numberOfPeople;k++){
					
					graph[k][j] =  Integer.parseInt(trustString[k]);
					
				}			
				
			}
			
			int[] trustValue = new int[numberOfPeople];
			
			pq.add(0);
			trustValue[0] = 0;
		
			while(!pq.isEmpty()){ // loop over priority queue
				
				int nextVertex = pq.poll();
				for(int j=0;j<numberOfPeople;j++){
					
					if(trustValue[j] < graph[nextVertex][j]){
						trustValue[j] = graph[nextVertex][j];
						pq.add(j);
					}
					
				}
				
			}
			
			int trustSum = 0;
			
        	//System.out.println(Arrays.toString(trustValue));

			
			for(int v=1;v<numberOfPeople;v++){
				trustSum=trustSum +   trustValue[v];
        	}
			
       	 System.out.println("Case #"+c+": "+trustSum);

			br.readLine();
		}
		
	}

	

	
	 public static void printRow(int[] row) {
	        for (int i : row) {
	            System.out.print(i);
	            System.out.print("\t");
	        }
	        System.out.println();
	    }
}
