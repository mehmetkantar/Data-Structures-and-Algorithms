package code;

import given.AbstractArraySort;

/*
 * Implement the heap-sort algorithm here. You can look at the slides for the pseudo-code.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */

public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Add any fields here

  public HeapSort() {
    name = "Heapsort";

    // Initialize anything else here
  }

  @Override
  public void sort(K[] inputArray) {
    // TODO: Implement the heap-sort algorithm
	  int n = inputArray.length;

      // build heap 
      heapify(inputArray);

      // extract an element in the heap
      for (int i = n - 1; i >= 0; i--) {
          // swap current root to end
    	  swap(inputArray, i, 0);
          // downheap on the reduced heap
          downheap(inputArray, i, 0);
      }

  }

  // Public since we are going to check its output!
  public void heapify(K[] inputArray) {
    // TODO: Heapify the array. See the slides for an O(n) version which uses
    // downheap
	  
	  int n = inputArray.length;
      // start from the last nonleaf node and go up
      for (int i = n / 2 - 1; i >= 0; i--) {
          downheap(inputArray,n, i);
      }

  }
  
  // The below methods are given given as suggestion. You do not need to use them.
  // Feel free to add more methods

  protected void downheap(K[] inputArray,int n, int i) {
    // TODO: Implement the downheap method to help with the algorithm

	  int largest = i; // temporary largest as root
      int left = 2 * i + 1; // left child index
      int right = 2 * i + 2; // right child index

      // if left child is larger than root
      if (left < n && compare(inputArray[left], inputArray[largest]) > 0) {
          largest = left;
      }

      // if right child is larger than largest so far
      if (right < n && compare(inputArray[right], inputArray[largest]) > 0) {
          largest = right;
      }

      // if largest is not root
      if (largest != i) {
          swap(inputArray, i, largest);

          // recursively downheap the affected sub-tree
          downheap(inputArray, n, largest);
      }
  }
}
