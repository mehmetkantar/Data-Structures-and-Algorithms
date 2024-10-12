package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import given.iMap;
import given.iBinarySearchTree;

/*
 * Implement a vanilla binary search tree using a linked tree representation
 * Use the BinaryTreeNode as your node class
 */

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {
  
  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */
	  Comparator<Key> comparator;
	  private BinaryTreeNode<Key,Value> root= new BinaryTreeNode<Key,Value>(null ,null);
	  private int size = 0 ;
  
  @Override
  public Value get(Key k) {
    // TODO Auto-generated method stub
	  
	  BinaryTreeNode<Key,Value> node = getNode(k);
	  if(node!=null) {
	    return node.getValue();
	  }
	  
	  return null;
	  
	    
  }
  
  
  public BinaryTreeNode<Key,Value> treeSearch(Key k,BinaryTreeNode<Key,Value>  node ) {

	  
	  //Case1
	  if (this.isExternal(node)) {
	      return node;
	  }
	  
	  //Case2
	  if (comparator.compare(k, node.getKey()) < 0) {
	      return treeSearch(k,node.getLeftchild());
	      
	  //Case3    
	  } else if (comparator.compare(k,  node.getKey())==0) { 
		  return node;
	   
	  //Case4	  
	  }else{
	      return treeSearch(k,node.getRightchild()); 
	  }

  }

  
  @Override
  public Value put(Key k, Value v) {
    // TODO Auto-generated method stub
	  
	  if (!root.isEmptyNode()) {
	      
	      BinaryTreeNode<Key, Value> node = getNode(k);
	      if (node == null) {
	    	  
	    	  BinaryTreeNode<Key, Value> searchedNode = treeSearch(k,root);
	    	  searchedNode.setKey(k);
	    	  searchedNode.setValue(v);
	    	  searchedNode.putDummy();
	    	  size++;
	    	  return null;        
	        
	      }else {
	    	  Value temp = node.getValue();
	    	  node.setValue(v);
	    	  return temp;
	    	  
	      }
	      
	  }else {
		  root = new BinaryTreeNode<>(k, v);
	      root.putDummy();
	      size++;
	      return null;
		  
	 }

  }
  
  public BinaryTreeNode<Key,Value> findMostLeft(BinaryTreeNode<Key,Value> node){

	  if(!node.hasLeftChild()){
	      return node;
	      
	  }else{
	      return findMostLeft(node.getLeftchild());
	  }

  }
  
  public BinaryTreeNode<Key,Value> findMinNode() {
	    
	  return findMostLeft(root);

  }


  @Override
  public Value remove(Key k) {
      BinaryTreeNode<Key, Value> node = getNode(k);

      if (node == null || isEmpty()) {
          return null;
      } else if (node.hasRightChild() && node.hasLeftChild()) {
          // Node with two children
          BinaryTreeNode<Key, Value> successor = findMostLeft(node.getRightchild());
          Value oldValue = node.getValue();
          node.setKey(successor.getKey());
          node.setValue(successor.getValue());
          // Remove the successor node directly
          removeSuccessor(node.getRightchild());
          return oldValue;
      } else {
          // Node with one or no child
          return removeNode(node);
      }
  }

  private Value removeSuccessor(BinaryTreeNode<Key, Value> node) {
      // If node is the successor
      if (!node.hasLeftChild()) {
          return removeNode(node);
      } else {
          return removeSuccessor(node.getLeftchild());
      }
  }

  private Value removeNode(BinaryTreeNode<Key, Value> node) {
      BinaryTreeNode<Key, Value> child = node.hasLeftChild() ? node.getLeftchild() : node.getRightchild();
      Value oldValue = node.getValue();

      if (isRoot(node)) {
          root = child;
          if (root != null) {
              root.setParent(null);
          }
      } else if (isLeftChild(node)) {
          node.getParent().setLeftchild(child);
      } else {
          node.getParent().setRightchild(child);
      }
      if (child != null) {
          child.setParent(node.getParent());
      }
      node.setToDummy();
      size--;
      return oldValue;
  }


  
  
  public void addToIterable(BinaryTreeNode<Key,Value> node, ArrayList<Key> arrayList){
	    
	  if(node.isEmptyNode()){
	      return;
	  }
	  
	  if (!arrayList.contains(node.getKey())) {
	      arrayList.add(node.getKey());
	  }
	  
	  addToIterable(node.getLeftchild(),arrayList);
	  addToIterable(node.getRightchild(),arrayList);
  }

  
  @Override
  public Iterable<Key> keySet() {
    // TODO Auto-generated method stub
	  ArrayList<Key> list = new ArrayList<>();
	  addToIterable(root,list);
	  return list;
    
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
	boolean flag = (size==0);
	return flag;
  }

  @Override
  public BinaryTreeNode<Key, Value> getRoot() {
    // TODO Auto-generated method stub
    return root;
  }

  @Override
  public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
    return node.getParent();
  }

  @Override
  public boolean isInternal(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
	  boolean flag = !isExternal(node);
	  return flag;
  }

  @Override
  public boolean isExternal(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
    return node == null || node.isEmptyNode();
  }

  @Override
  public boolean isRoot(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
	  boolean flag = (node==root);
	  return flag;
  }

  @Override
  public BinaryTreeNode<Key, Value> getNode(Key k) {
    // TODO Auto-generated method stub
	  
	  BinaryTreeNode<Key, Value> node = treeSearch(k,root);
	  if(node == null|| node.isEmptyNode()){
		  return null;
	  }
	  return node;
  }

  @Override
  public Value getValue(Key k) {
    // TODO Auto-generated method stub
	  
	  BinaryTreeNode<Key, Value> node = getNode(k);
	  if(node == null){
	      return null;
	  }else{return node.getValue();}

  }

  @Override
  public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
    return node.getLeftchild();
  }

  @Override
  public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
    return node.getRightchild();
  }

  @Override
  public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
	  if(node.getParent()==null){
	      	return null;
	  }else {
		    if(node.getParent().getLeftchild() == node){
			      if (!node.getParent().hasRightChild()){
			    	  return null;
			      }else {
			    	  return node.getParent().getRightchild();
			      }
		    }else{
			      if(!node.getParent().hasLeftChild()){
			    	  return null;
			      }
			      else{
			    	  return node.getParent().getLeftchild();
			      }
		    }
	  }
  }

  @Override
  public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
	  	  
	  if(node.getParent()==null){
	      return false;
	      
	  }else{
		  boolean flag = node.getParent().getLeftchild()==node;
	      return flag;
	  }
  }

  @Override
  public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
    // TODO Auto-generated method stub
	  
	  
	  if(node.getParent()==null){
	      return false;
	      
	  }else{
		  boolean flag = node.getParent().getRightchild()==node;
	      return flag;
	  }
  }
  
  
  //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
  public void  inTraversal(BinaryTreeNode<Key,Value> node , ArrayList<Key> list){

	    if(node == null || node.isEmptyNode()){
	      return;
	    }
	    
	    inTraversal(node.getLeftchild(),list);
	    list.add(node.getKey());
	    inTraversal(node.getRightchild(),list);
	  }

  @Override
  public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
    // TODO Auto-generated method stub
	  
	  List<BinaryTreeNode<Key, Value>> list = new ArrayList<>();
	  ArrayList<Key> arrayList = new ArrayList<>();
	  inTraversal(root,arrayList);
	  
	  for (Key key : arrayList) {
		  if(getNode(key) != null){
			  list.add(getNode(key));
		  }
	  }
	  
	  return list;

  }

  @Override
  public void setComparator(Comparator<Key> C) {
    // TODO Auto-generated method stub
	  
	  comparator = C;
    
  }

  @Override
  public Comparator<Key> getComparator() {
    // TODO Auto-generated method stub
    return comparator;
  }

  @Override
  public BinaryTreeNode<Key, Value> ceiling(Key k) {
    // TODO Auto-generated method stub
	    if(k == null || isEmpty()) {
	        return null;
	    }
	    BinaryTreeNode<Key,Value> node = treeSearch(k,root);
	    
	    
	    if(isInternal(node)) {
	        return node;
	    }
	    BinaryTreeNode<Key,Value> parent = node.getParent();
	    
	    while(parent != null && isRightChild(node)){
	        node = parent;
	        parent = node.getParent();
	    }
	    return parent;

  }

  @Override
  public BinaryTreeNode<Key, Value> floor(Key k) {
    // TODO Auto-generated method stub
	  
	  //Case1
	  if(k == null || isEmpty()) {
	      return null;
	  }
	  
	  //Case2
	  BinaryTreeNode<Key,Value> node = treeSearch(k,root);
	  if(isInternal(node)) {
	      return node;
	  }
	  
	  //Case3
	  BinaryTreeNode<Key,Value> parent = node.getParent();
	  while(parent != null && isLeftChild(node)){
		  node = parent;
	      parent = node.getParent();
	  }
	  
	  return parent;
    
  }
  
  
  public BinaryTreeNode<Key, Value> findNodeByValue(Value v){

	    for(BinaryTreeNode<Key, Value> node : getNodesInOrder()){
	    	if(node.getValue().equals(v) ){
	    		return node;
	    	}
	    }
	    return null;
  }
  
  
  
  
}
