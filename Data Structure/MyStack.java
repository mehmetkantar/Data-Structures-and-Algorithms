package ds;

public class MyStack {
	private Node top;
	private int size = 0;

	// Adds a new element to the top of the stack
	public void push(String data) {
		// TODO
		Node pusher = new Node(data);
        if (top == null) {
            top = pusher;
        } else {
            pusher.setNext(top);
            top=pusher;
            
        }
        size++;
	}

	// Removes and returns the top element of the stack
	public String pop() {
		// TODO
		//checkIndex(size);
		Node popper = top;
		top=top.getNext();
		
		size --;
		return popper.getData();
	}

	// Returns the top element of the stack without removing it
	public String peek() {
		// TODO
		//checkIndex(size);
		return top.getData();
	}

	// Checks if the stack is empty
	public boolean isEmpty() {
		// TODO		
		if(size==0) {
			return true;
		}
		return false;
	}

	// Returns the number of elements in the stack
	public int size() {
		// TODO
		return size;
	}

	// Checks if the index is in the range of the stack size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
