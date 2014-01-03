/*
 * Sample Dataset:
 * 
 * 
 ** Data set 1:
a b 10
a c 2
b d 4
c d 15
c b 3
exit
a
d

*
**Dataset 2: http://optlab-server.sce.carleton.ca/POAnimations2007/DijkstrasAlgo.html

o a 2
o b 5
o c 4
a f 12
a d 7
a b 2
b d 4
b c 1 
b e 3
c e 4
d t 5
e d 1
e t 7
f t 3
exit
o
t
* */

import java.util.*;

public class Dijkstra {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphV2<String> g =new GraphV2<String>();
		
		Scanner s= new Scanner(System.in);
		
		while(true){
			String nodeA = s.next();
			if(nodeA.equals("exit"))
				break;
			String nodeB = s.next();
			int weight = s.nextInt();
			
			g.addEdge(nodeA, nodeB, weight);
			
		}
		
		String startNode=s.next();
		String endNode= s.next();
		
		System.out.println(g.edges);
		
		Set<String> set = new LinkedHashSet<String>();
		
		
		TreeMap<Integer, String> extractMin = new TreeMap<Integer, String>();
		Map<String,Integer> dist = new HashMap<String,Integer>();
		Map<String, String> pred = new HashMap<String,String>();
		
		extractMin.put(0, startNode);
		dist.put(startNode, 0);
		pred.put(startNode, null);
		
		System.out.println("Initial extractMin "+ extractMin);
		TreeMap<String, Integer> edgesCurrentNode;
		
		while(set.size()!=g.nodeCount){ //!extractMin.isEmpty()
			
			Map.Entry<Integer, String> current = extractMin.pollFirstEntry();
			String currentNode= current.getValue();
			//System.out.println("CurrentNode starts "+currentNode);
			int distCurrentNode= dist.get(currentNode);
			//System.out.println("CurrentNode distance "+distCurrentNode);
			
			dist.put(currentNode, distCurrentNode);
			
			if(! set.contains(currentNode)){
				set.add(currentNode);		
				edgesCurrentNode= g.edges.get(currentNode);
				if (edgesCurrentNode.isEmpty()) break;
						
				for (Map.Entry<String, Integer> entry : edgesCurrentNode.entrySet()){
					String edgeNode = entry.getKey();
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
		s.close();
		System.out.println("Set "+set);
//		Iterator<String> itr = set.iterator();
//		while(itr.hasNext()){
//			Object current = itr.next();
//			System.out.println(current);
//			if(current.equals(endNode))
//				break;
//		}

		
		System.out.println("Distance Map "+dist);
		System.out.println("Pred Map "+pred);
		
		Stack<String> printPathStack = new Stack<String>();
		while(true){
			if(pred.get(endNode)==null) break;
			printPathStack.push(endNode);
			endNode=pred.get(endNode);
		}
		System.out.println(startNode);
		while(! printPathStack.empty()){
			System.out.println(printPathStack.pop());
		}
		
	}

}


