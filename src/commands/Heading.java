package commands;

import TurtleStuff.Turtle;

public class Heading extends NonParameterTurtleCommand {

	public Heading() { }
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.getStats().getRot();
	}
}
