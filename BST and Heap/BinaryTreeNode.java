package code;

import given.Entry;

/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {
  
  /*
   * 
   * YOUR CODE HERE
   * 
   */
	
	private BinaryTreeNode<Key, Value>parent =null ;
	private BinaryTreeNode<Key, Value> leftchild = null ;
	private BinaryTreeNode<Key, Value> rightchild = null;
  public BinaryTreeNode(Key k, Value v) {
    super(k, v);
    
    /*
     * 
     * This constructor is needed for the autograder. You can fill the rest to your liking.
     * YOUR CODE AFTER THIS:
     * 
     */
    
    if(!this.isEmptyNode()){
        putDummy();
    }
  }
  
  
  public boolean isEmptyNode() {
	    return k == null && v == null;
  }
  

  public BinaryTreeNode<Key, Value> getParent() {
	    return parent;
  }

  
  public void setParent(BinaryTreeNode<Key, Value> parent) {
	    this.parent = parent;
  }

  
  public BinaryTreeNode<Key, Value> getLeftchild() {
	    return leftchild;

  }
  
  
  public void setLeftchild(BinaryTreeNode<Key, Value> leftchild) {
	    leftchild.setParent(this);
	    this.leftchild = leftchild;

  }

  
  public BinaryTreeNode<Key, Value> getRightchild() {
	    return rightchild;
  }
  
  
  public void setRightchild(BinaryTreeNode<Key, Value> rightchild) {
	    rightchild.setParent(this);
	    this.rightchild = rightchild;

  }
  
  
  public boolean hasRightChild(){
	    return !rightchild.isEmptyNode();
  }
  
  
  public boolean hasLeftChild(){
	    return !leftchild.isEmptyNode();
  }
  
  
  public void putDummy(){
	    this.setLeftchild( new BinaryTreeNode<>(null,null));
	    this.setRightchild( new BinaryTreeNode<>(null, null));
  }
  
  
  public void setToDummy(){
	    this.setKey(null);
	    this.setValue(null);
  }

}
