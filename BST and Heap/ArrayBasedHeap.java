package code;

import java.util.ArrayList;
import java.util.Comparator;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement an array based heap
 * Note that you can just use Entry here!
 * 
 */

public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {
  
  // Use this arraylist to store the nodes of the heap. 
  // This is required for the autograder. 
  // It makes your implementation more verbose (e.g. nodes[i] vs nodes.get(i)) but then you do not have to deal with dynamic resizing
  protected ArrayList<Entry<Key,Value>> nodes;
  
  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */
  Comparator<Key> comparator;
  
  //constructor
  public ArrayBasedHeap() {
	  nodes = new ArrayList<>();
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
	  return nodes.size();
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
	  boolean flag = (nodes.size()==0);
	  return flag;
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
  
  public void downHeap(int i ){
	  
	  if (isEmpty()){
		  return;
	  }
	  
	  while (hasLeftChild(i)){
		  int smallestChild = leftChildOf(i);
		  
		  if (hasRightChild(i)){
			  int rightchild = rightChildOf(i);
			  
			  if(comparator.compare(nodes.get(rightchild).getKey(),nodes.get(smallestChild).getKey())<0){
				  smallestChild = rightchild;
			  }
		  }
		  
		  if(comparator.compare(nodes.get(smallestChild).getKey(),nodes.get(i).getKey())>=0){
			  break;
		  }
		  
		  
		  swap(i,smallestChild);
		  i = smallestChild;
	  }

  }
  public void upHeap(int i){
	  
	  
	  while(i>0){
		  
		  int parent = parentOf(i);
		  
		  if(comparator.compare(nodes.get(i).getKey() , nodes.get(parent).getKey())>=0) {
			  break;
		  }
		  
		  swap(i,parent);
		  i=parent;
	  }

  }

  @Override
  public void insert(Key k, Value v) {
    // TODO Auto-generated method stub
	  
	  Entry<Key,Value> insertion = new Entry<Key,Value>(k,v);
	  nodes.add(insertion);
	  upHeap(size()-1);
    
  }

  @Override
  public Entry<Key, Value> pop() {
    // TODO Auto-generated method stub
	  
	 if(isEmpty()){
	    return null;
	 }
	 
	 Entry<Key, Value> popped = nodes.get(0);
	 nodes.set(0,nodes.get(size()-1));
	 nodes.remove(size()-1);
	 downHeap(0);
	 
	 return popped;
  }

  @Override
  public Entry<Key, Value> top() {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()){
	      return null;
	  }
	  
	  return nodes.get(0);
  }

  @Override
  public Value remove(Key k) {
    // TODO Auto-generated method stub
	  
	  if(isEmpty()) {
		  return null;
	  }
	  
	  Entry<Key, Value> removed = null;
	  int index = -1;
	  for(int i = 0; i < size(); i++) {
		  if(comparator.compare(nodes.get(i).getKey(),k)==0) {
			  index = i;
			  swap(i, size()-1);
			  removed = nodes.get(size()-1);
			  break;
	      }
	  }
	  
	  if(index == -1) {
	      return null;
	  }
	  
	  nodes.remove(size()-1);
	  for(int j = parentOf(size()-1); j >= 0; j--) {
	      downHeap(j);
	  }

	  return removed.getValue();
	  
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
    // TODO Auto-generated method stub
	  
	  Key key = null;
	  int index = -1;
	  
	  
	  for(int i = 0; i < size(); i++) {
		  if(entry.equals(nodes.get(i))) {
			  index = i;
			  key = entry.getKey();
			  nodes.get(i).setKey(k);
			  break;
		  }
	  }
	  
	  
	  if(index == -1) {
		  return null;
	  }
	  
	  
	  for(int j = parentOf(size()-1); j >= 0; j--) {
		  downHeap(j);
	  }
		
	  return key;
  }

  @Override
  public Key replaceKey(Value v, Key k) {
    // TODO Auto-generated method stub
	  Key replace = null;
	  int index = -1;

	  for(int i = 0; i < size(); i++) {
		  if(nodes.get(i).getValue().equals(v)) {
			  index = i;
			  replace = nodes.get(i).getKey();
			  nodes.get(i).setKey(k);
			  break;
	      }

	  }

	  if(index == -1) {
	      return null;
	  }

	  for(int j = parentOf(size()-1); j >= 0; j--) {
	      downHeap(j);
	  }

	  return replace;

  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
    // TODO Auto-generated method stub
	  
	  for(int i = 0 ; i<size();i++){
		  if(entry.getKey().equals(nodes.get(i).getKey())){ 
			  Value ret = nodes.get(i).getValue();
			  nodes.get(i).setValue(v);
			  return ret;
	      }
	  }
	  
	  return null;
  }
  
  
  public void swap(int i, int j){
	  
	  Entry<Key, Value> temp = nodes.get(i);
	  nodes.set(i, nodes.get(j));
	  nodes.set(j, temp);
	  
  }
  
  
  public int parentOf(int i){

	  return (i-1)/2;
  }
  
  
  public int leftChildOf(int i){

	  return 2*i+1;  
  }
  
  
  public int rightChildOf(int i){

	  return 2*i+2;
  }
  
  
  public boolean hasLeftChild(int i){

	  return leftChildOf(i) < size() ;
  }
  
  
  public boolean hasRightChild(int i){

	  return rightChildOf(i) < size();
  }
  
  
  public void heapify(){
	  
	  int initial = parentOf(size()-1);
	  for( int i = initial ; i >= 0 ; i-- ){
		  downHeap(i);
	  }
	    
  }
  
}
