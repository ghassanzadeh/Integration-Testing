import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import modules.ModuleA;
import modules.ModuleB;
import modules.ModuleC;
import modules.ModuleD;
import modules.ModuleE;
import modules.ModuleE.DataBaseExitException;
import modules.ModuleF;
import modules.ModuleG;


public class Main {
	
	public static void main(String[] args){
		
		
		ModuleE e = new ModuleE();
		ModuleF f = new ModuleF();
		ModuleG g = new ModuleG();
		
		ModuleB b = new ModuleB(f);
		ModuleC c = new ModuleC(f);
		ModuleD d = new ModuleD(f, g);
		
		ModuleA a = new ModuleA(b, c, d, e);
		
		System.out.println("Welcome to Simple Database!");
		System.out.println("type 'help' for command list");
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		String[] command;
		
		while(true){
			System.out.print("SimpleDB>>  ");
			try {
				s = bufferRead.readLine();
			} catch (IOException ex) {
				System.out.println("Error reading input! Exiting...");
				System.exit(-1);
			}
			s = s.trim();
			command = s.split(" ");
			try {
				a.run(command);
			} catch (DataBaseExitException e1) {
				System.exit(0);
			} catch (Exception e2){
				System.out.println("Unknown Error occured!");
			}
		}
	}

}
