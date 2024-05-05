package com.ubolib.CSV;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class CSVIterator implements Iterator<CSVLine>{
	private CSV csv;
	private int index;
	
	public CSVIterator(CSV csv) {
		this.csv=csv;
		this.index=0;
	}
	
	@Override
	public boolean hasNext() {
		if(index < csv.getLength()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public CSVLine next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		CSVLine line = csv.get(index);
		index++;
		return line;
	}
}
