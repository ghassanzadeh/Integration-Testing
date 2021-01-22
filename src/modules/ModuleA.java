package modules;

import java.io.PrintStream;
import java.util.ArrayList;

import data.Entry;
import modules.ModuleE.DataBaseExitException;

public class ModuleA  {
	
	ModuleB b;
	ModuleC c;
	ModuleD d;
	ModuleE e;
	
	ArrayList<Entry> data = null;
	String filename = null;
	
	PrintStream out = System.out;
	
	public ModuleA (ModuleB b, ModuleC c, ModuleD d, ModuleE e) {
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}

	public void run(String[] command) throws DataBaseExitException {
		
		
		if(command[0].equals("")){}
		else if(command[0].equals("help")){
			this.displayHelp();
		}
		else if(command[0].equals("load")) {
			try {
				this.parseLoad(command[1]);
			}
			catch (ArrayIndexOutOfBoundsException ex){
				out.println("Malformed command!");
			}
		}
		else if(command[0].equals("add")) {
			try {
				if(data != null)
					this.parseAdd(command[1], command[2]);
				else {
					out.println("No file loaded!");
				}
			}
			catch (ArrayIndexOutOfBoundsException ex){
				out.println("Malformed command!");
			}
		}
		else if(command[0].equals("sort")) {
			if(data != null)
				this.runSort();
			else {
				out.println("No file loaded!");
			}
		}
		else if(command[0].equals("update")) {
			try {
				if(data != null)
					this.parseUpdate(Integer.parseInt(command[1]), command[2], command[3]);
				else {
					out.println("No file loaded!");
				}
			}
			catch (ArrayIndexOutOfBoundsException ex){
				out.println("Malformed command!");
			}
		}
		else if(command[0].equals("delete")) {
			try {
				if(data != null)
					this.parseDelete(Integer.parseInt(command[1]));
				else {
					out.println("No file loaded!");
				}
			}
			catch (ArrayIndexOutOfBoundsException ex){
				out.println("Malformed command!");
			}
		}
		else if(command[0].equals("exit")) {
			this.runExit();
		}
		else {
			out.println("Unknown command, type 'help' for command list.");
			return;
		}
		
	}
	
	private boolean parseDelete(int index) {
		this.data =  d.deleteData(this.data, index-1, this.filename);
		if(this.data != null)
			return true;
		return false;
	}

	private boolean displayHelp() {
		String help = "Available Commands: \n" +
					"load <filepath>\n" +
					"add <name> <number>\n" +
					"update <index> <name> <number>\n" +
					"delete <index>\n" +
					"sort\n" +
					"exit";
		out.println(help);
		return true;
	}
	
	private boolean parseLoad(String filename){
		this.filename = filename;
		this.data = b.loadFile(filename);
		if(data != null)
			return true;
		return false;
	}
	
	private boolean parseAdd(String name, String number) {
		this.data =  d.insertData(this.data, name, number, this.filename);
		if(this.data != null)
			return true;
		return false;
	}
	
	private boolean runSort(){
		this.data = c.sortData(this.data);
		if(this.data != null)
			return true;
		return false;
	}
	
	private boolean parseUpdate(int index, String name, String number) {
		this.data = d.updateData(this.data, index-2, name, number, this.filename);
		if(this.data != null)
			return true;
		return false;
	}
	
	private void runExit() throws DataBaseExitException{
		ModuleE e = new ModuleE();
		e.exitProgram();
	}

	public void setOutputStream(PrintStream p) {
		out = p;
	}
	
}
