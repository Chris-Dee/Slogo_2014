package commands;

import frontEnd.Turtle;

public abstract class TurtleCommand {
	public TurtleCommand(){}
	public abstract double execute(Turtle turtle, double magnitude);
	public abstract String getCommandType();
}
