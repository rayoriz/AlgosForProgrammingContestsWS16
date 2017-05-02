package week1;

import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class QuestionC {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader); 
		
		
		// read the number of inputs
		int lines = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=lines; i++){
			
			String input = br.readLine();
			
			String[] content = input.split("#",2); // get only 2 inputs
			
			int index = Integer.parseInt(content[0]);
		
			String s1 = content[1].substring(index);
			String s2 = content[1].substring(0,index);
			
			System.out.println("Case #"+i+": "+s1+s2);

		}
		
	}

}
