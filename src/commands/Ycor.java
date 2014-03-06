package commands;

import TurtleStuff.Turtle;

/*
 * This command is used to get a turtle's Y from the center of the screen
 */
public class Ycor extends NonParameterTurtleCommand {

	public Ycor() { }
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.getStats().getPos().yPos();
	}
}
