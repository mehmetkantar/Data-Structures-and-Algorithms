package code;

import given.AbstractArraySort;

public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  public InsertionSort()
  {
    name = "Insertionsort";
  }
  
  @Override
  public void sort(K[] inputArray) 
  {   
    //TODO: Implement the insertion sort algorithm
     
	  int length=inputArray.length;
	  for (int i=1; i<length;i++) {
		  
		  K element = inputArray[i];
		  int j = i-1;
		  while (j>=0 && compare( inputArray[j], element)>0 ) {//if element is less than previous swap it
			  swap(inputArray, j, j+1);
			  j=j-1;
		  }
		  inputArray[j + 1] = element;
		  
	  }
	  
  }

}
