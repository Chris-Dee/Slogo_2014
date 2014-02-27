package commands;

import frontEnd.Turtle;
/*
 * This command erases turtle's trails and sends it to the home position.
 */
public class CSCommand extends TurtleCommand{
	
	public CSCommand(String string, Turtle turtle){
		super(string, turtle);
	}

	@Override
	/*
	 * @see commands.AbstractCommand#execute()
	 * return the distance turtle moved from sending it to the home position
	 */
	public double execute() {
		myTurtle.clearLines();
		HomeCommand homeCom = new HomeCommand("HOME", myTurtle);
		return homeCom.execute();
	}
}
