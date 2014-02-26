package commands;

import java.awt.Point;

import frontEnd.Turtle;

public class HomeCommand extends TurtleCommand {
	
	public static final int HOME_X = 0;
	public static final int HOME_Y = 0;
	
	public HomeCommand(String string, Turtle turtle){
		super(string, turtle);
	}

	@Override
	public double execute() {
		// need revision
		double distance = 0;
		myTurtle.setTarget(new Point(HOME_X, HOME_Y));
		return distance;
	}
}
