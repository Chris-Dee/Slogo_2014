package commands;

import frontEnd.Turtle;

public class CORQuery extends TurtleCommand {
	
	protected boolean queryX;

	public CORQuery(String string, Turtle turtle) {
		super(string, turtle);
		if(myString.startsWith("X")){ queryX = true; }
		else{ queryX = false; }
	}

	@Override
	public double execute() {
		if(queryX){
			// need revision
			return myTurtle.getPosx();	
		}
		// need revision
		return myTurtle.getPosy();
	}
}
