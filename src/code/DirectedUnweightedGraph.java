package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedUnweightedGraph<V> extends BaseGraph<V> {
  
  /*
   * YOU CAN ADD ANY FIELDS AND ADDITIONAL METHODS AS YOU LIKE
   * 
   */
	private Map<V, List<V>> adjacencyList;

    public DirectedUnweightedGraph() {
        adjacencyList = new HashMap<>();//Hashmap for reducing accessing elements
    }
  @Override
  public String toString() {
    String tmp = "Directed Unweighted Graph";
    return tmp;
  }

  @Override
  public void insertVertex(V v) {
    // TODO Auto-generated method stub
	  if(!adjacencyList.containsKey(v))adjacencyList.put(v, new ArrayList<>());
    
  }

  @Override
  public V removeVertex(V v) {
    // TODO Auto-generated method stub
	  if(!adjacencyList.containsKey(v))return null;
	  adjacencyList.remove(v);
	  for(V vertex : adjacencyList.keySet()) {
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
  }

  @Override
  public void insertEdge(V source, V target, float weight) {
    // TODO Auto-generated method stub
	  insertEdge(source, target);
  }

  @Override
  public boolean removeEdge(V source, V target) {
    // TODO Auto-generated method stub
	  if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).contains(target)) return false;
      adjacencyList.get(source).remove(target);
      return true;
  }

  @Override
  public float getEdgeWeight(V source, V target) {
    // TODO Auto-generated method stub
	  //////////////////////////////////////////////////////////////
	  return areAdjacent(source, target) ? 1.0f : 0.0f;
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
    return false;
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
          if (adjacencyList.get(vertex).contains(v)) {
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
      return adjacencyList.get(v);
  }

  @Override
  public Iterable<V> incomingNeighbors(V v) {
    // TODO Auto-generated method stub
	  List<V> incoming = new ArrayList<>();
      for (V vertex : adjacencyList.keySet()) {
          if (adjacencyList.get(vertex).contains(v)) {
              incoming.add(vertex);
          }
      }
      return incoming;
  
  }
}
