package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionB {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		  InputStreamReader inputReader = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(inputReader); 
	        
	     // read the number of inputs
	        int lines = Integer.parseInt(br.readLine());
	        
	        for(int i=1; i<=lines; i++){
	        	
	            String input = br.readLine();

	            String[] content = input.split(" "); // get only 2 inputs
	        	
	            
	            double distance = Integer.parseInt(content[0]);
	            int poles = Integer.parseInt(content[1]);
	            double canyonStart = Integer.parseInt(content[2]);	
	            double canyonEnd = Integer.parseInt(content[3]);
	            
	            
	            // initiate high and low for the recursion
	            double high = distance;
	            double low = 0; 
	            
	            
	            // loop for precision
	            
	            while(high-low >  0.001){
	            	
	            	// find the center and check if this is in canyon
	            	double mid  = (high + low)/2;
	            	
	            	if(placePoles(canyonStart,canyonEnd, mid,poles,distance)){
	            		low = mid;
	            		
	            	}else{
	            		high = mid;	
	            	}
	        	
	        }
				System.out.println("Case #"+ i +": "+ low);


		
}
	}

	private static boolean placePoles(double canyonStart, double canyonEnd, double mid, int poles, double distance) {
		
		double index = 0;
		int individualPoles = 0;
		
		while(index <= distance){

		if ( (index+mid) > canyonStart && (index+mid) < canyonEnd) {

			index = canyonEnd;
			individualPoles++; // add additional poles whenever the step moves
			}else{
				
				individualPoles++;
				index = index+mid;
			}
		}
		
		return individualPoles>=poles;
	}
	
	

}
