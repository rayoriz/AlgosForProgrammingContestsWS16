package week3;
import java.io.*;

// Code inspired from geeks for geeks implementation of Prims algorithm, same as used in Question A
class QuestionE{
	
	public static void main (String[] args) throws NumberFormatException, IOException, Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases= Integer.parseInt(br.readLine());
		for(int i=1;i<=cases;i++){
			int input1= Integer.parseInt(br.readLine());
			int graph[][] = new int[input1][input1];
			for(int j=0;j<input1;j++){
				String input2  = br.readLine();
				String[] temp2  = input2.split(" ");
				for(int k=j+1;k<input1;k++){
					graph[j][k] = Integer.parseInt(temp2[k]);
					graph[k][j] = Integer.parseInt(temp2[k]);
				}
			}
			QuestionE travelWay = new QuestionE(input1);
			String houseSequence = travelWay.primMST(graph);
			System.out.println("Case #"+i+": "+houseSequence);
			br.readLine();
		}
	}
	
	
	private int V;
	private String houseSequence = "";
	public QuestionE(int v){
		this.V=v;
	}
	int minKey(int key[], Boolean mstSet[],int graph[][],int u,int[] parent)
	{
		
		int min = Integer.MAX_VALUE, min_index=0;
		int inList = 0;
		for (int v = 0; v < V; v++){
			if (mstSet[v] == false && key[v] < min){
				if(parent[v]!=-1 && graph[parent[v]][V-1]==key[v]+graph[v][V-1]){
					if(graph[v][V-1]==0){
						min_index = -1;
						inList =v+1;
						break;
					}
					min = key[v];
					min_index = v;
					inList =v+1;
				}
			}
		}
		if(inList!=0){
			houseSequence += " "+inList;
		}
		return min_index;
	}
	String primMST(int graph[][])
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
		int u = 0;
		// always add the first vertex
		houseSequence+= "1"; // 
		for (int count = 0; count < V; count++)
		{
			u = minKey(key, mstSet,graph,u,parent);
			if(u==-1){ // exit sequence
				break;
			}
			mstSet[u] = true;
			for (int v = 0; v < V; v++)
				if (graph[u][v]!=0 && mstSet[v] == false &&
					graph[u][v] < key[v])
				{
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
    	return houseSequence;
	}

	
}
