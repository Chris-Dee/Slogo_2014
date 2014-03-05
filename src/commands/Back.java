package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Back extends OneParameterTurtleCommand {
	
	public Back() {}

	@Override
	public String getCommandType() {
		return "BK";
	}

	@Override
	public double execute() {
		myTurtle.goForward(-1.0*myMagnitude);
		return myMagnitude;
	}
}
