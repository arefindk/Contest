import java.util.Scanner;
import java.util.Stack;


public class UVa_10004_Bicoloring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s= new Scanner (System.in);
		while(true){
			int nodeCount = s.nextInt();
			
			if(nodeCount==0){
				break;
			}
			
			boolean bicolor = true ;
			
			int edgeCount= s.nextInt();
			
			int[][] adjacencyMatrix =new int[nodeCount][nodeCount];
			int[] color = new int [nodeCount];//two different colors, 0 and 1
			int[] visit = new int[nodeCount];// 0 if a node not visited, 1 if visited
			
			for(int i= 0; i<edgeCount; i++){
				int u = s.nextInt();
				int v= s.nextInt();
				adjacencyMatrix[u][v]=1;
				adjacencyMatrix[v][u]=1;
			}
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(0);
			while( !stack.isEmpty()){
				if(!bicolor)break;
				int u = stack.pop();
				visit[u]=1;
				for(int v= 0; v<nodeCount; v++){
					if(!bicolor)break;
					if(adjacencyMatrix[u][v]==1 && visit[v]==1 && color[v]==color[u]){
						bicolor=false;
					}	
					if(adjacencyMatrix[u][v]==1 && visit[v]==0){
						stack.push(v);
						if(color[u]==0 && visit[v]==0){
							color[v]=1;
							visit[v]=1;
						}
						else if(color[u]==1 && visit[v]==0){
							color[v]=0;
							visit[v]=1;
						}					
					}
				}
			}
			if(bicolor)
				System.out.println("BICOLORABLE.");
			else System.out.println("NOT BICOLORABLE.");
		}
		
		s.close();
	}

}
