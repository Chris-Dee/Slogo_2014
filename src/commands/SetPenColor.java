package commands;

import TurtleStuff.Turtle;

public class SetPenColor extends OneParameterTurtleCommand{

	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.setPenColor(myMagnitude);
		return myMagnitude;
	}

}
