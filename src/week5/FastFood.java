package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// code inspired from stackoverflow for lcm
// for chinese remainder theorem used algorithm from http://maciejkus.com/chinese_remainder/
public class FastFood {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int t = 1; t <= cases; t++) {
			int k = Integer.parseInt(br.readLine());
			String[] inp = br.readLine().split(" ");
			int[] mods = new int[k];
			int[] constraints = new int[k];

			for (int i = 0; i < k; i++) {
				mods[i] = Integer.valueOf(inp[i]);
				int c = -i;
				int d = mods[i];
				// (c % d + d) % d
				//(a % b + b) % b
				constraints[i] = (c % d + d) % d;
				
				
				
			}
			
		/*	System.out.println(Arrays.toString(mods));
			System.out.println(Arrays.toString(constraints));
*/
			 
			int b = lcmofarray(mods,0,k);

			int a = chinRem(mods,constraints,b);
			
			System.out.println("Case #" + t + ": " + a + " " + b );
			br.readLine();

		}

		
	}
	
	
	
	
	
	public static int chinRem(int[] bArray, int[] aArray, int least_common) {
		int impossible = 0;
		int[] mods = bArray;
		int[] Na = new int[aArray.length+1];
		Na[0] = aArray[0];
		int Nb = mods[0];
		for(int i=0;i<aArray.length-1;i++){
			
			int a = Na[i]-aArray[i+1];
			int b = mods[i+1]-Nb;
			
			while ((a<0) && (b>0)) {
				a += mods[i+1]; //add latest mod until positive
			} 
			while ((b<0) && (a>0)) {
				b = -b;
				a= -a;
				a += mods[i+1];
			}
			
			// change from two negatives to two positives
						if ((a<0) &&(b<0)) {
							a = -a;
							b = -b;
						}
						
						//add mod until able to divide by 'b' evenly, 1=2b needs to have mod added 1 one until divisable by 2.
						int x = 0;
						//some problems are not possible?
						try{
						while ((a%b!=0)) {
							a += mods[i+1];
							
							x++;
						}
						Na[i+1] = a/b;
						
						Na[i+1] = Na[i+1] * Nb + Na[i]; //Why Na needed to be array
						Nb = mods[i+1]*Nb;
						
						}catch (Exception e) {
	                           impossible =1 ;
							}

						
		}
		
		//System.out.println(Arrays.toString(Na));
		int answer = Na[Na.length-2];

		while ((answer-least_common) > 0 ){
			answer = answer - least_common;
		}
		
		if(impossible == 1){
			return bArray[0]; // return the first gcd value
		}
		
		return answer;
		
		
		
	}
	
	
	
	public static int gcd(int a, int b) {
		if (a < b)
			return gcd(b, a);
		if (a % b == 0)
			return b;
		else
			return gcd(b, a % b);
	}

	public static int lcm(int a, int b) {
		return ((a * b) / gcd(a, b));

	}
	public static int lcmofarray(int[] arr, int start, int end) {
		if ((end - start) == 1)
			return lcm(arr[start], arr[end - 1]);
		else
			return (lcm(arr[start], lcmofarray(arr, start + 1, end)));
	}
	
}
