package commands;

import frontEnd.Turtle;

public class LTRTCommand extends TurtleCommand {

	protected boolean turnLeft;
	
	public LTRTCommand(String string, double degrees, Turtle turtle) {
		super(string, degrees, turtle);
		if(myString.startsWith("L")){ turnLeft = true; }
		turnLeft = false;
	}

	@Override
	public double execute() {
		//myTurtle.
		return myMagnitude;
	}
}
