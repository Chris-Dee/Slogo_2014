package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Forward extends OneParameterTurtleCommand {
	
	public Forward() {}

	@Override
	public String getCommandType() {
		return "FD";
	}

	@Override
	public double execute() {
		myTurtle.goForward(myMagnitude);
		return myMagnitude;
	}
}
