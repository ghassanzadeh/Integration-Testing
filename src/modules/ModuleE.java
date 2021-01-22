package modules;


public class ModuleE {

	
	public void exitProgram() throws DataBaseExitException {
		throw new DataBaseExitException();
	}
	
	public class DataBaseExitException extends Exception {
		private static final long serialVersionUID = -8327268493958757259L;
	}

}
