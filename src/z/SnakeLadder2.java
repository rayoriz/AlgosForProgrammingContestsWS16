package z;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class SnakeLadder2
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int x = 1; x <= t; x++) 
		{
			String[] inp1 = br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);
			int[] moves = new int[n];
			int[] moves2 = new int[n];
			for(int i=0;i<Integer.parseInt(inp1[1]);i++){
				 String[] inp2=br.readLine().split(" ");
				 moves[Integer.parseInt(inp2[0])-1] = Integer.parseInt(inp2[1])-1;
				 //moves2[Integer.parseInt(inp2[0])-1] = Integer.parseInt(inp2[1])-1;
			}
			for(int i=0;i<Integer.parseInt(inp1[2]);i++){
				 String[] inp2=br.readLine().split(" ");
//				 if(Integer.parseInt(inp2[1]) > Integer.parseInt(inp2[0])){
//					 moves[Integer.parseInt(inp2[0])-1] = Integer.parseInt(inp2[1])-1;
//				 }
				 moves2[Integer.parseInt(inp2[0])-1] = Integer.parseInt(inp2[1])-1;
			}
		 getmindicethrow(moves,moves2,n,x);
		 br.readLine();
		}
	}

	public static void getmindicethrow(int[] moves1,int[] moves2, int n,int cas){
		int[] moves = moves1;
		int count = 1;
		Integer[] res = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
		for(int k=1;k<=6;k++){
//			PriorityQueue<queueEntry> pq=new PriorityQueue<queueEntry>(new Comparator<queueEntry>(){
//
//				@Override
//				public int compare(queueEntry arg0, queueEntry arg1) {
//					
//					return arg0.dist-arg1.dist;
//				}
//
//			});

			boolean[] visited = new boolean[n];
			boolean loop = false; 
			PriorityQueue<queueEntry> q = new PriorityQueue<queueEntry>();
			visited[0] = true;
			queueEntry s = new queueEntry();
			s.v = 0;
			s.dist = 0;
			q.offer(s);
			queueEntry qe = new queueEntry();
			int j=0;
			int count2=0;
			while(!q.isEmpty()){
				count2++;
				if(count2==n){
					loop=true;
					break;
				}
				qe = q.peek();
				int v = qe.v;
				if(v==n-1)
					break;
				if(v+k<n){
					j=v+k;
				}
				else{
					j=n-1;
				}
				if(visited[j]){
					qe.dist=Integer.MAX_VALUE;
					break;
				}

				q.poll();
				queueEntry a = new queueEntry();
				a.dist = qe.dist;//dice roll number
				visited[j] = true;
				if(moves[j] != 0){//snake is there
					a.v = moves[j];
					q.offer(a);

				}
				else if(moves2[j] != 0){
					a.v = moves[j];
					q.offer(a);
					a.dist = qe.dist+1;//dice roll number
					a.v = j;
					q.offer(a);
				}
			}
			if(loop){
				res[k-1]=Integer.MAX_VALUE;
				count++;
			}
			res[k-1]=qe.dist;
//			if(count==1 && qe.dist==Integer.MAX_VALUE){
//				count++;
//				moves =moves2;
//				k--;
//			}
//			else
//			{
//				res[k-1]=qe.dist;
//				moves= moves1;
//				count=1;
//			}
		}
		int min=Integer.MAX_VALUE-5;
		StringBuffer sb = new StringBuffer("Case #"); 
		sb.append(cas);
		if(count == 6){
			sb.append(": ");
			sb.append("impossible");
		}
		else{
			for(int k=0;k<6;k++){
				if(res[k]==min){
					sb.append(" "+(k+1));
				}
				else if(res[k]<min){
					sb=new StringBuffer("Case #");
					sb.append(cas);
					sb.append(": ");
					sb.append(k+1);
					min=res[k];
				}
			}
			
		}
//		boolean flag=false;
//		StringBuffer sb = new StringBuffer("Case #"); 
//		sb.append(cas);
//		sb.append(": ");
//		int min=Integer.MAX_VALUE-5;
//		for(int k=0;k<6;k++){
//			if(res[k]==min){
//				sb.append(" "+(k+1));
//			}
//			else if(res[k]<min){
//				flag=true;
//				sb=new StringBuffer("Case #");
//				sb.append(cas);
//				sb.append(": ");
//				sb.append(k+1);
//				min=res[k];
//			}
//		}
//		if(!flag)
//			sb.append("impossible");
//		System.out.println(sb);
	}
}
class queueEntry{
	int v;
	int dist;
}