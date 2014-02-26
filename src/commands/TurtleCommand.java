package commands;

import frontEnd.Turtle;

public abstract class TurtleCommand extends AbstractCommand{

	public static final int DEFAULT_MAGNITUDE = 0;
	
	protected double myMagnitude;
	protected Turtle myTurtle;
	
	public TurtleCommand(String string, double magnitude, Turtle turtle){
		super(string);
		myMagnitude = magnitude;
		myTurtle = turtle;
	}
	
	public TurtleCommand(String string, Turtle turtle){
		this(string, DEFAULT_MAGNITUDE, turtle);
	}
}
