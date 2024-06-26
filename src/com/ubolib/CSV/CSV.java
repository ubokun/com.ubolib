package com.ubolib.CSV;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSV implements Iterable<CSVLine>{
	
	private List<CSVLine> lines;
	
	public CSV() {
		lines=new ArrayList<CSVLine>();
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
	
	public void appendWrite(String filename) throws IOException,FileNotFoundException{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename).getAbsolutePath(),true)))){
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
	
	public CSV appendLine(CSVLine line) {
		lines.add(line);
		return this;
	}
	
	@Override
	public Iterator<CSVLine> iterator(){
		return new CSVIterator(this);
	}
	
}
