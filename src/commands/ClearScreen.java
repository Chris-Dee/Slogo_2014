package commands;

import TurtleStuff.Turtle;

/*
 * This command erases turtle's trails and sends it to the home position.
 */
public class ClearScreen extends NonParameterTurtleCommand{
	
	public ClearScreen(){ }

	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.reset();
	}
}
