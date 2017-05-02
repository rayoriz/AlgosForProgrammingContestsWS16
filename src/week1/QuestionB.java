package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class QuestionB {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader); 
		int c = 299792458;
		//BigInteger c2 = BigInteger.valueOf(c*c);

		BigInteger c2 = BigInteger.valueOf(c).multiply(BigInteger.valueOf(c));
		// read the number of inputs
		int lines = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=lines; i++){
			
			int m = Integer.parseInt(br.readLine());
			//BigInteger mass = BigInteger.valueOf(m);
			
			System.out.println("Case #"+i+": "+BigInteger.valueOf(m).multiply(c2));
		}
		
	}

}
