package week3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Code inspired from geeks for geeks implementation of Prims algorithm, same as used in Question A
public class QuestionC {

	public static void main (String[] args) throws NumberFormatException, IOException, Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases= Integer.parseInt(br.readLine());
		
		for(int i=1;i<=cases;i++){
			int input1= Integer.parseInt(br.readLine());
			String[] inputArray = new String[input1+1];
			int graph[][] = new int[input1+1][input1+1]; // initialize the graph
			
			inputArray[0]="0 0 "+Integer.MAX_VALUE;
			for(int j=1;j<=input1;j++){
				inputArray[j]  = br.readLine();  // read input
			}
			
			 formPowerGraph(inputArray, graph, input1);
			
			QuestionC network =new QuestionC(input1+1);
			int powerConsumed = network.primMST(graph);
			if(powerConsumed < 0)
				System.out.println("Case #"+i+": impossible");
			else 
				System.out.println("Case #"+i+": "+(2 * powerConsumed));
			br.readLine();
		}
	}


	private static void formPowerGraph(String[] inputArray, int[][] graph, int input1) {
		
		for(int j=0;j<=input1;j++){
			String[] temp1  = inputArray[j].split(" ");
			for(int k=j+1;k<=input1;k++){
				String[] temp2  = inputArray[k].split(" ");// read coordinates
				int x = Integer.parseInt(temp2[0])-Integer.parseInt(temp1[0]);
				int y = Integer.parseInt(temp2[1])-Integer.parseInt(temp1[1]);
				int power = (int)Math.pow(x,2)+(int)Math.pow(y,2);
				if(Integer.parseInt(temp2[2])>=power && Integer.parseInt(temp1[2])>=power){
					graph[j][k] = power;
					graph[k][j] = power;
				}
			}
		}
	}


	private int V;
	public QuestionC(int v){
		this.V=v;
	}
	int minKey(int key[], Boolean mstSet[])
	{
		int min = Integer.MAX_VALUE, min_index=0;
		for (int v = 0; v < V; v++)
			if (mstSet[v] == false && key[v] < min)
			{
				min = key[v];
				min_index = v;
			}
		return min_index;
	}
	int primMST(int graph[][])
	{
		int parent[] = new int[V];
		int key[] = new int [V];
		Boolean mstSet[] = new Boolean[V];
		for (int i = 0; i < V; i++)
		{
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		for (int count = 0; count < V-1; count++)
		{
			int u = minKey(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < V; v++)
				if (graph[u][v]!=0 && mstSet[v] == false &&
				graph[u][v] < key[v])
				{
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
		int powerConsumed = 0;
		for (int i = 1; i < V; i++){
			if(graph[i][parent[i]] == 0){
				powerConsumed = -1;
				break;
			}
			powerConsumed = powerConsumed + graph[i][parent[i]];
		}	
		return powerConsumed;
	}


}
