package commands;

import TurtleStuff.Turtle;

/*
 * This command is used to get a turtle's X from the center of the screen
 */
public class Xcor extends NonParameterTurtleCommand {

	public Xcor() { }
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.getStats().getPos().xPos();
	}
}
