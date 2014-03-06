package commands;

import TurtleStuff.Turtle;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Left extends OneParameterTurtleCommand {
	
	public Left() {}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.addRotation(myMagnitude);
		return myMagnitude;
	}
}
