package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UndirectedWeightedGraph<V> extends BaseGraph<V> {

  /*
   * YOU CAN ADD ANY FIELDS AND ADDITIONAL METHODS AS YOU LIKE
   * 
   */
	
	private Map<V, Map<V, Float>> adjacencyList;

    public UndirectedWeightedGraph() {
        adjacencyList = new HashMap<>();
    }

  @Override
  public String toString() {
    String tmp = "Undirected Weighted Graph";
    return tmp;
  }

  @Override
  public void insertVertex(V v) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(v)) {
          adjacencyList.put(v, new HashMap<>());
      }
  }

  @Override
  public V removeVertex(V v) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(v)) {
          return null;
      }
      adjacencyList.remove(v);
      for (V vertex : adjacencyList.keySet()) {
          adjacencyList.get(vertex).remove(v);
      }
      return v;
  }

  @Override
  public boolean areAdjacent(V v1, V v2) {
    // TODO Auto-generated method stub
	  return adjacencyList.containsKey(v2) && adjacencyList.get(v2).containsKey(v1);
  }

  @Override
  public void insertEdge(V source, V target) {
    // TODO Auto-generated method stub
	  insertEdge(source, target, 1.0f); // Default weight is 1.0
  }

  @Override
  public void insertEdge(V source, V target, float weight) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source)) {
          insertVertex(source);
      }
      if (!adjacencyList.containsKey(target)) {
          insertVertex(target);
      }
      adjacencyList.get(source).put(target, weight);
      adjacencyList.get(target).put(source, weight);//Edge is directional in both way
  }

  @Override
  public boolean removeEdge(V source, V target) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).containsKey(target)) {
          return false;
      }
      adjacencyList.get(source).remove(target);
      adjacencyList.get(target).remove(source); // Edge is removed bidirectionally in both way
      return true;
  }

  @Override
  public float getEdgeWeight(V source, V target) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).containsKey(target)) {
          return Float.POSITIVE_INFINITY;
      }
      return adjacencyList.get(source).get(target);
  }

  @Override
  public int numVertices() {
    // TODO Auto-generated method stub
    return adjacencyList.size();
  }

  @Override
  public Iterable<V> vertices() {
    // TODO Auto-generated method stub
    return adjacencyList.keySet();
  }

  public int numEdges() {
	    // TODO Auto-generated method stub
		  int count = 0;
	      for (V vertex : adjacencyList.keySet()) {
	          count += adjacencyList.get(vertex).size();
	      }
	      
	      return count / 2;// Each edge is counted twice because the graph is undirected, 
	  }

	  @Override
	  public boolean isDirected() {
	    // TODO Auto-generated method stub
	    return false;
	  }

	  @Override
	  public boolean isWeighted() {
	    // TODO Auto-generated method stub
	    return true;
	  }

	  @Override
	  public int outDegree(V v) {
	    // TODO Auto-generated method stub
		  if (!adjacencyList.containsKey(v)) {
	            return 0;
	        }
		  return adjacencyList.containsKey(v) ? adjacencyList.get(v).size() : 0;
	  }

	  @Override
	  public int inDegree(V v) {
	    // TODO Auto-generated method stub
	    return outDegree(v);// Same with outdegree due to undirected graph
	  }

	  @Override
	  public Iterable<V> outgoingNeighbors(V v) {
	    // TODO Auto-generated method stub
		  if (!adjacencyList.containsKey(v)) {
	            return new ArrayList<>();
	      }
	      return adjacencyList.get(v).keySet();
	  }

	  @Override
	  public Iterable<V> incomingNeighbors(V v) {
	    // TODO Auto-generated method stub
		return outgoingNeighbors(v);// Incoming neighbors are same as outgoing neighbors in undirected graph, 
	  }
}
