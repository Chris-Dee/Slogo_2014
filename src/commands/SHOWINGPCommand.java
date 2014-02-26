package commands;

import frontEnd.Turtle;

public class SHOWINGPCommand extends TurtleCommand {

	public SHOWINGPCommand(String string, Turtle turtle) {
		super(string, turtle);
	}

	@Override
	public double execute() {
		//if(myTurtle.){ return 1; }
		return 0;
	}

}
