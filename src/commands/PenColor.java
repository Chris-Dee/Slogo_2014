package commands;

import TurtleStuff.Turtle;

public class PenColor extends NonParameterTurtleCommand{

	@Override
	protected double executeTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		return turtle.getPenIndex();
	}

}
