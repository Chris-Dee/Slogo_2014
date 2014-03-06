package commands;

import TurtleStuff.Turtle;
import frontEnd.Position;

public class SetXY extends TwoParameterTurtleCommand{
	
	public SetXY(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.setTarget(new Position(myExpression1, myExpression2));
	}
}
