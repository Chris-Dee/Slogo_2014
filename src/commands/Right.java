package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Right extends OneParameterTurtleCommand {
	
	public Right() {}

	@Override
	public String getCommandType() {
		return "RT";
	}

	@Override
	public double execute() {
		myTurtle.addRotation(-1.0*myMagnitude);
		return myMagnitude;
	}
}
