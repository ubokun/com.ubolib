package com.ubolib.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class CSV implements Iterable<CSVLine>{
	
	private List<CSVLine> lines;
	
	public CSV() {
		
	}
	
	public void load(String filename) throws IOException,FileNotFoundException{
		try (BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsolutePath()))){
			String line;
			while((line=in.readLine())!=null) {
				lines.add(new CSVLine(line));
			}
		}
		
	}
	public boolean fileExist(String filename) {
		return Files.exists(Paths.get(new File(filename).getAbsolutePath()));
	}
	public void write(String filename) throws IOException,FileNotFoundException{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename).getAbsolutePath(),false)))){
			for(CSVLine line:lines) {
				String lineStr=String.join(",", line.getRawLine());
				out.printf("%s\n",lineStr);
			}
		}
	}
	public int getLength() {
		return lines.size();
	}
	
	public CSVLine get(int lineId) {
		return lines.get(lineId);
	}
	
	@Override
	public Iterator<CSVLine> iterator(){
		return new CSVIterator(this);
	}
	
}
