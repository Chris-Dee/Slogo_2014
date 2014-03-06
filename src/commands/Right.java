package commands;

import TurtleStuff.Turtle;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Right extends OneParameterTurtleCommand {
	
	public Right() {}

	@Override
	public String getCommandType() {
		return "RT";
	}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.addRotation(-1.0*myMagnitude);
		return myMagnitude;
	}
}
