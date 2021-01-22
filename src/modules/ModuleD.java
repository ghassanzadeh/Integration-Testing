package modules;

import java.util.ArrayList;

import data.Entry;

public class ModuleD {
	
	private ModuleF f;
	private ModuleG g;
	
	public ModuleD(ModuleF f, ModuleG g) {
		this.f = f;
		this.g = g;
	}

	
	public ArrayList<Entry> insertData(ArrayList<Entry> data, String name, String number, String filename) {
		data.add(new Entry(name, number));
		f.displayData(data);
		g.updateData(filename, data);
		return data;
	}

	
	public ArrayList<Entry> updateData(ArrayList<Entry> data, int index,
			String name, String number, String filename) {
		data.set(index+1, new Entry(name, number));
		f.displayData(data);
		g.updateData(filename, data);
		return data;
	}

	
	public ArrayList<Entry> deleteData(ArrayList<Entry> data, int index,
			String filename) {
		data.remove(index);
		f.displayData(data);
		g.updateData(filename, data);
		return data;
	}
	
	
	public void setF(ModuleF f){
		this.f = f;
	}
	

	public void setG(ModuleG g){
		this.g = g;
	}

}
