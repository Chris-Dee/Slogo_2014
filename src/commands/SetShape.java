package commands;

import TurtleStuff.Turtle;

public class SetShape extends OneParameterTurtleCommand{

	@Override
	protected double executeTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		turtle.setShape(myMagnitude);
		return myMagnitude;
	}

}
