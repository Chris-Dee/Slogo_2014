package commands;

import TurtleStuff.Turtle;

public class PenDownP extends NonParameterTurtleCommand{

	public PenDownP() { }
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		if(turtle.getStats().penBool()) { return 1; }
		return 0;
	}
}
