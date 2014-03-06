package commands;

import TurtleStuff.Turtle;

public class ShowingP extends NonParameterTurtleCommand {

	public ShowingP() { }

	@Override
	protected double executeTurtle(Turtle turtle) {
		if( turtle.isSuspended() ) return 0;
		return 1;
	}

	@Override
	public String getCommandType() {
		return "SHOWINGP";
	}

}
