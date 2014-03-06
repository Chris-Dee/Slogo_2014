package commands;

import TurtleStuff.Turtle;

public class HideTurtle extends NonParameterTurtleCommand{
	
	public HideTurtle(){} 
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.suspend();
		return 0;
	}
}
