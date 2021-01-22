package data;

public class Entry implements Comparable<Entry>{
	
	private String name;
	private String number;
	
	public Entry(String n1, String n2)
	{
		this.setName(n1);
		this.setNumber(n2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int compareTo(Entry arg0) {
		return this.getName().compareTo(arg0.getName());
	}
	
	public String toString() {
		return this.name + ", " + this.number;
	}

}
