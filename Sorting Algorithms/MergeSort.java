package code;

import given.AbstractArraySort;

/*
 * Implement the merge-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 * You may need to create an Array of K (Hint: the auxiliary array). 
 * Look at the previous assignments on how we did this!
 * 
 */

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Add any fields here

  public MergeSort() {
    name = "Mergesort";

    // Initialize anything else here
  }

  @Override
  public void sort(K[] inputArray) {
    // TODO: Implement the merge-sort algorithm
	  mergeSort(inputArray, 0, inputArray.length -1);

  }

  // Public since we are going to check its output!
  public void merge(K[] inputArray, int lo, int mid, int hi) {
    // TODO: Implement the merging algorithm
	// Find sizes of two subarrays to be merged
	  int n1 = mid - lo + 1;
      int n2 = hi - mid;

      // Create temp arrays
      K[] L = (K[]) new Comparable[n1];
      K[] R = (K[]) new Comparable[n2];

      // Copy data to temp arrays
      for (int i = 0; i < n1; i++)
          L[i] = inputArray[lo + i];
      for (int i = 0; i < n2; i++)
          R[i] = inputArray[mid + 1 + i];

      // Merge the temp arrays
      int i = 0, j = 0;
      int k = lo;
      while (i < n1 && j < n2) {
          if (compare(L[i], R[j]) <= 0) {
        	  inputArray[k] = L[i];
              i++;
          } else {
        	  inputArray[k] = R[j];
              j++;
          }
          k++;
      }

      // Copy remaining elements of L[] and R[] if any
      while (i < n1) {
    	  inputArray[k] = L[i];
          i++;
          k++;
      }
      while (j < n2) {
    	  inputArray[k] = R[j];
          j++;
          k++;
      }
  }
  
  // Feel free to add more methods
  
  private void mergeSort(K[] array, int left, int right) {
      if (left < right) {
          // Find the middle point
          int mid = left + (right - left) / 2;

          // Sort first and second halfs
          mergeSort(array, left, mid);
          mergeSort(array, mid + 1, right);

          // Merge the sorted halfs
          merge(array, left, mid, right);
      }
  }
}
