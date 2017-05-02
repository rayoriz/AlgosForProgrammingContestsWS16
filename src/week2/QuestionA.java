package week2;

import java.io.*;
import java.util.*;

public class QuestionA
{
	public static void main(String[] args) throws NumberFormatException, IOException, Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		
		for(int i=1;i<=t;i++){
			List<Integer> marriages = new ArrayList<Integer>();
			List<String> amount = new ArrayList<String>();
			boolean flag=false;
			int x=0;

            String input = br.readLine();

            String[] content = input.split(" ");
			PersonRelations relations = new PersonRelations(Integer.parseInt(content[0]));

            String moneyLine = br.readLine();

            String[] moneyArray	 = moneyLine.split(" ");
			if(moneyArray[0].matches("\\d+")){
				amount = new ArrayList<String>(Arrays.asList(moneyArray)); // initialize all the amounts
			}	
			// read all relations
			for (int j=1;j<=Integer.parseInt(content[1]);j++){
                String relationLine = br.readLine();
                String[] relationLineArray = relationLine.split(" ");
                // add all related persons
				relations.union(Integer.parseInt(relationLineArray[0])-1, Integer.parseInt(relationLineArray[1])-1);
			}
			// read all marriages
			for(int j=1;j<=Integer.parseInt(content[2]);j++){
				String[] marriageArray = br.readLine().split(" ");
				marriages.add(Integer.parseInt(marriageArray[0])-1);
				marriages.add(Integer.parseInt(marriageArray[1])-1);
				relations.union(Integer.parseInt(marriageArray[0])-1, Integer.parseInt(marriageArray[1])-1);
			}
			for(int j=0;j<Integer.parseInt(content[0])-1 && !(amount.isEmpty());j++){
				if(relations.find(j) != relations.find(Integer.parseInt(content[0])-1)){	
					if(!(marriages.contains(j))){ // sort to 
						if(x <= Integer.parseInt(amount.get(j))){
							flag = true;
							x=Integer.parseInt(amount.get(j));

						}
					}
				}
			}
			if(flag){
				System.out.println("Case #"+i+": "+x);
			}
			else{
				System.out.println("Case #"+i+": impossible");
			}
		    br.readLine();
		}
	}
}

// Inspired from Geeks for Geeks implementation of disjoint sets
class PersonRelations
{
	int[] rank, related;
	int n;
	public PersonRelations(int n)
	{
		rank = new int[n];
		related = new int[n];
		this.n = n;
		addToRelations();
	}
	void addToRelations()
	{
		for (int i=0; i<n; i++)
		{
			related[i] = i;
		}
	}

	int find(int x)
	{
		if (related[x]!=x)
		{
			related[x] = find(related[x]);
		}

		return related[x];
	}

	void union(int x, int y)
	{
		int xRoot = find(x), yRoot = find(y);

		if (xRoot == yRoot)
			return;

		if (rank[xRoot] < rank[yRoot])

			related[xRoot] = yRoot;

		else if (rank[yRoot] < rank[xRoot])

			related[yRoot] = xRoot;

		else // if ranks are the same
		{
			related[yRoot] = xRoot;
			rank[xRoot] = rank[xRoot] + 1;
		}
	}
}
