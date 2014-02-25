package commands;

import java.util.List;
import frontEnd.Turtle;

public abstract class ActionCommand extends ICommand{

	protected double myMagnitude;
	
	public ActionCommand(String string, double magnitude, List<Turtle> turtles){
		super(string, turtles);
		myMagnitude = magnitude;
	}

	@Override
	public double execute() {
		double distance = 0;
		for(Turtle current: myTurtles){
			distance += moveTurtle(current);
		}
		return distance;
	}

	protected abstract double moveTurtle(Turtle turtle);
}
