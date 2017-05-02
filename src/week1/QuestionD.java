package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class QuestionD {
	
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader); 
		
		
		// read the number of inputs
		int lines = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=lines; i++){
			
			int numberOfSchools = Integer.parseInt(br.readLine());
			
			Integer[][] finalArray = new Integer[numberOfSchools][5];;
			
			for(int j=0; j<numberOfSchools; j++){
                
				// source inspired from stack overflow to convert string to integer array
				String[] items = br.readLine().split(" ");
				Integer[] results = new Integer[items.length];

				for (int k = 0; k < items.length; k++) {
				    try {
				        results[k] = Integer.parseInt(items[k]);
				    } catch (NumberFormatException nfe) {
				    };
				}
				
				Arrays.sort(results, Collections.reverseOrder());
				finalArray[j] = results;
				
			}
			
			// source inspired from http://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
			java.util.Arrays.sort(finalArray, new java.util.Comparator<Integer[]>() {
			    public int compare(Integer[] a, Integer[] b) {
			    	

			    	for(int index = 0;index<5; index ++){
			    		if (b[index]<a[index]){
			    			return -1;
			    		}else if(a[index]<b[index]){
			    			return 1;
			    		}			    		
			    	}
	    			return 0;

			    	
			    }
			});

			System.out.println("Case #"+i+":");
			
			for (int indexRow = 0; indexRow < finalArray.length; indexRow++) {
	            for (int indexColumn = 0; indexColumn < finalArray[0].length; indexColumn++) {
	            	System.out.print(finalArray[indexRow][indexColumn]);
	            	if(indexColumn!=4){
	            		System.out.print(" ");
	            	}
	            }
            	if(indexRow!=finalArray.length){

	            System.out.println();
            	}
	        }

			if(i!=lines){
				br.readLine();
			}
		}
	}
			
		
}
