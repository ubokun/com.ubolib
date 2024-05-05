package com.ubolib.FileIO;

public class CSVLine {
	private String[] cells;
	public CSVLine(String line) {
		cells=line.split(",",-1);
	}
	public String get(int columnId) {
		return cells[columnId];
	}
	public CSVLine set(int columnId, String data) {
		cells[columnId]=data;
		return this;
	}
	public String getRawLine() {
		return String.join(",",cells);
	}
	
	
}
