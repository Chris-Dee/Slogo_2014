package commands;

import TurtleStuff.Turtle;

public abstract class NonParameterTurtleCommand implements AbstractCommand{
	protected Turtle myTurtle;
	
	public void setTurtle(Turtle turtle){
		if(turtle != null){
			myTurtle = turtle;
		}
	}
}