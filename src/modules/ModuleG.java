package modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.Entry;


public class ModuleG {
	

	public void updateData(String openFile, ArrayList<Entry> data) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(new File(
					openFile)));
			for (int i = 0; i < data.size(); i++)
				bf.write(data.get(i).getName() + "," + data.get(i).getNumber()
						+ "\n");
			bf.close();
		} catch (IOException e) {
			System.out.println("Error updating DB File.");
			e.printStackTrace();
		}
	}
}
