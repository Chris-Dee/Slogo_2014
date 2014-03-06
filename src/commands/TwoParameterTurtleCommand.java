package commands;

import TurtleStuff.Turtle;

public abstract class TwoParameterTurtleCommand extends TwoParameterOperationCommand{
	
	protected Turtle myTurtle;
	
	public void setTurtle(Turtle turtle){
		if(turtle != null){
			myTurtle = turtle;
		}
	}
}
