package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ProblemC {

public static void main(String[] args) throws NumberFormatException, IOException {
	

	BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
	int cases = Integer.parseInt(br.readLine());
	 

	for (int i = 1; i <= cases; i++) {
		BigInteger fuel = new BigInteger(br.readLine());	
	
		fuel = fuel.multiply(BigInteger.valueOf(6));
		long index = 1;
		
		//n(n+1)(2n + 1) / 6 <=fuel

				//9999999999 
		BigInteger low = BigInteger.ONE;
		
		BigInteger high = new BigInteger("9999999999");
		//BigInteger mid = (high.add(low)).divide(BigInteger.valueOf(2));
		BigInteger mid; 
		BigInteger prevMid = new BigInteger("1");
		BigInteger value = new BigInteger("0"); 
		
		while(true){
		
		 mid = (high.add(low)).divide(BigInteger.valueOf(2));

			
		 value = mid;
	
		 //n(n+1)(2n + 1) / 6 <=fuel

		BigInteger ans =  ((value.multiply(value.add(BigInteger.ONE))).multiply((value.multiply(BigInteger.valueOf(2))).add(BigInteger.ONE)));
		
		if(prevMid.equals(mid)){
			break;
		}
		
		prevMid = mid;
      
		if(ans.compareTo(fuel)<0){
			
			low = mid;
			
		}else if(ans.compareTo(fuel)>0){
			
			high = mid;
			
		}
		
		}
		
		//System.out.println(value);
	/*	
		while(true){
			//System.out.println("first x:"+x);
			BigInteger y = x;
			BigInteger square = (y.subtract(BigInteger.ONE)).pow(2);
			//System.out.println(square);
			fuel = fuel.subtract( square);
			//System.out.println(fuel);
			if(fuel.compareTo(BigInteger.ZERO)<0){
				break;
			}
			//System.out.println("Before add:"+x);
			x = x.add(BigInteger.ONE);
			//System.out.println("After Add"+x);
			index++;
			
		}*/
		System.out.println("Case #"+i+": "+value.add(BigInteger.valueOf(2)));
	}
	
	
}

static BigInteger sqrt(BigInteger n) {
	BigInteger a = BigInteger.ONE;
	BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8));
	while (b.compareTo(a) >= 0) {
		BigInteger mid = a.add(b).shiftRight(1);
		if (mid.multiply(mid).compareTo(n) > 0) {
			b = mid.subtract(BigInteger.ONE);
		} else {
			a = mid.add(BigInteger.ONE);
		}
	}
	return a.subtract(BigInteger.ONE);
}
}
