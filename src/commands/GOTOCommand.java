package commands;

import java.awt.Point;

import frontEnd.Turtle;

public class GOTOCommand extends TurtleCommand{

	protected double myNewX;
	protected double myNewY;
	
	public GOTOCommand(String string, double newX, double newY, Turtle turtle){
		super(string, turtle);
		myNewX = newX;
		myNewY = newY;
	}
	
	protected double computeEuclidean(double x1, double y1, double x2, double y2){
		return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) );
	}

	@Override
	public double execute() {
//		Point destination = new Point();
//		destination.setLocation(myNewX, myNewY);
//		turtle.setTarget(destination);
		return 1;
	}
}
