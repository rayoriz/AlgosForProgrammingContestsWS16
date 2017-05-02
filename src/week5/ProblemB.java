package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ProblemB {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <=cases; i++) {
			
			int n = Integer.parseInt(br.readLine());
			 
			 String[] numbers = br.readLine().split(" ");
			 
			 
				System.out.println("Case #"+i+": "+gcd(numbers));

/*			 for(int a=0;a<n; a++){
				 
				 inputNumbers[a] = new BigInteger(numbers[a]);
			 }
			 System.out.println(gcd(inputNumbers));*/
			 br.readLine();
		}

	}	
	
	public static BigInteger gcd(String[] parts){
	    BigInteger gcd = new BigInteger(parts[0]);
	    for(int i = 1; i < parts.length; i++){
	    	BigInteger c = new BigInteger(parts[i]);
	        gcd = c.gcd(gcd);
	    }
	    return gcd;
	}
}
