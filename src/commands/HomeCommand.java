package commands;

import java.awt.Point;

import frontEnd.Turtle;

public class Home extends TurtleCommand {
	
	public static final int HOME_X = 0;
	public static final int HOME_Y = 0;
	
	public Home(){
	}

	@Override
	public double execute() {
		// need revision
		double distance = 0;
		myTurtle.setTarget(new Point(HOME_X, HOME_Y));
		return distance;
	}
}
