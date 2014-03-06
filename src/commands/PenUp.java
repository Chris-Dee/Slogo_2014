package commands;

public class PenUp extends NonParameterTurtleCommand{
	
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
