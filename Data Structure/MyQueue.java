package ds;

public class MyQueue {
	
    private Node head;
    private Node tail;
    private int size = 0;

    // Adds a new element to the end of the queue
    public void enqueue(String data) {
    	//TODO 
    	Node que = new Node(data);
        if (isEmpty()) {
            head = que;
            tail = que;
        } else {
            tail.setNext(que);
            tail=que;
        }
        size++;
    }

    // Removes and returns the element at the front of the queue
    public String dequeue() {
    	//TODO 
    	Node deque = head;
    	if (isEmpty()) {
    		throw new IndexOutOfBoundsException("You do not have enough element in the queue ");
        } else if(size==1) {
            head=null;
            tail=null;
            
        }else {
            head=head.getNext();
            
        }
    	size--;
    	return deque.getData();
    }

    // Returns the element at the front of the queue without removing it
    public String peek() {
    	//TODO 
    	if (isEmpty()) {
    		throw new IndexOutOfBoundsException("You do not have enough element in the queue ");
        }
    	return head.getData();
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
    	//TODO
    	if(size==0) {
			return true;
		}
        return false;
    }

    // Returns the number of elements in the queue
    public int size() {
    	//TODO 
        return size;
    }
    
    // Checks if the index is in the range of the queue size.
    // You can use this method while implementing the other ones.
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
