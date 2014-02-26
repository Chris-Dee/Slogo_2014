package commands;

public abstract class AbstractCommand {
	
	protected String myString;
	
	public AbstractCommand(String string){
		myString = string;
	}
	
	public abstract double execute();
	
	public String getCommandType(){
		return myString;
	}
}
