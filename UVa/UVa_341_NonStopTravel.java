import java.util.*;

public class UVa_341_NonStopTravel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s= new Scanner (System.in);
		int caseNumber = 0;

		
		while(true){
			int caseCount=s.nextInt();
			if(caseCount==0) break;
			caseNumber++;
			int nodeA, nodeB;
			GraphV2<Integer> g= new GraphV2<Integer>();
			int cost;
			for(int i=1; i<=caseCount; i++){
				nodeA=i;
				int edgesFromCurrentNode = s.nextInt();
				while(edgesFromCurrentNode-- >0){
					nodeB=s.nextInt();
					cost= s.nextInt();
					g.addEdge(nodeA, nodeB, cost);
				}
			}
			
			
			
			
			//System.out.println(g.edges);
			//System.out.println(g.nodeList);
			
			
			Integer startNode=s.nextInt();
			Integer endNode= s.nextInt();
			
			//System.out.println(g.edges);
			
			Set<Integer> set = new LinkedHashSet<Integer>();
			
			
			TreeMap<Integer, Integer> extractMin = new TreeMap<Integer, Integer>();
			Map<Integer,Integer> dist = new HashMap<Integer,Integer>();
			Map<Integer, Integer> pred = new HashMap<Integer,Integer>();
			
			extractMin.put(0, startNode);
			dist.put(startNode, 0);
			pred.put(startNode, null);
			
			//System.out.println("Initial extractMin "+ extractMin);
			TreeMap<Integer, Integer> edgesCurrentNode;
			
			//System.out.println("Set Size "+set.size());
			//System.out.println("NodeCount "+g.nodeCount);
			
			
			while(set.size() != g.nodeCount){ //!extractMin.isEmpty()
				
				Map.Entry<Integer, Integer> current = extractMin.pollFirstEntry();
				Integer currentNode= current.getValue();
				//System.out.println("CurrentNode starts "+currentNode);
				int distCurrentNode= dist.get(currentNode);
				//System.out.println("CurrentNode distance "+distCurrentNode);
				
				dist.put(currentNode, distCurrentNode);
				//System.out.println("dist "+dist);
	
				if(! set.contains(currentNode)){
					set.add(currentNode);		
					edgesCurrentNode= g.edges.get(currentNode);
					if (edgesCurrentNode.isEmpty()) break;
					//System.out.println("currentNode edges "+ edgesCurrentNode);
							
					for (Map.Entry<Integer, Integer> entry : edgesCurrentNode.entrySet()){
						Integer edgeNode = entry.getKey();
						int distFromCurrentNode = entry.getValue();
						if(! dist.containsKey(edgeNode)){
							//System.out.println("if Current Distance of edgeNode "+edgeNode+" from "+ currentNode+ " is "+(dist.get(currentNode)+distFromCurrentNode));
							dist.put(edgeNode, dist.get(currentNode)+distFromCurrentNode );
							pred.put(edgeNode,	currentNode);
							//System.out.println("dist "+ dist);
						}
						
						else{
							//System.out.println("else Current Distance of edgeNode "+edgeNode+" from "+ currentNode+ " is "+(dist.get(currentNode)+distFromCurrentNode));
							int updatedDistnace= dist.get(currentNode)+distFromCurrentNode;
							if(dist.get(edgeNode)> updatedDistnace){
								dist.put(edgeNode, updatedDistnace);
								pred.put(edgeNode, currentNode);
								//System.out.println("dist "+ dist);
							}
						}
						
						extractMin.put(distFromCurrentNode,edgeNode);
					}
					
					//System.out.println("Every iteration extractMin "+extractMin);
				}
			}
			
			//System.out.println("Set "+set);
	//		Iterator<String> itr = set.iterator();
	//		while(itr.hasNext()){
	//			Object current = itr.next();
	//			System.out.println(current);
	//			if(current.equals(endNode))
	//				break;
	//		}
			Integer nodeIterator;
			nodeIterator=endNode;
			//System.out.println("Distance Map "+dist);
			//System.out.println("Pred Map "+pred);
			System.out.print("Case "+caseNumber+": Path = ");
			Stack<Integer> printPathStack = new Stack<Integer>();
			while(true){
				if(pred.get(nodeIterator)==null) break;
				printPathStack.push(nodeIterator);
				nodeIterator=pred.get(nodeIterator);
			}
			System.out.print(startNode);
			while(! printPathStack.empty()){
				System.out.print(" "+printPathStack.pop());
			}
			System.out.println("; "+dist.get(endNode)+" second delay");
		}
		s.close();
	}
	

}
