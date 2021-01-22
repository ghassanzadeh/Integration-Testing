package modules;

import java.util.ArrayList;
import data.Entry;

public class ModuleC {
	
	ModuleF f;
	
	public ModuleC(ModuleF f){
		this.f = f;
	}

	
	public ArrayList<Entry> sortData(ArrayList<Entry> data) {
		int increment = data.size() / 2;
		while (increment > 0) {
			for (int i = increment; i < data.size(); i++) {
				int j = i;
				Entry temp = data.get(i);
				while (j > increment && data.get(j - increment).compareTo(temp) > 0) {
					data.set(j, data.get(j-increment));
					j = j - increment;
				}
				data.set(j, temp);
			}
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			}
		}
		f.displayData(data);
		return data;
	}
	
	
	public void setF(ModuleF f){
		this.f = f;
	}

}
