package commands;

import TurtleStuff.Turtle;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Forward extends OneParameterTurtleCommand {
	
	public Forward() {}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.goForward(myMagnitude);
	}
}
