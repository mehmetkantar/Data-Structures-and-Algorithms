package code;

import java.util.Arrays;

import given.AbstractArraySort;

/*
 * Implement the c algorithm here. You can look at the slides for the pseudo-codes.
 * You do not have to use swap or compare from the AbstractArraySort here
 * 
 * You may need to cast any K to Integer
 * 
 */

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  //Add any fields here
  
  public CountingSort()
  {
    name = "Countingsort";
  }
  
  @Override
  public void sort(K[] inputArray) {
    
    if(inputArray == null)
    {
      System.out.println("Null array");
      return;
    }
    if(inputArray.length < 1)
    {
      System.out.println("Empty array");
      return;
    }   
    
    if(!(inputArray[0] instanceof Integer)) {
      System.out.println("Can only sort integers!");
      return;
    }
    
    //TODO:: Implement the counting-sort algorithm here
    

    
    //in order to save memory firstly look range for new database
    int min=(int)inputArray[0];
    int max=(int)inputArray[0];
	for (K element: inputArray) {
	    if(min> (int)element) {
	    	min= (int)element;
	    }
	   	if(max< (int)element) {
	   		max= (int)element;
	   	}
	    	
	}
	
	int length=max-min+1;
    Integer countingArray[] = new Integer[length] ;
    Arrays.fill(countingArray, 0);
    
    for (K element: inputArray) {
    	int index=(int)element-min;
    	countingArray[index]++;
    }
    
    int sortedCounter=0;
    for(int i=0;i<length;i++) {
    	//Looking a number whether inside of the Array
    	if (countingArray[i]>0 ) {
    		
    		//Repetitive scenerios
    		for (int j=0;j<countingArray[i];j++) {
    			inputArray[sortedCounter++]=(K)(Integer)(i+min);//+min gives us a chance to sorting also negative integers
    		}
    	}
    }
    
    
    
  }
  


}
