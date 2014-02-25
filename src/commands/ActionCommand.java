package commands;

import java.util.List;
import frontEnd.Turtle;

public abstract class ActionCommand implements ICommand{

	protected String myString;
	protected double myMagnitude;
	protected List<Turtle> myTurtles;
	
	public ActionCommand(String string, double magnitude, List<Turtle> turtles){
		myString = string;
		myMagnitude = magnitude;
		myTurtles = turtles;
	}

	@Override
	public double execute() {
		for(Turtle current: myTurtles){
			moveTurtle(current);
		}
		return myMagnitude;
	}

	protected abstract void moveTurtle(Turtle turtle);

	@Override
	public String getCommandType() {
		return myString;
	}
	
}
