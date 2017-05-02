package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionE {


	static class Country {
		int id;
		int foundCountries;
		Country friend;
		Country enemy;
		Country(int id){
			this.friend = this;
			this.id=id;
			foundCountries=1;
			this.enemy=null;
		}	
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases= Integer.parseInt(br.readLine());
		
		for(int i=1;i<=cases;i++){
			
			List<Country> country = new ArrayList<Country>();
			String input[]= br.readLine().split(" ");
			int countryCount = Integer.parseInt(input[0]);
			int relations = Integer.parseInt(input[1]);
			// create country objects
			for(int index1=1;index1<=countryCount;index1++){
				Country Country = new Country(index1);
				country.add(Country);
			}
			for(int index1=0;index1<relations;index1++){
				Country firstCountry;
				Country secondCountry;
				Country enemyOfFirst;
				Country enemyOfSecond;
				
				String countryAffiliation[]=br.readLine().split(" ");
				firstCountry=country.get(Integer.parseInt(countryAffiliation[1])-1);
				secondCountry=country.get(Integer.parseInt(countryAffiliation[2])-1);
				firstCountry=findFriend(firstCountry);
				secondCountry=findFriend(secondCountry);
				if(firstCountry.id == secondCountry.id){
					continue; // don't process, same countries
				}
				if(countryAffiliation[0].equals("F")){ // For friend
				
					if(firstCountry.foundCountries>=secondCountry.foundCountries){
						union(firstCountry,secondCountry);
						enemyOfFirst=firstCountry.enemy;
						enemyOfSecond=secondCountry.enemy;
						secondCountry.enemy=null;
						if(enemyOfFirst!=null){
							findAllEnemies(firstCountry, enemyOfFirst, enemyOfSecond);
						}
						else{
							if(enemyOfSecond!=null){
								firstCountry.enemy=enemyOfSecond;
								enemyOfSecond.enemy=firstCountry;
							}
						}
					}
					else{
						union(secondCountry,firstCountry);
						enemyOfSecond=secondCountry.enemy;
						enemyOfFirst=firstCountry.enemy;
						firstCountry.enemy=null;
						if(enemyOfSecond!=null){
							findAllEnemies(secondCountry,enemyOfSecond,enemyOfFirst);
						}
						else{
							if(enemyOfFirst!=null){
								secondCountry.enemy=enemyOfFirst;
								enemyOfFirst.enemy=secondCountry;
							}
						}
					}
				}
				else{ // for A

					enemyOfFirst=firstCountry.enemy;
					enemyOfSecond=secondCountry.enemy;
					if(enemyOfFirst!=null && enemyOfSecond!=null){

						if(secondCountry.foundCountries>=enemyOfFirst.foundCountries){
							union(secondCountry,enemyOfFirst);
							firstCountry.enemy=null;
							enemyOfFirst.enemy=null;
							union(enemyOfSecond,firstCountry);
						}
						else{
							union(enemyOfFirst,secondCountry);
							secondCountry.enemy=null;
							enemyOfSecond.enemy=null;
							union(firstCountry,enemyOfSecond);
						}
					}
					else if(enemyOfFirst == null && enemyOfSecond!=null){
						union(enemyOfSecond,firstCountry);
					}
					else if(enemyOfSecond == null && enemyOfFirst!=null){
						union(enemyOfFirst,secondCountry);
					}
					else if(enemyOfFirst == null && enemyOfSecond == null){
						firstCountry.enemy=secondCountry;
						secondCountry.enemy=firstCountry;
					}
				}
			}
			
			
			if (findFriend(country.get(0)).foundCountries>(countryCount/2)){
				System.out.println("Case #"+i+": yes");
			}
			else {
				System.out.println("Case #"+i+": no");
			}
			br.readLine();
		}
	}
	private static void findAllEnemies(Country firstCountry, Country enemyOfFirst, Country enemyOfSecond) {
		if(enemyOfSecond!=null){
			if(enemyOfSecond.foundCountries>enemyOfFirst.foundCountries)
			{
				union(enemyOfSecond,enemyOfFirst);
				firstCountry.enemy=enemyOfSecond;
				enemyOfSecond.enemy=firstCountry;
				enemyOfFirst.enemy=null;
			}
			else 
			{
				union(enemyOfFirst,enemyOfSecond);
				firstCountry.enemy=enemyOfFirst;
				enemyOfFirst.enemy=firstCountry;
				enemyOfSecond.enemy=null;
			}
		}
	}
	
	public static Country findFriend(Country Country){
		if(Country == Country.friend)
			return Country;
		else{
			// find all the friends of this country - recursion from stackoverflow
			Country temp= findFriend(Country.friend);
			Country.friend=temp;
			return temp;
		}
	}
	public static Country findEnemy(Country Country){
		if(Country.enemy == null){
			return null;
		}
		else{
			return Country.enemy;
		}
	}
	public static void union(Country firstCountry,Country secondCountry){
		if(! (firstCountry.id == secondCountry.id)){
			secondCountry.friend=firstCountry;
			firstCountry.foundCountries+=secondCountry.foundCountries;
		}
	}
	
}