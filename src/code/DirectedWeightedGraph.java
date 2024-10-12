package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedWeightedGraph<V> extends BaseGraph<V>  {

  /*
   * YOU CAN ADD ANY FIELDS AND ADDITIONAL METHODS AS YOU LIKE
   * 
   */
  
	private Map<V, Map<V, Float>> adjacencyList;

    public DirectedWeightedGraph() {
        adjacencyList = new HashMap<>();
    }
	
  @Override
  public String toString() {
    String tmp = "Directed Weighted Graph";
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
    
  }

  @Override
  public boolean removeEdge(V source, V target) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).containsKey(target)) {
          return false;
      }
      adjacencyList.get(source).remove(target);
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

  @Override
  public int numEdges() {
    // TODO Auto-generated method stub
	  int count = 0;
      for (V vertex : adjacencyList.keySet()) {
          count += adjacencyList.get(vertex).size();
      }
      return count;
  }

  @Override
  public boolean isDirected() {
    // TODO Auto-generated method stub
    return true;
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
      return adjacencyList.get(v).size();
  }

  @Override
  public int inDegree(V v) {
    // TODO Auto-generated method stub
	  int count = 0;
      for (V vertex : adjacencyList.keySet()) {
          if (adjacencyList.get(vertex).containsKey(v)) {
              count++;
          }
      }
      return count;
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
	  List<V> incoming = new ArrayList<>();
      for (V vertex : adjacencyList.keySet()) {
          if (adjacencyList.get(vertex).containsKey(v)) {
              incoming.add(vertex);
          }
      }
      return incoming;
  }
}
