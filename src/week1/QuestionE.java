package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionE {
	
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader); 
		
		
		// read the number of inputs
		int cases = Integer.parseInt(br.readLine());

		
		for(int i=1; i<=cases; i++){
			
			String input1 = br.readLine();
			String[] input1Array = input1.split(" ");
			
			int numberOfGrids = Integer.parseInt(input1Array[0]);
			int numberOfFoods = Integer.parseInt(input1Array[1]);
			
			// Initialize the playing field
			/**
			 * 0 for empty space
			 * 1 for food 
			 * 5 is where the snake is
			 */
			
			int playArena[][] = new int[numberOfGrids][numberOfGrids];
			//Arrays.fill(playArena, 0);
            
			
			String input2 = br.readLine();
			String[] input2Array = input2.split(" ");
			
			int columStart = Integer.parseInt(input2Array[0])-1; // because the array starts with 0 and not 1
			int rowStart = Integer.parseInt(input2Array[1])-1;
			
/* 		System.out.println();
			System.out.println();*/
			
			for(int index=0; index< numberOfFoods; index++){
				String foodLine = br.readLine();
				String[] foodArray = foodLine.split(" ");
				
				int ci = Integer.parseInt(foodArray[0])-1;
				int ri = Integer.parseInt(foodArray[1])-1;
				int wi = Integer.parseInt(foodArray[2]);
				int hi = Integer.parseInt(foodArray[3]);
				
				int tempCi = ci;
				int tempRi = ri;
				
				// Fill with food
				for(int index2 = 0; index2<hi; index2++){
					for(int index3 = 0; index3<wi; index3++){
						
						tempCi = tempCi % numberOfGrids; // this prevents the array overflow
						tempRi = tempRi % numberOfGrids;
						playArena[tempRi][tempCi] = 1;
						tempCi++;
					}
					tempRi++;
					tempCi = ci;
					
					

				}				
			}
			
			// Initialize the snek
			playArena[rowStart][columStart] = 5;
			
//			System.out.println(Arrays.deepToString(playArena));
			
/*			for(int[] row : playArena) {
	            printRow(row);
	        }*/


			String input3 = br.readLine();
			String[] input3Array = input3.split(" ");
			
			
			
			int length = Integer.parseInt(input3Array[0]);
			String playString = input3Array[1];
//			System.out.println(playString);
			
			
			List<String> snakePositions = new ArrayList<String>();
			
			int numberOfSuccessfulMoves = 0;
			int numberOfFoodsEaten = 0;
			String currentDirection = "Right"; 
			String currentCoordinates = columStart+","+rowStart;
			
			snakePositions.add(currentCoordinates); // initialize game
			
			// Inspired from http://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
			for (int charIndex = 0; charIndex < playString.length(); charIndex++){
			    char playStep = playString.charAt(charIndex);  
			    
			    
			   String newOrientation =  calculateOrientation(currentDirection,currentCoordinates,playStep,numberOfGrids);
			   
			   String[] tempOrientation = newOrientation.split("@");
			   currentDirection = tempOrientation[0];
			   currentCoordinates = tempOrientation[1];
			   int currentColumn = Integer.parseInt(currentCoordinates.split(",")[0]);
			   int currentRow = Integer.parseInt(currentCoordinates.split(",")[1]);

			   
			   // Check if current coordinates are already part of the snake - if so end game
			  
//			   System.out.println("old positions of the snake:"+snakePositions.toString());
			   if(playArena[currentRow][currentColumn] != 1){
					  String tailOfSnake = snakePositions.get(0);
				   snakePositions.remove(0);
					  playArena[Integer.parseInt(tailOfSnake.split(",")[1])][Integer.parseInt(tailOfSnake.split(",")[0])] = 0;

			   }
			  if( snakePositions.contains(currentCoordinates)){
				  // gameOver
				  break;
			  }else{
				  
				  snakePositions.add(currentCoordinates);
				  numberOfSuccessfulMoves ++;

				  if(playArena[currentRow][currentColumn] == 1){ // food is found
					  numberOfFoodsEaten++;
				  }/*else{
				  //snakePositions.remove(0);
				  }*/
				  
				  playArena[currentRow][currentColumn] = 5;

				  
				  
			  }

/*			  System.out.println();
				for(int[] row : playArena) {
		            printRow(row);
		        }
*/
			    
			}
			
			System.out.println("Case #"+i+": " + numberOfSuccessfulMoves + " "+ numberOfFoodsEaten);
			

			if(i!=cases){
				br.readLine();
			}
		}
	}
	
	private static  String calculateOrientation(String currentDirection, String currentCoordinates, char playStep, int numberOfGrids) {
	//	System.out.println("for step:"+playStep);
/*	    System.out.println("for step:"+playStep);
	    System.out.println("for currentDirection:"+currentDirection);
	    System.out.println("for currentCoordinates:"+currentCoordinates);
	    System.out.println("for numberOfGrids:"+numberOfGrids);*/

		
		String[] coordinateArray = currentCoordinates.split(",");
		int currentColumn = Integer.parseInt(coordinateArray[0]);
		int currentRow = Integer.parseInt(coordinateArray[1]);
        
		int newColumn = 0;
		int newRow = 0;
		String newDirection = " ";		
		if(currentDirection.equals("Right")){
			
			 if(playStep == 'F'){
				 
				 newRow = currentRow /*% numberOfGrids*/;
				 newColumn = Math.floorMod(((currentColumn + 1)) ,numberOfGrids);;

				 newDirection = "Right";
				 
			    	
			    }else if (playStep == 'L') {
			    	
			    	newRow =	Math.floorMod((currentRow -1) ,numberOfGrids);
					// newRow = (currentRow -1)  % numberOfGrids;
					 newColumn = currentColumn /*% numberOfGrids*/;	
					 newDirection = "Up";
					
				}else if(playStep == 'R'){
					
					// newRow = (currentRow +1)  % numberOfGrids;
			    	newRow =	Math.floorMod((currentRow +1) ,numberOfGrids);

					 newColumn = currentColumn ;
					 newDirection = "Down";

				}
			
		}else if (currentDirection.equals("Left")) {
			
			 if(playStep == 'F'){
				 
				 newRow = currentRow /*% numberOfGrids*/;
				 //newColumn = (currentColumn - 1) % numberOfGrids;
				 newColumn = Math.floorMod(((currentColumn - 1)) ,numberOfGrids);;

				 newDirection = "Left";
			    	
			    }else if (playStep == 'L') {
			    	
					// newRow = (currentRow +1)  % numberOfGrids;
			    	newRow =	Math.floorMod((currentRow +1) ,numberOfGrids);

					 newColumn = currentColumn ;
					 newDirection = "Down";

					
				}else if(playStep == 'R'){
					
					 //newRow = (currentRow -1)  % numberOfGrids;
				    	newRow =	Math.floorMod((currentRow -1) ,numberOfGrids);

					 newColumn = currentColumn /*% numberOfGrids*/;	
					 newDirection = "Up";
					
				}
			
		}else if (currentDirection.equals("Up")){
			
			 if(playStep == 'F'){
				 
				// newRow = (currentRow -1)  % numberOfGrids;
			    	newRow =	Math.floorMod((currentRow -1) ,numberOfGrids);

				 newColumn = currentColumn /*% numberOfGrids*/;	
				 newDirection = "Up";
			    	
			    }else if (playStep == 'L') {
			    	
					 newRow = currentRow /*% numberOfGrids*/;
					// newColumn = (currentColumn - 1) % numberOfGrids;
				    	newColumn =	Math.floorMod((currentColumn -1) ,numberOfGrids);

					 newDirection = "Left";
					
				}else if(playStep == 'R'){
					
					 newRow = currentRow /*% numberOfGrids*/;
					// newColumn = (currentColumn + 1) % numberOfGrids;
				    	newColumn =	Math.floorMod((currentColumn +1) ,numberOfGrids);
 
					 newDirection = "Right";
					
				}
			
		}else if (currentDirection.equals("Down")){
			
			 if(playStep == 'F'){
				 
				// newRow = (currentRow +1)  % numberOfGrids;
			    	newRow =	Math.floorMod((currentRow +1) ,numberOfGrids);

				 newColumn = currentColumn ;
				 newDirection = "Down";
				 
			    	
			    }else if (playStep == 'L') {
			    	
			    	 newRow = currentRow /*% numberOfGrids*/;
					// newColumn = (currentColumn - 1) % numberOfGrids;
				    	newColumn =	Math.floorMod((currentColumn +1) ,numberOfGrids);

					 newDirection = "Right";
					
				}else if(playStep == 'R'){
					
					 newRow = currentRow /*% numberOfGrids*/;
					 //newColumn = (currentColumn + 1) % numberOfGrids;
				     newColumn =	Math.floorMod((currentColumn -1) ,numberOfGrids);

					 newDirection = "Left";
				}
		}
		
		
		
		// Current Coordinates contain the new orientation and the current colum,row separated by a @
		
//		System.out.println(newDirection+"@"+newColumn+","+newRow);
		return newDirection+"@"+newColumn+","+newRow;
		
	}
			
   /* public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }*/
		
}
