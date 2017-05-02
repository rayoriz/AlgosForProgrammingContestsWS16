package z;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Snake {

	static int nGrid;

	public static void main(String[] args) throws NumberFormatException, IOException, Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 


		int cases = Integer.parseInt(br.readLine());


		for(int i=1; i<=cases; i++){

			String line = br.readLine();
			String[] gf = line.split(" ");
			nGrid = Integer.parseInt(gf[0]);
			int nFood = Integer.parseInt(gf[1]);
			String gameTheme[][] = new String[nGrid][nGrid];
			String line2 = br.readLine();
			int cs = Integer.parseInt(line2.split(" ")[0])-1; 
			int rs = Integer.parseInt(line2.split(" ")[1])-1;

			for(int j=0; j< nFood; j++){
				String block = br.readLine();

				int ci = Integer.parseInt(block.split(" ")[0])-1;
				int tCol = Integer.parseInt(block.split(" ")[0])-1;
				int rRow = Integer.parseInt(block.split(" ")[1])-1;
				int wi = Integer.parseInt(block.split(" ")[2]);
				int hi = Integer.parseInt(block.split(" ")[3]);

				for(int index2 = 0; index2<hi; index2++){
					for(int index3 = 0; index3<wi; index3++){

						tCol = tCol % nGrid; 
						rRow = rRow % nGrid;
						gameTheme[rRow][tCol] = "Food";
						tCol++;
					}
					rRow++;
					tCol = ci;
				}				
			}

			String plan = br.readLine();
			int l = Integer.parseInt(plan.split(" ")[0]);
			String s = plan.split(" ")[1];

			String sLength = new String();

			int totalMove = 0;
			int snakeFood = 0;
			String startingMove = "R"; 
			String snakePosition = cs+","+rs;

			sLength = snakePosition+"$"; 

			for (int k = 0; k < l; k++){
				char move = s.charAt(k);  


				String newMove =  moveSnake(startingMove,snakePosition,move);

				startingMove = newMove.split("#")[0];
				snakePosition = newMove.split("#")[1];
				int column = Integer.parseInt(snakePosition.split(",")[0]);
				int row = Integer.parseInt(snakePosition.split(",")[1]);

				if(!(("Food").equals(gameTheme[row][column]))){
					
					String[] availablePositions = sLength.split("$");
					String newString = "";
					for(int x=0; x<availablePositions.length; x++){
						if(x==0){
							continue;
						}else{
							newString += availablePositions[x]+"$";
						}
					}
					
					sLength = newString;
 
				}
				System.out.println(sLength);
				System.out.println(snakePosition);
				if( sLength.contains(snakePosition)){
					System.out.println("entered exit condition");
					break;
				}else{
					sLength += snakePosition+"$";
					totalMove ++;

					if(("Food").equals(gameTheme[row][column])){ // food is found
						snakeFood++;
					}
					}
			}

			System.out.println("Case #"+i+": " + totalMove + " "+ snakeFood);

			if(i!=cases){
				br.readLine();
			}
		}
	}

	private static  String moveSnake(String startingMove, String snakePosition, char move) {

		int column = Integer.parseInt(snakePosition.split(",")[0]);
		int row = Integer.parseInt(snakePosition.split(",")[1]);

		int nCol = 0;
		int nRow = 0;
		String nextMove = " ";		
		if(startingMove.equals("R")){

			if(move == 'F'){

				nRow = row ;
				nCol = Math.floorMod(((column + 1)) ,nGrid);;

				nextMove = "R";


			}else if (move == 'L') {

				nRow =	Math.floorMod((row -1) ,nGrid);
				nCol = column;	
				nextMove = "U";

			}else if(move == 'R'){

				nRow =	Math.floorMod((row +1) ,nGrid);

				nCol = column ;
				nextMove = "D";

			}

		}else if (startingMove.equals("R")) {

			if(move == 'F'){

				nRow = row ;
				nCol = Math.floorMod(((column - 1)) ,nGrid);;
				nextMove = "R";

			}else if (move == 'L') {
				nRow =	Math.floorMod((row +1) ,nGrid);
				nCol = column ;
				nextMove = "D";
			}else if(move == 'R'){
				nRow =	Math.floorMod((row -1) ,nGrid);
				nCol = column 	;	
				nextMove = "U";
			}

		}else if (startingMove.equals("U")){
			if(move == 'F'){
				nRow =	Math.floorMod((row -1) ,nGrid);
				nCol = column ;	
				nextMove = "U";

			}else if (move == 'L') {

				nRow = row ;
				nCol =	Math.floorMod((column -1) ,nGrid);
				nextMove = "R";

			}else if(move == 'R'){
				nRow = row ;
				nCol =	Math.floorMod((column +1) ,nGrid);
				nextMove = "R";
			}
		}else if (startingMove.equals("D")){
			if(move == 'F'){
				nRow =	Math.floorMod((row +1) ,nGrid);
				nCol = column ;
				nextMove = "D";
			}else if (move == 'L') {
				nRow = row ;
				nCol =	Math.floorMod((column +1) ,nGrid);
				nextMove = "R";
			}else if(move == 'R'){
				nRow = row ;
				nCol =	Math.floorMod((column -1) ,nGrid);
				nextMove = "R";
			}
		}
		return nextMove+"#"+nCol+","+nRow;
	}

}
