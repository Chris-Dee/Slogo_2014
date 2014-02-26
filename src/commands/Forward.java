package commands;

import frontEnd.Turtle;
/*
 * This command makes the turtle move forward or backward by certain pixels
 */
public class Forward extends TurtleCommand {
	
	public Forward() {}

	@Override
	public double execute(Turtle turtle, double magnitude) {
		turtle.goForward(magnitude);
		return magnitude;
	}

	@Override
	public String getCommandType() {
		return "FD";
	}
}
