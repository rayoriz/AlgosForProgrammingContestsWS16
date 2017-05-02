package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionA {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader); 
		
		// read the number of inputs
		int lines = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=lines; i++){
			
			System.out.println("Case #"+i+": Hello "+br.readLine()+"!");
		}
		
	}

}
