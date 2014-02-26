package commands;

import frontEnd.Turtle;

public class SETHCommand extends TurtleCommand{

	public SETHCommand(String string, double degrees, Turtle turtle) {
		super(string, degrees, turtle);
	}

	@Override
	public double execute() {
		// need revision
		return 0;
	}

}
