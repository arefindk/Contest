/*
	Special Thanks to https://github.com/indy256
	
	added nodeCount, edgeCount, nodeList, removeEdge, Total degreeCount of the graph, directed/ undirected, weighted/unweighted,
 */
import java.util.*;

public class GraphV2<T> {
	public Map<T, TreeMap<T, Integer>> edges = new TreeMap<T, TreeMap<T, Integer>>();
	
	public LinkedList<T> nodeList = new LinkedList<T>();
	
	//public Map<Integer,T> extractMin = new HashMap<Integer,T>(); //for dijkstra algorithm
	
	TreeMap<T,Integer> v = new TreeMap<T, Integer>();
	
	
	boolean undirected;
	int edgeCount, nodeCount;

	public GraphV2() {
		undirected = false;
		edgeCount = 0;
		nodeCount = 0;
	};

	public GraphV2(boolean undirected) {
		this.undirected = undirected;
		edgeCount = 0;
		nodeCount = 0;
	}

	public void addNode(T u) {
		if (!edges.containsKey(u)) {
			edges.put(u, new TreeMap<T, Integer>());
			nodeList.add(u);
			nodeCount++;
		}
	}

	public void removeNode(T u) {
		if (!edges.containsKey(u)) {
			return;
		}

		for (Map.Entry<T, Integer> entry : edges.get(u).entrySet()) {
			if (!edges.get(entry.getKey()).isEmpty()) {
				edges.get(entry.getKey()).remove(u);
				edgeCount--;
				//System.out.println(this.edges);
				//System.out.println("Total Edgecount now " + this.edgeCount);
			}
		}
		//System.out.println("The size of node " + u + " is " + edges.get(u).size());
		if (!edges.get(u).isEmpty())
			edgeCount = edgeCount - edges.get(u).size();
		edges.remove(u);
		nodeList.remove(u);
	}

	public void addEdge(T u, T v) {
		if (undirected) {
			addNode(u);
			addNode(v);
			edges.get(u).put(v, 1);
			edges.get(v).put(u, 1);
			edgeCount += 2;
		} else {
			addNode(u);
			addNode(v);
			edges.get(u).put(v, 1);
			edgeCount++;
		}
	}

	public void addEdge(T u, T v, Integer weight) {
		if (undirected) {
			addNode(u);
			addNode(v);
			edges.get(u).put(v, weight);
			edges.get(v).put(u, weight);
			edgeCount += 2;
		} else {
			addNode(u);
			addNode(v);
			edges.get(u).put(v, weight);
			edgeCount++;
		}
	}

	public void removeEdge(T u, T v) {
		if (undirected) {
			edges.get(u).remove(v);
			edgeCount--;
			// if (edges.get(u).isEmpty()) removeNode(u); //not good, a graph
			// may have disconnected nodes
			edges.get(v).remove(u);
			edgeCount--;
			// if (edges.get(u).isEmpty()) removeNode(v); //not good, a graph
			// may have disconnected nodes
			// edgeCount = edgeCount - 2 ;
		} else if (!undirected) {
			edges.get(u).remove(v);
			// if (edges.get(u).isEmpty()) removeNode(u); //not good, a graph
			// may have disconnected nodes
			edgeCount--;
		}
	}

	public int degreeCount(T node) {
		if (undirected)
			return edges.get(node).size() / 2; // I am not sure if it is the
												// right procedure to cut the
												// degree count into half is the
												// graph is undirected
		else
			return edges.get(node).size();
	}

	public static void main(String[] args) {
		GraphV2<Integer> g = new GraphV2<Integer>(true);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,1);
		g.addEdge(4,2);
		g.addEdge(1,5);
		g.addEdge(3,6);
		System.out.println(g.edges);
		System.out.println(g.nodeCount);
		System.out.println(g.edgeCount);
		System.out.println(g.nodeList);
		/* 
		 * this is the implementation of the vertex cover greedy algorithm taught in UIU
		 * 
		 * */
		TreeSet<Integer> c = new TreeSet<Integer>();		
		int v;
		while (g.edgeCount>0){
			v=g.nodeList.pop();
			c.add(v);
			g.removeNode(v);
			System.out.println(g.edges);
		}
		System.out.println(c);
		
	}
}