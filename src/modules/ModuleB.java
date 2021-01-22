package modules;

import data.Entry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleB {
	
	private ModuleF f;
	
	public ModuleB(ModuleF f){
		this.f = f;
	}
	
	public ArrayList<Entry> loadFile(String filename)
	{
		ArrayList<Entry> data = new ArrayList<Entry>();
		try {

			String line;
			BufferedReader br = new BufferedReader(new FileReader(
					filename));

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 2)
					data.add(new Entry(values[0], values[1]));
			}
			br.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found!");
			return null;
		}
		catch (IOException e) {
			System.out.println("IO Error!");
			e.printStackTrace();
			return null;
		}
		
		f.displayData(data);
		return data;
	}
	
	public void setF(ModuleF f){
		this.f = f;
	}

}
