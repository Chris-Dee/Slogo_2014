package commands;

import frontEnd.Turtle;

public class FDBKCommand extends TurtleCommand {
	
	protected double forwardSign;
	
	public FDBKCommand(String string, double pixels, Turtle turtle) {
		super(string, pixels, turtle);
		if(myString.startsWith("F")){ forwardSign = 1.0; }
		else { forwardSign = -1.0; }
	}

	@Override
	public double execute() {
		double distance = forwardSign * myMagnitude;
		myTurtle.goForward(distance);
		return distance;
	}
}