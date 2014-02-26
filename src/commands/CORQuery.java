package commands;

import frontEnd.Turtle;
/*
 * Depending on the string of command passed into the constructor,
 * this command is used to get a turtle's X or Y coordinate from the center of the screen
 */
public class CORQuery extends TurtleCommand {
	
	protected boolean queryX;

	public CORQuery(String string, Turtle turtle) {
		super(string, turtle);
		if(myString.startsWith("X")){ queryX = true; }
		else{ queryX = false; }
	}

	@Override
	/*
	 * @see commands.AbstractCommand#execute()
	 * Return the turtle's X or Y coordinate from the center of the screen
	 */
	public double execute() {
		if(queryX){
			//return myTurtle.;	
		}
		//return myTurtle.getPosy();
	}
}
