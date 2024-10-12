package code;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * The class that will hold your graph algorithm implementations
 * Implement:
 * - Depth first search
 * - Breadth first search
 * - Dijkstra's single-source all-destinations shortest path algorithm
 * 
 * Feel free to add any addition methods and fields as you like
 */
public class GraphAlgorithms<V extends Comparable<V>> {
  
  /*
   * YOU CAN ADD ANY FIELDS AND ADDITIONAL METHODS AS YOU LIKE
   * 
   */
  
  public static boolean usageCheck = false;
  
  /*
   * WARNING: MUST USE THIS FUNCTION TO SORT THE 
   * NEIGHBORS (the adjacent call in the pseudocodes)
   * FOR DFS AND BFS
   * 
   * THIS IS DONE TO MAKE AUTOGRADING EASIER
   */
  public Iterable<V> iterableToSortedIterable(Iterable<V> inIterable) {
    usageCheck = true;
    List<V> sorted = new ArrayList<>();
    for (V i : inIterable) {
      sorted.add(i);
    }
    Collections.sort(sorted);
    return sorted;
  }
  
  /*
   * Runs depth first search on the given graph G and
   * returns a list of vertices in the visited order, 
   * starting from the startvertex.
   * 
   */
  public List<V> DFS(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    //TODO
    
    List<V> result = new ArrayList<>();
    Set<V> visited = new HashSet<>();
    Stack<V> stack = new Stack<>();

    stack.push(startVertex);

    while (!stack.isEmpty()) {
        V vertex = stack.pop();

        if (!visited.contains(vertex)) {
            visited.add(vertex);
            result.add(vertex);

            for (V neighbor : iterableToSortedIterable(G.outgoingNeighbors(vertex))) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    return result;
  }
  
  /*
   * Runs breadth first search on the given graph G and
   * returns a list of vertices in the visited order, 
   * starting from the startvertex.
   * 
   */
  public List<V> BFS(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    //TODO
    List<V> result = new ArrayList<>();
    Set<V> visited = new HashSet<>();
    Queue<V> queue = new LinkedList<>();

    queue.add(startVertex);
    visited.add(startVertex);

    while (!queue.isEmpty()) {
        V vertex = queue.poll();
        result.add(vertex);

        for (V neighbor : iterableToSortedIterable(G.outgoingNeighbors(vertex))) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
    }

    return result;
  }
  
  /*
   * Runs Dijkstras single source all-destinations shortest path 
   * algorithm on the given graph G and returns a map of vertices
   * and their associated minimum costs, starting from the startvertex.
   * 
   */
  public HashMap<V,Float> Dijkstras(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    //TODO
    HashMap<V, Float> distances = new HashMap<>();
    PriorityQueue<Map.Entry<V, Float>> priorityQueue = new PriorityQueue<>(Map.Entry.comparingByValue());

    for (V vertex : G.vertices()) {
        distances.put(vertex, Float.MAX_VALUE);
    }
    distances.put(startVertex, 0.0f);

    priorityQueue.add(new AbstractMap.SimpleEntry<>(startVertex, 0.0f));

    while (!priorityQueue.isEmpty()) {
        V currentVertex = priorityQueue.poll().getKey();

        for (V neighbor : G.outgoingNeighbors(currentVertex)) {
            float edgeWeight = G.getEdgeWeight(currentVertex, neighbor);
            float newDist = distances.get(currentVertex) + edgeWeight;

            if (newDist < distances.get(neighbor)) {
                distances.put(neighbor, newDist);
                priorityQueue.add(new AbstractMap.SimpleEntry<>(neighbor, newDist));
            }
        }
    }

    return distances;
  }
  
  /*
   *  Returns true if the given graph is cyclic, false otherwise
   */
  
  private boolean isCyclicUtil(V vertex, Set<V> visited, Set<V> recursionStack, BaseGraph<V> G) {
      if (recursionStack.contains(vertex)) {
          return true;
      }
      if (visited.contains(vertex)) {
          return false;
      }

      visited.add(vertex);
      recursionStack.add(vertex);

      for (V neighbor : G.outgoingNeighbors(vertex)) {
          if (isCyclicUtil(neighbor, visited, recursionStack, G)) {
              return true;
          }
      }

      recursionStack.remove(vertex);
      return false;
  }
  
  
  
  
  public boolean isCyclic(BaseGraph<V> G) {
    //TODO
	  Set<V> visited = new HashSet<>();
      Set<V> recursionStack = new HashSet<>();

      for (V vertex : G.vertices()) {
          if (isCyclicUtil(vertex, visited, recursionStack, G)) {
              return true;
          }
      }
      return false;
  }

}
