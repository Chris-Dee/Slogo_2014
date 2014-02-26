package commands;

import frontEnd.Turtle;

public class STHTCommand extends TurtleCommand{
	
	protected boolean showTurtle;
	
	public STHTCommand(String string, Turtle turtle){
		super(string, turtle);
		if(myString.startsWith("S")){ showTurtle = true; }
		else{ showTurtle = false; }
	}

	@Override
	public double execute() {
		if(showTurtle){
			// need revision
			return 1;
		}
		// revision
		return 0;
	}
}
