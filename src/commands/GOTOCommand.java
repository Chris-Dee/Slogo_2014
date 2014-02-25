package commands;

import java.util.List;

import frontEnd.Turtle;

public class GOTOCommand implements ICommand{

	protected String myString;
	protected double myNewX;
	protected double myNewY;
	protected List<Turtle> myTurtles;
	
	public GOTOCommand(String string, double newX, double newY, List<Turtle> turtles){
		myString = string;
		myNewX = newX;
		myNewY = newY;
		myTurtles = turtles;
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

	@Override
	public String getCommandType() {
		return myString;
	}

}
