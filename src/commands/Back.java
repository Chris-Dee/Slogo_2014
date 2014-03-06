package commands;

import TurtleStuff.Turtle;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Back extends OneParameterTurtleCommand {
	
	public Back() {}

	@Override
	public String getCommandType() {
		return "BK";
	}

	@Override
	protected double executeTurtle(Turtle turtle) {
		return -1.0*turtle.goForward(-1.0*myMagnitude);
	}
}
