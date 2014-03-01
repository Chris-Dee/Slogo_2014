package commands;

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
		myTurtle.clearLines();
		Home homeCom = new Home();
		return homeCom.execute();
	}

	@Override
	public String getCommandType() {
		return "CS";
	}
}
