package modules;

import java.io.PrintStream;
import java.util.ArrayList;

import data.Entry;

public class ModuleF {
	
	PrintStream out = System.out;

	
	public void displayData(ArrayList<Entry> data) {
		out.println("Current Data:");
		for(int i=1; i< data.size(); i++) {
			out.println((i+1)+" "+data.get(i).toString());
		}
	}
	
	public void setOutputStream(PrintStream p) {
		out = p;
	}
}
