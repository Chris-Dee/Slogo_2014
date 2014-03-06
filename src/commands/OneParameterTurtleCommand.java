package commands;

import TurtleStuff.Turtle;

public abstract class OneParameterTurtleCommand extends OneParameterOperationCommand{
	
	protected Turtle myTurtle;
	
	public void setTurtle(Turtle turtle){
		if(turtle != null){
			myTurtle = turtle;
		}
	}
}
