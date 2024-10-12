package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedUnweightedGraph<V> extends BaseGraph<V> {
  
  /*
   * YOU CAN ADD ANY FIELDS AND ADDITIONAL METHODS AS YOU LIKE
   * 
   */
  
	
	private Map<V, List<V>> adjacencyList;

    public UndirectedUnweightedGraph() {
        adjacencyList = new HashMap<>();
    }
  
  @Override
  public String toString() {
    String tmp = "Undirected Unweighted Graph";
    return tmp;
  }

  @Override
  public void insertVertex(V v) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(v)) {
          adjacencyList.put(v, new ArrayList<>());
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
	return adjacencyList.containsKey(v2)&&adjacencyList.get(v2).contains(v1);
  }

  @Override
  public void insertEdge(V source, V target) {
    // TODO Auto-generated method stub
    
	  if (!adjacencyList.containsKey(source)) {
          insertVertex(source);
      }
      if (!adjacencyList.containsKey(target)) {
          insertVertex(target);
      }
      adjacencyList.get(source).add(target);
      adjacencyList.get(target).add(source); // directional edge for both side
  }

  @Override
  public void insertEdge(V source, V target, float weight) {
    // TODO Auto-generated method stub
      insertEdge(source, target);// Unweighted graph, so ignore the weight
  }

  @Override
  public boolean removeEdge(V source, V target) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).contains(target)) {
          return false;
      }
      adjacencyList.get(source).remove(target);
      adjacencyList.get(target).remove(source); // Remove bidirectional edge
      return true;
    
  }

  @Override
  public float getEdgeWeight(V source, V target) {
    // TODO Auto-generated method stub
	
      return areAdjacent(source, target) ? 1.0f : 0.0f;// Unweighted graph, so always return 1.0 if the edge exists, else 0.0
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

  @Override
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
    return false;
  }

  @Override
  public int outDegree(V v) {
    // TODO Auto-generated method stub
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
	return adjacencyList.containsKey(v) ? adjacencyList.get(v) : new ArrayList<>();
  }

  @Override
  public Iterable<V> incomingNeighbors(V v) {
    // TODO Auto-generated method stub
	return outgoingNeighbors(v);// Incoming neighbors are same as outgoing neighbors in undirected graph, 
  }

}
