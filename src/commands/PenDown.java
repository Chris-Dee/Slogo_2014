package commands;

import TurtleStuff.Turtle;

public class PenDown extends NonParameterTurtleCommand{
	
	public PenDown(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.lowerPen();
		return 1;
	}
}
