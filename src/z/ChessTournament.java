package z;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessTournament {

	public static void main(String[] args) {
		int t;
		int n;
		int i= 0;
		int teams;
		int l=0;
		String input=null;
		StringBuilder cases = new StringBuilder();
		ArrayList<String> entries = new ArrayList<String>();
		String per[] = null;
		String ent =null;
		try {
			   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   t= Integer.parseInt(br.readLine());
			   for(int j=1;(j<=t && j<=20);j++){
				   input  = br.readLine();
				   if(!(input.equals(""))){
					   n  = Integer.parseInt(input);
					   if(n >= 2 && n<=1000){
						  cases.append(n+"$");
						  for(int k=1;k<=n;k++){
							  input = br.readLine();//getting row
							  cases.append(input+" ");
						  } 
			   		   		entries.add(cases.toString());
			   		   		cases.delete(0, (cases.length()+1));
					   }
					   else {
						   for(int k=1;k<=n;k++){
								  br.readLine();// not valid number of schools
							} 
					   }
				   }
				   else {
					   j--;
				   }
				}
				   
			   for(String entrie : entries){
				   i++;
				   l = 0;
				   teams = Integer.parseInt(entrie.substring(0, entrie.indexOf('$')));
				   ent = entrie.substring((entrie.indexOf('$')+1));
				   per = ent.split("\\s+");
				   Integer[][] team = new Integer[teams][5];
				   Integer [] sum = new Integer[teams];
				   Map<Integer, Integer> unsortTeams = new HashMap<Integer, Integer>();
				   System.out.println("Case #"+i+":");
				   for(int j =0;j<teams;j++){
					   sum[j] = 0;
					   for(int k = 0; k<5;k++){
						   team[j][k]= Integer.parseInt(per[l]);
						   if(team[j][k]<=1000 && team[j][k]>=1)
							   sum[j] = sum[j]+team[j][k];
						   l++;
					   }
					   unsortTeams.put(j, sum[j]);
				   }
				   for (Integer[] innerArray : team) {
					    Arrays.sort(innerArray, Collections.reverseOrder());
					}
				   Map<Integer, Integer> sortedTeamsDesc = sortByComparator(unsortTeams);
				   for (Entry<Integer, Integer> entry : sortedTeamsDesc.entrySet())
			        {
			            for(int k = 0; k<5; k++ ){
			            	//if(team[entry.getKey()][k] <=1000 && team[entry.getKey()][k] >=1)
			            		System.out.print(team[entry.getKey()][k]+" ");
			            }
			            System.out.print("\n");
			        }
			   }
			 } catch (Exception e) {
			    System.err.println("Error: " + e);
			 }

	}
	
	private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap)
    {

        List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
        {
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
            {
                return o2.getValue().compareTo(o1.getValue());

            }
        });
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


}