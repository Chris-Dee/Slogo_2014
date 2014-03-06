package commands;

import TurtleStuff.Turtle;

public class PenUp extends NonParameterTurtleCommand{
	
	public PenUp(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.raisePen();
		return 0;
	}

	@Override
	public String getCommandType() {
		return "PU";
	}	
}
