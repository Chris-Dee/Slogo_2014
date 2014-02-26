package commands;

import frontEnd.Turtle;

public class PENCommand extends TurtleCommand{
	
	protected boolean penDown;
	
	public PENCommand(String string, Turtle turtle){
		super(string, turtle);
		if(myString.endsWith("D")){ penDown = true; }
		else{ penDown = false; }
	}

	@Override
	public double execute() {
		if(penDown){
			myTurtle.lowerPen();
			return 1;
		}
		myTurtle.raisePen();
		return 0;
	}	
}
