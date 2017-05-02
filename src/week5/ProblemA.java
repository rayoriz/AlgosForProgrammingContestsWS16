package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemA {

	static boolean[]sieve ;
	static int primeCount;
	static int num1, num2;
	static int count;
	static int[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader (new InputStreamReader (System .in));
		int cases = Integer.parseInt(br.readLine());
		 int[] inputNumbers = new int[cases];

		int max = 0;

		for (int i = 0; i < cases; i++) {
			inputNumbers[i] = Integer.parseInt(br.readLine());	
			if (max< inputNumbers[i]){
				max = inputNumbers[i];
			}
		}

		initializeSieve(max);
        printOutput(inputNumbers);
       
/*        for(int k =0;k<sieve.length; k++){
        	if(sieve[k]){
        		System.out.println(k);
        	}
        }
        
        System.out.println();
		System.out.println(primeCount);*/

	}

	private static void printOutput(int[] inputNumbers) {

		for(int i = 0;  i <inputNumbers.length; i++){
			
			StringBuilder sb = new StringBuilder();
			sb.append("Case #"+ (i+1)+": ");
			
			if(inputNumbers[i]%2 == 0){
			
				getPrimes(inputNumbers[i]);
				
				sb.append(num1+" "+num2);
				}
			
			else{
				
				if(sieve[inputNumbers[i]-4]){ // for odd and starting with 2
					sb.append("2 2 "+ (inputNumbers[i]-4));
				}else{
					
					getPrimes(inputNumbers[i]-3);
					sb.append("3 "+num1+ " "+num2);


				}
			}
			
			System.out.println(sb);
		}
		
	}

	private static void getPrimes(int i) {

		 int left = 0, right = count-1;
	        while (left <= right) {
	            if      (list[left] + list[right] == i){
	            	num1= list[left] ;
	    	        num2 = list[right];
	            	break;
	            }
	            else if (list[left] + list[right]  < i) left++;
	            else right--;
	        }
	        
	        
	}

	private static void initializeSieve(int max) {

		sieve = new boolean[max];
		Arrays.fill(sieve, true);
		sieve[0]= false;
		sieve[1]= false;


		for(int i =2; i*i<max; i++){
			if(sieve[i]){
				for(int j=i; i*j<max;j++){
					sieve[i*j] = false;
				}
			}
		}
		
		 // count primes
        int primes = 0;
        for (int i = 2; i < max; i++)
            if (sieve[i]) primes++;


        // store primes in list
        list = new int[primes];
        count = 0;
        for (int i = 0; i < max; i++)
            if (sieve[i]) list[count++] = i;	
        
	}

}
