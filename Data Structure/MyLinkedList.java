package ds;

public class MyLinkedList {
	private Node head;
	private int size = 0;

	public MyLinkedList() {
		this.head = null;
	}

	// Adds a new node with the specified data at the end of the list
	public void add(String data) {
		// TODO
		Node adder = new Node(data);
        if (head == null) {
            head = adder;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(adder);
        }
        size++;
		
	}

	// Inserts a new node with the specified data at the given index
	public void insert(int index, String data) {
		// TODO
		checkIndex(index);
		Node inserter = new Node(data);


        if (index == 0) {
            inserter.setNext(head); 
            head = inserter;
            
        }else {

        Node current = head;


        for (int i = 0; i < index - 1; i++) {
            current = current.next;

        }
        
        inserter.setNext(current.getNext());
        current.setNext(inserter);

        }
        size++;

	}

	// Removes the node at the specified index
	public void remove(int index) {
		// TODO
		checkIndex(index);
		Node remover = head;
		if (index==0) {
			head=remover.getNext();
			
		}else {
			
			for(int i=0;i<index-1;i++) {
				remover=remover.getNext();
			}
			remover.setNext(remover.getNext().getNext());
		}
		size --;
		
	}

	// Returns the data of the node at the specified index
	public String get(int index) {
		// TODO
		checkIndex(index);
		Node getter = head;
		for(int i=0;i<index;i++) {
			getter=getter.getNext();
		}
		return getter.getData();
	}

	// Returns the number of elements in the list
	public int size() {
		// TODO
		return size;
	}

	// Checks if the index is in the range of the list size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
