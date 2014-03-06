package commands;

import java.util.List;

import TurtleStuff.Turtle;

public abstract class TwoParameterTurtleCommand extends TwoParameterOperationCommand{
	
	protected List<Turtle> myTurtles;
	
	public void setTurtles(List<Turtle> turtles){
		if(turtles != null){
			myTurtles = turtles;
		}
	}
	
	@Override
	public double execute() {
		double answer = 0;
		for(Turtle turtle: myTurtles){
			answer = executeTurtle(turtle);
		}
		return answer;
	}
	
	protected abstract double executeTurtle(Turtle turtle);
}
