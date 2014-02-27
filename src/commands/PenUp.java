package commands;

public class PenUp extends TurtleCommand{
	
	public PenUp(){}

	@Override
	public double execute() {
		myTurtle.raisePen();
		return 0;
	}

	@Override
	public String getCommandType() {
		return "PU";
	}	
}
