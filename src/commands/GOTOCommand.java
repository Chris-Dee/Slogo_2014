package commands;

import java.util.List;

import frontEnd.Turtle;

public class GOTOCommand extends ICommand{

	protected double myNewX;
	protected double myNewY;
	
	public GOTOCommand(String string, double newX, double newY, List<Turtle> turtles){
		super(string, turtles);
		myNewX = newX;
		myNewY = newY;
	}

	@Override
	public double execute() {
		double distance = 0;
		for(Turtle current: myTurtles){
			distance = distance + moveTurtle(current);
		}
		return distance;
	}
	
	// need revision
	protected double moveTurtle(Turtle turtle){
		return 1;
	}
}
