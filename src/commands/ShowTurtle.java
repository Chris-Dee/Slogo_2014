package commands;

import TurtleStuff.Turtle;

public class ShowTurtle extends NonParameterTurtleCommand{
	
	public ShowTurtle(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.resume();
		return 1;
	}
}
