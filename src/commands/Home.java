package commands;

import TurtleStuff.Turtle;
import frontEnd.Position;

public class Home extends NonParameterTurtleCommand {
	
	public static final int HOME_X = 0;
	public static final int HOME_Y = 0;
	
	public Home(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.setTarget(new Position(Turtle.TURTLE_INIT_X, Turtle.TURTLE_INIT_Y));
	}
}
