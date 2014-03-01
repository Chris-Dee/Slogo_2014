package commands;

/*
 * This command is used to get a turtle's X from the center of the screen
 */
public class Xcor extends TurtleCommand {

	public Xcor() { }

	@Override
	/*
	 * @see commands.AbstractCommand#execute()
	 * Return the turtle's X coordinate from the center of the screen
	 */
	public double execute() {
		return myTurtle.getStats().getPos().xPos();
	}

	@Override
	public String getCommandType() {
		return "XCOR";
	}
}
