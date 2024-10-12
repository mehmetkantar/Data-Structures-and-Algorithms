package code;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement a binary search tree based priority queue
 * Do not try to create heap behavior (e.g. no need for a last node)
 * Just use default binary search tree properties
 */

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */
  
  @Override
  public void insert(Key k, Value v) {
    // TODO Auto-generated method stub
	  put(k,v);
    
  }

  @Override
  public Entry<Key, Value> pop() {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()){
	      return null ;
	  }
	  
	  Key key = findMinNode().getKey();
	  Value value  = remove(key);
	  Entry<Key,Value> entry = new Entry<>(key,value);

	  return entry;
  }

  @Override
  public Entry<Key, Value> top() {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()){
	      return null ;
	  }
	  
	  Key key = findMinNode().getKey();
	  Value value = getValue(key);
	  Entry<Key,Value> entry = new Entry<>(key,value);
	  return entry;
	  
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
    // TODO Auto-generated method stub
	  
	  
	  BinaryTreeNode<Key,Value> node = getNode(entry.getKey());
	  if(node == null ){
	      return null;
	  }
	  
	  Key oldkey = entry.getKey();
	  Value sameVal = entry.getValue();
	  remove(node.getKey());
	  put(k,sameVal);

	  return oldkey;
  }

  @Override
  public Key replaceKey(Value v, Key k) {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()){
	      return null ;
	  }
	  
	  BinaryTreeNode<Key,Value> node = findNodeByValue(v);
	  if (node == null ){
	      return null;
	      
	  }else{
		  
		  Key key = node.getKey();
	      Value val = node.getValue();
	      remove(node.getKey());
	      put(k,val);
	      return key;
	  }
	  
  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()){
	      return null ;
	  }
	  
	  BinaryTreeNode<Key,Value> node = getNode(entry.getKey());
	  if(node == null){
	      return null;
	  }
	  
	  Value value = node.getValue();
	  node.setValue(v);
	  return value;

  }


}
