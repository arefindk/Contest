/*
	Special Thanks to https://github.com/indy256
*/
import java.util.*;

public class Graph <T> {
  public Map<T, Set<T>> edges = new TreeMap<T, Set<T>>();

  
  boolean undirected;
  int edgeCount, nodeCount;
  
  public Graph(){
	  undirected = false;
	  edgeCount = 0;
	  nodeCount=0;
  };
  
  public Graph(boolean undirected){
	  this.undirected = undirected;
	  edgeCount = 0;
	  nodeCount=0;
  }
  
  public void addNode(T u) {
    if (!edges.containsKey(u)) {
      edges.put(u, new TreeSet<T>());
      nodeCount++;
    }
  }

  public void removeNode(T u) {
    if (!edges.containsKey(u)) {
      return;
    }
    for (T v : edges.get(u)) {
      edges.get(v).remove(u);
      edgeCount--;
    }
    edges.remove(u);
  }

  public void addEdge(T u, T v) {
    if(undirected){
    	addNode(u);
    	addNode(v);
        edges.get(u).add(v);
        edges.get(v).add(u);
        edgeCount += 2 ;
    }
    else{
    	addNode(u);
    	addNode(v);
        edges.get(u).add(v);
        edgeCount++;
    }
  }
  
  public void removeEdge (T u, T v){
	  if(undirected){
	        edges.get(u).remove(v);
	       //if (edges.get(u).isEmpty()) removeNode(u); //not good, a graph may have disconnected nodes
	        edges.get(v).remove(u);
	        //if (edges.get(u).isEmpty()) removeNode(v); //not good, a graph may have disconnected nodes
	        edgeCount -= 2 ;
	    }
	  else if(!undirected){
		  	edges.get(u).remove(v);
		  	//if (edges.get(u).isEmpty()) removeNode(u); //not good, a graph may have disconnected nodes
		  	edgeCount--;		  	
	  }
  }
  
  public int degreeCount(T node){
	  if(undirected)
		  return edges.get(node).size()/2; // I am not sure if it is the right procedure to cut the degree count into half is the graph is undirected
	  else
		  return edges.get(node).size();
  }


  public static void main(String[] args) {
    Graph<Integer> g = new Graph<Integer>();
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    System.out.println(g.edges);
    System.out.println(g.degreeCount(2));
    System.out.println(g.nodeCount);
    System.out.println(g.edgeCount);
    
    g.removeEdge(3, 2);
    System.out.println(g.edges);
    System.out.println(g.nodeCount);
    System.out.println(g.edgeCount);
  }
}