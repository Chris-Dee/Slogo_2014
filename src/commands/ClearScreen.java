package commands;

import frontEnd.Position;
import frontEnd.Turtle;

/*
 * This command erases turtle's trails and sends it to the home position.
 */
public class ClearScreen extends TurtleCommand{
	
	public ClearScreen(){ }

	@Override
	/*
	 * @see commands.AbstractCommand#execute()
	 * return the distance turtle moved from sending it to the home position
	 */
	public double execute() {
		double distance=myTurtle.reset();

		return distance;
	}

	@Override
	public String getCommandType() {
		return "CS";
	}
}
