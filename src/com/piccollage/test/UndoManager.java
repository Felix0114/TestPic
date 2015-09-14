package com.piccollage.test;

import java.util.ArrayList;



public class UndoManager {
	
	
	
	/**
	 * 0         size-1
	 * last ->  oldest  
	 */
	//private ArrayList<Record> records=new ArrayList<Record>();
	private ArrayList<Integer> records=new ArrayList<Integer>();
	private int historyLimit;
	private int currentNumber;
	private int recordIndex=0;
		/**
	    * @param number the initial value 
	    * @param historySize the size of history the undo manager should be keep
	    */
	    public UndoManager(int number, int historySize) {
	    	currentNumber=number;
	    	historyLimit=historySize+1;
	    	addRecord(currentNumber);
	    }
	    public int getNumber() {
	        return currentNumber;
	    }
	    public void undo() {
	    	//check history limit
	    	if(recordIndex==historyLimit-1){
	    		//do nothing
	    	}else{
	    		recordIndex++;
	    	}
	    	currentNumber=records.get(recordIndex);
	    }
	    public void redo() {
	        //check history limit
	    	if(recordIndex==0){
	    		//do nothing
	    	}else{
	    		recordIndex--;
	    	}
	        
	        
	        currentNumber=records.get(recordIndex);
	        	
	       
	    }
	    public void add(int num) {
	    	
	    	
	    	currentNumber+=num;
	    	addRecord(currentNumber);
	    	recordIndex=0;
	    	
	    }
	    public void subtract(int num) {
	    	
	    	currentNumber-=num;
	    	addRecord(currentNumber);
	    	recordIndex=0;
	    }
	    public void multiply(int num) {
	    	
	    	currentNumber*=num;
	    	addRecord(currentNumber);
	    	recordIndex=0;
	    }
	    public void divide(int num) {
	    	
	    	currentNumber/=num;
	    	addRecord(currentNumber);
	    	recordIndex=0;
	    }
	    
	    private void addRecord(int old){
	    	records.add(0,new Integer(old));
	    	if(records.size()>historyLimit){
	    		records.remove(records.size()-1);
	    	}
	    }

	
}
