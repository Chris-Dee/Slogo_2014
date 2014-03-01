package commands;

/*
 * This command is used to get a turtle's Y from the center of the screen
 */
public class Ycor extends TurtleCommand {

	public Ycor() { }

	@Override
	/*
	 * @see commands.AbstractCommand#execute()
	 * Return the turtle's Y coordinate from the center of the screen
	 */
	public double execute() {
		return myTurtle.getStats().getPos().yPos();
	}

	@Override
	public String getCommandType() {
		return "YCOR";
	}
}
